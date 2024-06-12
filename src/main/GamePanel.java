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

    private MouseInputs mouseInputs; // MouseInputs instance to handle mouse input events
    private float xDelta = 100, yDelta = 100; // Player position deltas (change in position)
    private BufferedImage img; // BufferedImage to hold the main image containing player sprites
    private BufferedImage[][] animations; // 2D array to store different animations
    private int aniTick, aniIndex, aniSpeed = 15; // Variables to control animation speed and state
    private int playerAction = IDLE;
    private int playerDir = -1; // Player's current action and direction
    private boolean moving = false; // Flag to check if the player is moving

    public GamePanel() {
        mouseInputs = new MouseInputs(this); // Initialize the mouse input handler
        importImg(); // Import the player sprite sheet
        loadAnimations(); // Load player animations from the sprite sheet
        setPanelSize(); // Set the size of the game panel
        addKeyListener(new KeyboardInputs(this)); // Add keyboard input listener
        addMouseListener(mouseInputs); // Add mouse input listeners for mouse actions
        addMouseMotionListener(mouseInputs); // Add mouse input listeners for mouse motion
    }

    private void loadAnimations() { // Load animations from the sprite sheet
        animations = new BufferedImage[9][6]; // Initialize the 2D animations array with specific dimensions
        for (int j = 0; j < animations.length; j++) { // Iterate through the rows (different actions)
            for (int i = 0; i < animations[j].length; i++) { // Iterate through the columns (frames of each action)
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40); // Extract a subimage from the sprite sheet
            }
        }
    }

    private void importImg() { // Import the player sprite sheet
        InputStream is = getClass().getResourceAsStream("/player_sprites.png"); // Load the sprite sheet from resources
        try {
            img = ImageIO.read(is); // Read the image from the input stream
        } catch (IOException e) { // Print the stack trace if there's an error
            e.printStackTrace();
        } finally {
            try {
                is.close(); // Close the input stream
            } catch (IOException e) { // Print the stack trace if there's an error
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() { // Set the preferred size of the panel
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void setDirection(int direction) { // Set the player's direction
        this.playerDir = direction;
        moving = true; // Mark the player as moving
    }

    public void setMoving(boolean moving) { // Update the player's moving status
        this.moving = moving;
    }

    private void updateAnimationTick() { // Increment the animation tick counter and update animation frame
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) { // If the frame index exceeds the number of frames, reset it
                aniIndex = 0;
            }
        }
    }

    private void setAnimation() { // Update the player's action based on the moving status
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePos() { // Update the player's position based on the current direction
        if (moving) {
            switch (playerDir) {
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass's paintComponent method
        updateAnimationTick(); // Update the animation tick
        setAnimation(); // Set the current animation based on player state
        updatePos(); // Update the player's position
        g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 256, 160, null); // Draw the current frame of the player's animation at the current position
    }
}
