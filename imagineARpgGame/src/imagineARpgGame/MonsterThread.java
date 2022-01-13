package imagineARpgGame;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MonsterThread {
	Game_Logic currentGL;
	
	public MonsterThread(Game_Logic gl) {
		currentGL = gl;		
	}
	
	public void startMonsterThread() {
		Thread one = new Thread() {
			public void run() {
				try {
					while(true) {
						//System.out.println("Something is happening");
						populateGameWithMonsters();
						// waiting 1 sec and populate again
						Thread.sleep(1000);
						
						//System.out.println("Something is happening again");
					}
				} catch(InterruptedException v) {
					System.out.println("InterruptedException: " + v);
				}
			}
		};		
		one.start();
	}	
	
	public void populateGameWithMonsters() {
		int roomMobCount = 0;
		List<String> lines = new ArrayList<String>();
		try {
			// the currentGL give us access to all the methods inside the game logic that we can use
			lines = currentGL.readLines("MyTextFiles/MonsterLocs.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < lines.size(); i++) {
			String[] words = lines.get(i).split(" "); // like 'Name:', 'Dragon', '5'
			
			if (words[0].equals("Name:")) {
				// loop through rooms
				for(int y = 0; y< Game_Objects.room.size(); y++) {
					
					// if it is referring to create a 'Dragon' in the room '5'
					// if the room that we get is equals the number of the file text is asking for:
					if(Game_Objects.room.get(y).getNumber() == Integer.parseInt(words[2])) {
						
						// then loop through all the monster in room '5'
						for(int z = 0; z < Game_Objects.room.get(y).getNpcs().size(); z++) {
							
							// check if the room have a NPC of the name 'Dragon' in it
							if (Game_Objects.room.get(y).getNpcs().get(z).getId().equalsIgnoreCase(words[1])) {
								// if there is a Dragon in this room, increase roomMobCount
								roomMobCount++;
							}
						}
					}
				}
				
				// it will loop through room '5'.. it didn't find any 'Dragon', not increasing the roomMobCount
				if (roomMobCount == 0) {
					// loop through rooms once again, to find room 5
					for (int y = 0; y < Game_Objects.room.size(); y++) {
						if(Game_Objects.room.get(y).getNumber() == Integer.parseInt(words[2])) {
							
							// if we find room '5'
							// add a new NPC of the name 'Dragon'
							try {								
								// adding the fully qualified name of the class that we have summoned	
								String fullyQualifiedNPCClassName = "imagineARpgGame." + words[1];
								
								//System.out.println(fullyQualifiedNPCClassName);								
								Game_Objects.room.get(y).getNpcs().add( (NPC) Class.forName(fullyQualifiedNPCClassName).getDeclaredConstructor().newInstance());
								
							} //catch ( InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
							catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			roomMobCount = 0;
		}
		
		
		
		
		
		
		
		
	}
}
