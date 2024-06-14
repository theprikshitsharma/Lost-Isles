package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;// MouseInputs instance to handle mouse input events
    private Game game;
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this); // Initialize the mouse input handler
        this.game = game;
        
        setPanelSize();
        addKeyListener(new KeyboardInputs(this)); // Add keyboard input listener
        addMouseListener(mouseInputs); // Add mouse input listeners for mouse actions
        addMouseMotionListener(mouseInputs); // Add mouse input listeners for mouse motion
    }
    private void setPanelSize() {
		Dimension size = new Dimension(1280 , 800);
		setPreferredSize(size);
		
	}
	public void updateGame() {
    	
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass's paintComponent method
        game.render(g);
    }
    
    public Game getGame() {
    	return game;
    }
}
