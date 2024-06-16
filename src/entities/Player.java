package entities;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.UP;
import static utilz.Constants.PlayerConstants.GetSpriteAmount;
import static utilz.Constants.PlayerConstants.IDLE;
import static utilz.Constants.PlayerConstants.RUNNING;
import static utilz.Constants.PlayerConstants.ATTACK_1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utilz.LoadSave;

public class Player extends Entity{
	
	private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15; // Variables to control animation speed and state
    private int playerAction = IDLE;
    private boolean moving = false , attacking = false; // Flag to check if the player is moving
    private boolean left , up , right , down;
    private float playerSpeed = 2.0f;
    
	public Player(float x, float y, int width , int height) {
		super(x, y, width , height);
		loadAnimations();
	}
	
	public void update() {
		updatePos(); // Update the player's position
		updateAnimationTick(); // Update the animation tick
        setAnimation(); // Set the current animation based on player state
	
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height, null);
	}
	
    private void updateAnimationTick() { // Increment the animation tick counter and update animation frame
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) { // If the frame index exceeds the number of frames, reset it
            	aniIndex = 0;
            	attacking = false;
            	
            	
            }
        }
    }

    private void setAnimation() { // Update the player's action based on the moving status
        
    	int startAni = playerAction;
    	if (moving) 
            playerAction = RUNNING;
        else 
            playerAction = IDLE;
        
        if (attacking)
        	playerAction = ATTACK_1;
        
        if (startAni != playerAction)
        	resetAniTick();
    }
    
    private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
    	
    	moving = false;
    	
    	if(left && !right) {
    		x -= playerSpeed;
    		moving = true;
    	} else if(right && !left) {
    		x += playerSpeed;
    		moving = true;
    	}
    	
    	if(up && !down) {
    		y -= playerSpeed;
    		moving = true;
    	} else if(down && !up) {
    		y += playerSpeed;
    		moving = true;
    	}
	}
    

	 private void loadAnimations() { // Load animations from the sprite sheet
			
		 	BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
	            animations = new BufferedImage[9][6]; // Initialize the 2D animations array with specific dimensions
		        for (int j = 0; j < animations.length; j++)  // Iterate through the rows (different actions)
		            for (int i = 0; i < animations[j].length; i++)  // Iterate through the columns (frames of each action)
		                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40); // Extract a subimage from the sprite sheet
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
	        
	 
	 
	    }
	

