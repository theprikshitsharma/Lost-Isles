package main;

import java.awt.Graphics;
import gamestate.Gamestate;
import gamestate.Menu;
import gamestate.Playing;

public class Game implements Runnable {
    
    private GameWindow gameWindow; // Reference to the game window
    private GamePanel gamePanel; // Reference to the game panel
    private Thread gameThread; // Thread for running the game loop
    private final int FPS_SET = 120; // Desired frames per second
    private final int UPS_SET = 200;
    
    
    private Playing playing;
    private Menu menu;
    
    //tiles set here
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.75f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    
    
    
    
    public Game() {
    	 initClasses();
        // Initialize GamePanel and GameWindow
        gamePanel = new GamePanel(this); // Create the game panel
        gameWindow = new GameWindow(gamePanel); // Create the game window and pass the panel
        
        gamePanel.requestFocus(); // Request focus for the game panel to ensure it receives keyboard inputs
        startGameLoop(); // Start the game loop in a new thread
        
    }

    private void initClasses() {
    	
    	menu = new Menu(this);
    	playing = new Playing(this);
	}

	public void startGameLoop() {
        // Create a new thread and start it to run the game loop
        gameThread = new Thread(this);
        gameThread.start();
    }


	private void update() {		
		switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case OPTIONS:
		case QUIT:
		default:
			System.exit(0);
			break;
		}
	}
	
	public void render (Graphics g) {
		switch(Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
		}
	}
	
    public void run() {
        // Calculate the time per frame based on the desired FPS
    	double timePerFrame = 1000000000.0 / FPS_SET;
    	double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;

        // Main game loop
        while(true) {

            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            
            if (deltaU >= 1) {
            	update();
            	updates++;
            	deltaU--;
            }
            
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            
            
//            if (now - lastFrame >= timePerFrame) {
//                gamePanel.repaint();
//                lastFrame = now;
//                frames++;
//            }  
            
            if (System.currentTimeMillis() - lastCheck >= 1000) {// Check if a second has passed to print the FPS
                lastCheck = System.currentTimeMillis();// Update the last check time
//                System.out.println("FPS " + frames + " | UPS: " + updates); // Print the number of frames rendered in the last second
                frames = 0; // Reset the frame count for the next second
                updates = 0;
            }
        }
    }
    
    public void windowFocusLost () {
    	if (Gamestate.state == Gamestate.PLAYING)
    		playing.getPlayer().resetDirBooleans();
    }
    
    public Menu getMenu() {
    	return menu;
    }
    
    public Playing getPlaying() {
    	return playing;
    }

}
