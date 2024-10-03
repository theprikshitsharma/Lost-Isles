package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	
    private JFrame jframe; // JFrame to represent the game window
    
    public GameWindow(final GamePanel gamePanel) {
    	
        jframe = new JFrame(); // Create a new JFrame instance
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the application when the window is closed
        jframe.add(gamePanel); // Add the game panel to the JFrame
        jframe.setResizable(false); // Prevent the window from being resized
        jframe.pack(); // Size the window to fit the preferred size and layouts of its components
        jframe.setLocationRelativeTo(null); // Center the window on the screen
        jframe.setVisible(true); // Make the window visible
        jframe.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				
				
			}
			
			
			public void windowLostFocus(WindowEvent e) {
				
				gamePanel.getGame().windowFocusLost();
				
			}
        	
        	
        });
        
        
    }
}
