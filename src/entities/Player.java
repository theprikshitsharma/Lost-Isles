package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class Player extends Entity{
	
	private Playing playing;
	
	private BufferedImage[][] animations; 
    private int aniTick, aniIndex, aniSpeed = 10; // Variables to control animation speed and state
    private int playerAction = IDLE;
    private boolean moving = false , attacking = false; // Flag to check if the player is moving
    private boolean left , up , right , down, jump;
    private float playerSpeed = 1f * Game.SCALE;
    private int[][] lvlData;
    private float xDrawOffset = (float)(3.42 * Game.SCALE); // x offset for hitbox
    private float yDrawOffset = (float)(3.42 * Game.SCALE); // y offset for hitbox
    
    // jumping and gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    
    // Status bar UI 
    
    private BufferedImage statusBarImg;
    
    private int statusBarWidth = (int) (219 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (10 * Game.SCALE);
    private int statusBarY = (int) (10 * Game.SCALE);
    
    private int healthBarWidth = (int) (172 * Game.SCALE);
    private int healthBarHeight = (int) (4 * Game.SCALE);
    private int healthBarXStart = (int) (34 * Game.SCALE);
    private int healthBarYStart = (int) (14 * Game.SCALE);
    
    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;
    
    //Attack Box
    private Rectangle2D.Float attackBox;
    
    private int flipX = 0;
    private int flipW = 1;
    
    private boolean attackChecked;
    
	public Player(float x, float y, int width , int height , Playing playing) {
		super(x, y, width , height);
		this.playing = playing;
		loadAnimations();
		initHitbox(x , y , (int) (17 * Game.SCALE), (int) (28 * Game.SCALE)); // 20 and 27 is the size of hitbox of the image sprite
		initAttackBox();
	}
	
	private void initAttackBox() {
	
		attackBox = new Rectangle2D.Float(x , y , (int) (20 * Game.SCALE) , (int) (20 * Game.SCALE));
		
	}

	public void update() {
		
		updateHealthBar();
		
		if (currentHealth <= 0) {
			playing.setGameOver(true);
			return;
		}
		
		updateAttackBox();
		updatePos(); // Update the player's position
		if (attacking)
			checkAttack();
		updateAnimationTick(); // Update the animation tick
        setAnimation(); // Set the current animation based on player state
	
	}

	private void checkAttack() {
		if (attackChecked || aniIndex != 3)
			return;
		attackChecked = true;
		playing.checkEnemyHit(attackBox);
		
	}

	private void updateAttackBox() {
		
		if (right) {
			attackBox.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 10);
		}
		else if (left) {
			attackBox.x = hitbox.x - hitbox.width - (int) (Game.SCALE * 10);
		}
		attackBox.y = hitbox.y + (Game.SCALE * 10);
		
	}

	private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
	}

	public void render(Graphics g, int lvlOffset) {
		g.drawImage(animations[playerAction][aniIndex], 
				(int) (hitbox.x - xDrawOffset) - lvlOffset + flipX, 
				(int) (hitbox.y - yDrawOffset), 
				width * flipW, height, null);
		drawHitbox(g , lvlOffset); // Used for testing purposes 
		drawAttackBox(g , lvlOffset);
		drawUI(g);
	}
	
    private void drawAttackBox(Graphics g, int lvlOffsetX) {
		g.setColor(Color.red);
		g.drawRect((int)attackBox.x - lvlOffsetX, (int)attackBox.y , (int)attackBox.width , (int)attackBox.height);
	}

	private void drawUI(Graphics g) {
		
		g.drawImage(statusBarImg, statusBarX, statusBarY , statusBarWidth , statusBarHeight , null);
    	g.setColor(Color.red);
    	g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth , healthBarHeight);
	}

	private void updateAnimationTick() { // Increment the animation tick counter and update animation frame
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) { // If the frame index exceeds the number of frames, reset it
            	aniIndex = 0;
            	attacking = false;
            	attackChecked = false;
            }
        }
    }

    private void setAnimation() { // Update the player's action based on the moving status
        
    	int startAni = playerAction;
    	if (moving) 
            playerAction = RUNNING;
        else 
            playerAction = IDLE;
    	
    	if(inAir) {
    		if(airSpeed < 0)
    			playerAction = JUMP;
    		else
    			playerAction = FALLING;
    	}
        
        if (attacking) {
        	playerAction = ATTACK_1;
        	if (startAni != ATTACK_1) {
        		aniIndex = 3;
        		aniTick = 0;
        		return;
        	}
        	
        }
        if (startAni != playerAction)
        	resetAniTick();
    }
    
    private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;
		
		if(jump)
			jump();
//    	if (!left && !right && !inAir)
//    		return;
    	
		if (!inAir)
			if (!left && !right || (left && right))
				return;
		
    	float xSpeed = 0;
    	if(left) {
    		xSpeed -= playerSpeed;  	
    		flipX = width;
    		flipW = -1;
    	}
    	if(right) {
    		xSpeed += playerSpeed;
    		flipX = 0;
    		flipW = 1;
    	}
    	if (!inAir) 
    		if (!IsEntityOnFloor(hitbox, lvlData)) 
    			inAir = true;
 
	    if(inAir) {
	    	if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
	    		hitbox.y += airSpeed;
	    		airSpeed += gravity;
	    		updateXPos(xSpeed);
	    	}else {
	    		hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
	    		if(airSpeed > 0) 
	    			resetInAir();
	    		else
	    			airSpeed = fallSpeedAfterCollision;
	    	}
	    }
	    else 
	    	updateXPos(xSpeed);
    	moving = true;
	}
    
	 private void jump() {
		 if(inAir) 
			 return;
		 inAir = true;
		 airSpeed = jumpSpeed;
	 }
	 private void resetInAir() {
		 inAir = false;
		 airSpeed = 0;
	 }
	 private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
	 	hitbox.x += xSpeed;
	 	} else {
	 		hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
	 	}
		
	}
	 
	 public void changeHealth(int value) {
		 currentHealth += value;
		 
		 if (currentHealth <= 0) {
			 currentHealth = 0;
			 // gameOver();
		 } else if (currentHealth >= maxHealth)
			 currentHealth = maxHealth;
	 }

	private void loadAnimations() { // Load animations from the sprite sheet
			
		 	BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
	            animations = new BufferedImage[7][16]; // Initialize the 2D animations array with specific dimensions
		        for (int j = 0; j < animations.length; j++)  // Iterate through the rows (different actions)
		            for (int i = 0; i < animations[j].length; i++)  // Iterate through the columns (frames of each action)
		                animations[j][i] = img.getSubimage(i * 77, j * 74, 77, 74); // Extract a subimage from the sprite sheet //animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
		        
		        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
	            }
	 
	 
	 public void loadLvlData(int [][] lvlData) {
		 this.lvlData = lvlData;
		 if(!IsEntityOnFloor(hitbox, lvlData))
			 inAir = true;
	 }
	 
	 public void resetDirBooleans() {
		 left = false;
		 right = false;
		 up = false;
		 down = false;
	 }
	 
	 public void setAttack(boolean attacking) {
		 this.attacking = attacking;
	 }
 
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	        
	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public void resetAll() {
		resetDirBooleans();
		inAir = false;
		attacking = false;
		moving = false;
		playerAction = IDLE;
		currentHealth = maxHealth;
		
		hitbox.x = x;
		hitbox.y = y;
		
		if (!IsEntityOnFloor(hitbox, lvlData)) 
			inAir = true;
	}
	 
}
	

