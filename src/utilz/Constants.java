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
        public static final int GROUND = 4; // Player on ground action
        public static final int HIT = 5; // Player hit action
        public static final int ATTACK_1 = 6; // Player attack 1 action
        public static final int ATTACK_JUMP_1 = 7; // Player attack while jumping action 1
        public static final int ATTACK_JUMP_2 = 8; // Player attack while jumping action 2

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
                case GROUND:
                    return 1; // Return the number of sprites for on ground action
                case HIT:
                    return 8; // Return the number of sprites for hit action
                case ATTACK_1:
                case ATTACK_JUMP_1:
                case ATTACK_JUMP_2:
                    return 11; // Return the number of sprites for jump and attack actions
                default:
                    return 1; // Default case, return 1 sprite
                    
            }
        }
    }
}