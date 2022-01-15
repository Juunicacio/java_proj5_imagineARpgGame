package imagineARpgGame;

import java.io.IOException;

public class imagineARpgGame {
	
	// making the program create a character:
	// creating an instance of our game_logic class
	static Game_Logic gl;

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		// creates first gui
		//MyGUI gui = new MyGUI();
		
		//launching pages
		LaunchPage launchPage = new LaunchPage();

		while(true) {				
			Game_Objects.initObjects();
			gl = new Game_Logic();
			gl.endGame=false;
			/* this 2 methods below is beeing called in the initGame method above
			// populate our NPC array and set up NPC database
			Game_Objects.initNPCArray();
			
			// populate our Item array and set up Item database
			Game_Objects.initItemArray();
			*/
			
			// Starting monsterThread
			MonsterThread mt = new MonsterThread(gl);
			mt.startMonsterThread();
			
			while(!gl.endGame) {
				game_loop();
				
			}
		}
	}
	
	// create a game_loop inside our main method
	public static void game_loop() {
		// instance the Game_Logic class
		// as it is inside our game_loop it will constantly wait for command
		gl.waitForCommand();
		
		
		
	}

}
