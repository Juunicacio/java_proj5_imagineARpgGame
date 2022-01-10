package imagineARpgGame;

import java.util.Scanner;

public class Game_Logic {
	public Game_Logic() {
		// adding a room with its name
		Game_Objects.room.add(new Room(1));
		// later on, we will have a text file that contains the desc and exits of the created room
		// adding it manually:
		// get the room in position 0 (the room number 1)
		Game_Objects.room.get(0).setName("First Room");
		Game_Objects.room.get(0).addDescription("Desc Line 1");
		Game_Objects.room.get(0).addDescription("Desc Line 2");
		Game_Objects.room.get(0).addDescription("Desc Line 3");
		Game_Objects.room.get(0).addDescription("Desc Line 4");
		Game_Objects.room.get(0).addLinkingExits("South links to room 2");
		Game_Objects.room.get(0).addLinkingExits("North links to room 3");
		
		
	}
	
	// print out the question and wait
	public void waitForCommand() {
		if(Game_Objects.player.getRoom() == 0) {
			createCharacter();
		}
		System.out.println("What you wanna do now?");
		Scanner sc = new Scanner(System.in);
		// scanner whatever the user types
		String com = sc.nextLine();
		// parse the command by spaces
		// read each word the user types into an array valueString s = *This is a sample
		// sentence. *;
		String[] words = com.split(" ");
		processCommand(words);
		
	}
	
	// it  will be like a database of commands, with if statements
	// see all of the commands you have created 
	public void processCommand(String[] x) {
		// if the first word that the user typed was look
		if(x[0].equals("look")) {
			// pass the entire array that was typed in
			// example: look troll - look position 0, troll position 1
			look(x);
		}
		if(x[0].equals("summon")) {
			summon(x);
		}
		
	}
	
	// looping and pulling things out that we want from the array
	public void look(String[] x) {
		// if the user types just look:
		if(x.length == 1) {
			for(int i=0; i < Game_Objects.room.size(); i++) {
				// if the room is the same as the player is in:
				if(Game_Objects.room.get(i).getNumber() == Game_Objects.player.getRoom()) {
					System.out.println(Game_Objects.room.get(i).getName());
					// loop through all the desc in the room
					for(int y=0; y<Game_Objects.room.get(i).getArrayDescSize(); y++) {
						System.out.println(Game_Objects.room.get(i).getDesc().get(y));
					}
					System.out.println("Exits:");
					for(int y=0; y<Game_Objects.room.get(i).getArrayExitsSize(); y++) {
						// print just the words, we will get the size of the exits
						// create an array out of that 
						// example: ("South links to room 2")
						// print the first word: South
						String exitFullName = Game_Objects.room.get(i).getExitsbyIndex(y);
						String[] exitName = exitFullName.split(" ");
						System.out.println(exitName[0]);
					}
				}				
			}
		}		
		
		// if the player type look 'self' for example
		if(x.length==2) {
			if(x[1].equals("self")) {
				Game_Objects.player.look();
			}
			
		}
	}
	
	public void summon(String[] x) {
		// if the player types just summon
		if(x.length == 1) {
			System.out.println("Summon what exactly?");
		}
		// check what that second word is
		if (x.length == 2) {
			// loop through the entire NPC database
			for (int i = 0; i < Game_Objects.NPCDataBase.size(); i++) {
				// CAST it from Object to NPC and save it as localNPC
				NPC localNPC = (NPC) Game_Objects.NPCDataBase.get(i);
				// check if what the user types matches the name of the enemy
				if(localNPC.getId().equalsIgnoreCase(x[1])) {
					// loop through all the rooms in the game
					for (int y=0; y< Game_Objects.room.size(); y++) {
						// check to see if the room matches the room the player is in
						// get every room
						/*System.out.println("----------------------");
						System.out.println("get(y): "+Game_Objects.room.get(y));
						System.out.println("get(y).getNumber(): "+ Game_Objects.room.get(y).getNumber());
						System.out.println("y: " + y +" ---- "+ " getNumber(y): " + Game_Objects.room.get(y).getNumber());
						System.out.println("----------------------");*/
						if(Game_Objects.room.get(y).getNumber() == Game_Objects.player.getRoom()) {
							try {
								// the method Class.forName will take the string 'localNPC.getid'
								// and check to see if there is a class that is available that matches that string
								// creating a new instance of it
								// if the user typer 'Troll' - localNPC.Troll
								// we do have a class called Troll, adding a npc to that
								Game_Objects.room.get(y).getNpcs().add(NPC) Class.forName(localNPC.getId()).newInstance());
								
								Game_Objects.room.get(Game_Objects.player.getRoom() - 1).getNpcs().add(NPC);
								// 
								System.out.println("You summon a "+ Game_Objects.room.get(y).getNpcs().get(Game_Objects.room.get(y).getNpcs().size() - 1).name);
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	public void createCharacter() {
		System.out.println("Welcome to the Game. What is your name?");
		// creating a new scanner
		// later change it to the top at the class since I will create it all the time
		Scanner sc = new Scanner(System.in);
		// pass what the user types in to the player's name
		Game_Objects.player.setName(sc.next());
		System.out.println("Hello "+ Game_Objects.player.getName() + ", you have 100hp and 75 accuracy to start");
		Game_Objects.player.setHp(100);
		Game_Objects.player.setAccuracy(75);
		// As the Player is created, it will have the room number of 1
		Game_Objects.player.setRoom(1);		
	}

}
