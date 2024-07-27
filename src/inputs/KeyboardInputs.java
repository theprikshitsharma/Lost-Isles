package inputs;

import java.awt.event.KeyEvent;
import entities.Player;
import gamestate.Gamestate;

import java.awt.event.KeyListener;

import main.Game;
import main.GamePanel;

import static utilz.Constants.Directions.*;
public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public void keyReleased(KeyEvent e) {
		switch (Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyReleased(e);
			break;
		default:
			break; 
		}
	}

	public void keyPressed(KeyEvent e) {
		switch (Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyPressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyPressed(e);
			break;
		default:
			break; 
		}
	}

}