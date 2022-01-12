package imagineARpgGame;

public class imagineARpgGame {
	
	// making the program create a character:
	// creating an instance of our game_logic class
	static Game_Logic gl = new Game_Logic();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// populate our NPC array and set up NPC database
		Game_Objects.initNPCArray();
		
		// populate our Item array and set up Item database
		Game_Objects.initItemArray();
		
		while(true) {
			game_loop();
		}
	}
	
	// create a game_loop inside our main method
	public static void game_loop() {
		// instance the Game_Logic class
		// as it is inside our game_loop it will constantly wait for command
		gl.waitForCommand();
		
		
		
	}

}
