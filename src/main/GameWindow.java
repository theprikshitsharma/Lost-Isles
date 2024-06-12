package main;

import javax.swing.JFrame;

public class GameWindow {
	
    private JFrame jframe; // JFrame to represent the game window
    
    public GameWindow(GamePanel gamePanel) {
    	
        jframe = new JFrame(); // Create a new JFrame instance
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the application when the window is closed
        jframe.add(gamePanel); // Add the game panel to the JFrame
        jframe.setLocationRelativeTo(null); // Center the window on the screen
        jframe.setResizable(false); // Prevent the window from being resized
        jframe.pack(); // Size the window to fit the preferred size and layouts of its components
        jframe.setVisible(true); // Make the window visible
        
    }
}
