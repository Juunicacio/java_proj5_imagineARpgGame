package imagineARpgGame;

//import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
		
		if(x[0].equals("create")) {
			createItem(x);
		}
		
		if(x[0].equals("get")) {
			get(x);
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
					
					// now we are gonna loop through the NPC list in that room					
					for (int y = 0; y< Game_Objects.room.get(i).getNpcs().size(); y ++) {
						// any NPC that is in that list we are gonna print the description
						// example "A Troll is standing here"
						System.out.println(Game_Objects.room.get(i).getNpcs().get(y).desc);
					}
					
					// look if there is any item in the room
					for (int y = 0; y< Game_Objects.room.get(i).getItems().size(); y ++) {
						// any NPC that is in that list we are gonna print the description
						// example "A Troll is standing here"
						System.out.println(Game_Objects.room.get(i).getItems().get(y).desc);
					}
					
					// later we can do a method that doesn't repeat it so many times ^
					
				}				
			}
		}		
		
		// if the player type look 'self' for example
		if(x.length==2) {
			if(x[1].equals("self")) {
				Game_Objects.player.look();
				// show the player the items he is carrying on	
				System.out.println("You are carrying: ");
				for(int i=0;i<Game_Objects.player.getItems().size();i++) {
					System.out.println(Game_Objects.player.getItems().get(i).name);
				}
			}
			
			// look throught the rooms
			// see what room the player is standing in
			for(int y=0;y<Game_Objects.room.size();y++) {
				if(Game_Objects.room.get(y).getNumber() == Game_Objects.player.getRoom()) {
					// loop through all the NPCs in that room
					for(int i=0; i<Game_Objects.room.get(y).getNpcs().size();i++) {
						// if the player typed look troll and in that room has an id of troll
						// it will call the look method inside that NPC
						if(x[1].equalsIgnoreCase(Game_Objects.room.get(y).getNpcs().get(i).getId())) {
							Game_Objects.room.get(y).getNpcs().get(i).look();
						}
					}					
				}
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
						if(Game_Objects.room.get(y).getNumber() == Game_Objects.player.getRoom()) {
							try {
								// the method Class.forName will take the string 'localNPC.getid'
								// and check to see if there is a class that is available that matches that string
								// creating a new instance of it
								// if the user typer 'Troll' - localNPC.Troll
								// we do have a class called Troll, adding a npc to that
								// adding a NPC to the list of NPCS in that room
								
				                /*Class tr = Troll.class;
				                System.out.println(tr.getName());*/
								
								// adding the fully qualified name of the class that we have summoned	
								String fullyQualifiedNPCClassName = "imagineARpgGame." + localNPC.getId();
								
								//String fullyQualifiedNPCClassName = "imagineARpgGame." + localNPC.getId();
								//System.out.println(fullyQualifiedNPCClassName);
								
								Game_Objects.room.get(y).getNpcs().add((NPC) Class.forName(fullyQualifiedNPCClassName).getDeclaredConstructor().newInstance());
								// we will get the last NPC that was added in the list of NPCs in the room the player is at
								// so if we add an NPC to the NPC list inside of the room, it has a size of 1
								// print the name of what the player summoned at the position 0, will have the size - 1								
								System.out.println("You summoned a "+ Game_Objects.room.get(y).getNpcs().get(Game_Objects.room.get(y).getNpcs().size() - 1).name);
							} catch ( InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	// create item
	public void createItem(String[] x) {
		// if the player just typed "create"
		if(x.length == 1) {
			System.out.println("Create what exactly?");
		}
		// if the player typed "create something"
		if(x.length == 2) {			
			// loop through the entire ItemDataBase			
			for(int i=0; i<Game_Objects.ItemDataBase.size();i++) {
				// creating a copy of each one as we go through
				Item localItem = (Item) Game_Objects.ItemDataBase.get(i);
				
				// check the id of the item to see if that matches what the player typed in			
				if(localItem.getId().equalsIgnoreCase(x[1])){
					// if it matches, we are gonna loop through all the rooms
					// to see what room the player is standing in
					for(int y=0; y<Game_Objects.room.size();y++) {
						if(Game_Objects.room.get(y).getNumber() == Game_Objects.player.getRoom()) {
							// now we need to do something with that room --> create an item, a sword for example
							try {								
								// adding the fully qualified name (name with 'path') of the item class that we have created								
								String fullyQualifiedItemClassName = "imagineARpgGame." + localItem.getId();
								
								Game_Objects.room.get(y).getItems().add((Item) Class.forName(fullyQualifiedItemClassName).getDeclaredConstructor().newInstance());
								// print what you have created
								System.out.println("You created a "+ Game_Objects.room.get(y).getItems().get(Game_Objects.room.get(y).getItems().size() - 1).name);
							} catch ( InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	// Player - get Items that is in that room
	public void get(String[] x) {
		// if the player just typed 'get'
		if (x.length == 1) {
			System.out.println("Get what exactly?");
		}
		if (x.length == 2) {
			// loop through all the items in the database
			for(int i=0; i<Game_Objects.ItemDataBase.size(); i++) {
				// then loop through all the rooms
				for (int y=0;y<Game_Objects.room.size();y++) {
					// if the player wanna gets something, he'll have to get it from the room that he is in					
					if(Game_Objects.room.get(y).getNumber() == Game_Objects.player.getRoom()) {
						// loop through all items that is in that room
						for(int z = 0; z < Game_Objects.room.get(y).getItems().size();z++) {
							// if there is an item that has the same name that the player wants to get, he can take it
							if(x[1].equalsIgnoreCase(Game_Objects.room.get(y).getItems().get(z).getId())) {
								Item localItem = Game_Objects.room.get(y).getItems().get(z);
								
								// add it to the player item list
								Game_Objects.player.getItems().add(localItem);
								System.out.println("You picked up a " + localItem.getName());
								
								// remove the item from the room
								Game_Objects.room.get(y).getItems().remove(z);
								break;
								
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
