package utilz;

import main.Game;

public class Constants {
	
	public static class Environment {
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;
		
		public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
		
	}
	
	public static class UI {
		public static class Buttons {
	        public static final int B_WIDTH_DEFAULT = 140;
	        public static final int B_HEIGHT_DEFAULT = 56;
	        public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
	        public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}
		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
	}
	
	
	
    // Inner class for directional constants
    public static class Directions {
        // Constants representing directions
        public static final int LEFT = 0; // Left direction
        public static final int UP = 1; // Up direction
        public static final int RIGHT = 2; // Right direction
        public static final int DOWN = 3; // Down direction
    }

    // Inner class for player-related constants
    public static class PlayerConstants {
    	
        // Constants representing player actions
        public static final int IDLE = 0; // Player idle action
        public static final int RUNNING = 1; // Player running action
        public static final int JUMP = 2; // Player jump action
        public static final int FALLING = 3; // Player falling action
        public static final int HIT = 4; // Player hit action
        public static final int ATTACK_1 = 5; // Player attack 1 action
        public static final int DEAD = 6; // Player attack 1 action


        // Method to get the number of sprites for a given player action
        public static int GetSpriteAmount(int player_action) {
        	
            switch(player_action) {

            	case IDLE:
                return 9; // Return the number of sprites for idle action
                case RUNNING:
                    return 16; // Return the number of sprites for running action
                case JUMP:
                	return 4;
                case FALLING:
                	return 2;
                case ATTACK_1:
                    return 11; // Return the number of sprites for jump and attack actions
                case HIT:
                    return 8; // Return the number of sprites for hit action
                case DEAD:
                    return 9; // Return the number of sprites for hit action
                default:
                    return 1; // Default case, return 1 sprite
                    
            }
        }
    }
    
    // enemies 
    
    public static class EnemyConstants {
    	
    	public static final int CRABBY = 0;
    	
    	public static final int IDLE = 0;
    	public static final int RUNNING = 1;
    	public static final int ATTACK = 2;
    	public static final int HIT = 3;
    	public static final int DEAD = 4;
    	
    	public static final int CRABBY_WIDTH_DEFAULT = 72;
    	public static final int CRABBY_HEIGHT_DEFAULT = 32;
    	
    	public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
    	public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);
    	
    	public static final int CRABBY_DRAWOFFSET_X = (int) (26 * Game.SCALE);
    	public static final int CRABBY_DRAWOFFSET_Y = (int) (9 * Game.SCALE);
    	
    	public static int GetSpriteAmount(int enemy_type, int enemy_state) {
    		
    		switch (enemy_type) {
    		case CRABBY:
    			switch (enemy_state) {
    			case IDLE:
    				return 9;
    			case RUNNING:
    				return 6;
    			case ATTACK:
    				return 7;
    			case HIT:
    				return 4;
    			case DEAD:
    				return 5;
    			}
    		}
    		return 0;
    	}
    	
    	public static int GetMaxHealth(int enemy_type) {
    		switch(enemy_type) {
    		case CRABBY:
    			return 10;
    		default:
    			return 1;
    		}
    	}
    	
    	public static int GetEnemyDamage(int enemy_type) {
    		switch(enemy_type) {
    		case CRABBY:
    			return 15;
    		default:
    			return 0;
    		}
    	}
    		
    	
    }
}