package main;

public class Game implements Runnable {
    
    private GameWindow gameWindow; // Reference to the game window
    private GamePanel gamePanel; // Reference to the game panel
    private Thread gameThread; // Thread for running the game loop
    private final int FPS_SET = 120; // Desired frames per second

    public Game() {
        // Initialize GamePanel and GameWindow
        gamePanel = new GamePanel(); // Create the game panel
        gameWindow = new GameWindow(gamePanel); // Create the game window and pass the panel
        
        gamePanel.requestFocus(); // Request focus for the game panel to ensure it receives keyboard inputs
        startGameLoop(); // Start the game loop in a new thread
    }

    private void startGameLoop() {
        // Create a new thread and start it to run the game loop
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        // Calculate the time per frame based on the desired FPS
        double timePerFrame = 1000000000 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        // Main game loop
        while(true) {
            now = System.nanoTime();
            
            // Check if enough time has passed to render the next frame
            if (now - lastFrame >= timePerFrame) {
                // Repaint the game panel to update the game visuals
                gamePanel.repaint();
                
                // Update the time of the last frame rendered
                lastFrame = now;
                
                // Increment the frame count
                frames++;
            }

            // Check if a second has passed to print the FPS
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                // Update the last check time
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS " + frames); // Print the number of frames rendered in the last second
                frames = 0; // Reset the frame count for the next second
            }
        }
    }
}
