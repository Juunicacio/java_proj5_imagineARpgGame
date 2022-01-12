package imagineARpgGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Game_Logic {
	public Game_Logic() {
		// adding a room with its name
		Game_Objects.room.add(new Room(0));
		// later on, we will have a text file that contains the desc and exits of the created room
		// adding it manually:
		// get the room in position 0 (the room number 1)
		/*Game_Objects.room.get(0).setName("First Room");
		Game_Objects.room.get(0).addDescription("Desc Line 1");
		Game_Objects.room.get(0).addDescription("Desc Line 2");
		Game_Objects.room.get(0).addDescription("Desc Line 3");
		Game_Objects.room.get(0).addDescription("Desc Line 4");
		Game_Objects.room.get(0).addLinkingExits("South links to room 2");
		Game_Objects.room.get(0).addLinkingExits("North links to room 3");*/
		
		// array list with the rooms info
		List<String> roomInfo = new ArrayList<>();		
		try {
			// we will have the entire Textfile inside of our roomInfo	
			roomInfo = readLines("MyTextFiles/RoomDescriptions.txt");			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// parsing the roomInfo by semicolons and spaces
		// loop through roomInfo
		for(int i = 0; i < roomInfo.size(); i++) {
			// it will give me all the lines
			String[] firstWord = roomInfo.get(i).split(" ");
			String[] everythingElse = roomInfo.get(i).split(":");
			
			if(firstWord[0].equals("Name:")) {
				int currentRoomSize = Game_Objects.room.size();
				
				Game_Objects.room.add(new Room(currentRoomSize));
				// because if we have 1 room, it will have the index 0, for example
				// set the name and number of it
				Game_Objects.room.get(Game_Objects.room.size() - 1).setName(everythingElse[1]);
				Game_Objects.room.get(Game_Objects.room.size() - 1).setNumber(currentRoomSize);				
				
				System.out.println("-----------");
				System.out.println(Game_Objects.room.get(Game_Objects.room.size() - 1).getName());				
				
				// it will needs to know everytime that it see the word "Name:"
				int roomCount = 0;
				for(int y = 0; y< roomInfo.size(); y++) {
					String[] nextFirstWord = roomInfo.get(y).split(" ");
					
					if(nextFirstWord[0].equals("Name:")) {
						roomCount++;
					}
					
					if(roomCount == currentRoomSize) {
						if(nextFirstWord[0].equals("Desc:")) {
							String[] nextEverythingElse = roomInfo.get(y).split(":");
							
							// add all the descriptions in the descriptions array
							Game_Objects.room.get(Game_Objects.room.size() - 1).getDesc().addAll(Arrays.asList(nextEverythingElse));							
						}
					}
				}
					
					// adding the Exits saved in the text file
					/*if(roomCount == currentRoomSize) {
						if(nextFirstWord[0].equals("Exit:")) {
							String[] nextEverythingElse = roomInfo.get(z).split(":");*/
							
							// Converting [Exit:, South, links, to, room, 1] into 
							// Exit: South links to room 1							
							/*StringBuilder sb = new StringBuilder();
							for(String str: nextEverythingElse) {
								sb.append(str);
								// add space between elements
								sb.append(" ");
							}
							String exitString = sb.toString();*/
							
							//System.out.println("My string is: " + exitString);
							
							//String separator = ", ";
							//String toPrint =
							
							// add all the desc of that exit
							//Game_Objects.room.get(Game_Objects.room.size() - 1).getExits().addAll(Arrays.asList(nextEverythingElse);
							//Game_Objects.room.get(Game_Objects.room.size() - 1).getExits().addAll(Arrays.asList(exitString));
							//Game_Objects.room.get(Game_Objects.room.size() - 1).addLinkingExits(exitString);
						//}
					//}
					
				// put the exits infos here to see if works, after checking the nextFirstWord
				roomCount = 0;
				for(int z =0; z< roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if(nextFirstWord[0].equals("Name:")) {
						roomCount++;
					}
					if (roomCount == currentRoomSize) {
						if(nextFirstWord[0].equals("Exit:")) {
							String[] nextEverythingElse = roomInfo.get(z).split(":");
							
							System.out.println("My list is: " + Arrays.asList(nextEverythingElse));
							
							String test;
							if(nextEverythingElse.length > 1) {
								test = nextEverythingElse[1];
							}
							else {
								test = nextEverythingElse[0];
							}
							
							// I will try it tomorrow!
							// tks everyone that has been here!!
							
							
							System.out.println("My string is: " + test);
							
							//Game_Objects.room.get(Game_Objects.room.size() - 1).addLinkingExits(test);
							
							Game_Objects.room.get(Game_Objects.room.size() - 1).getExits().add(test);
							//Game_Objects.room.get(Game_Objects.room.size() - 1).getExits().addAll(Arrays.asList(nextEverythingElse));
						}
					}
				}				
			}
		}		
	}
	
	// this bit you can put off from internet:
	// it takes the file and reads it into an array of lines
	// returning all the lines in the file
	public List<String> readLines(String filename) throws IOException{
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		List<String> lines = new ArrayList<String>();
		String line = null;
		//String line = bufferedReader.readLine();
		
		//System.out.println(bufferedReader.readLine());
		
		while((line = bufferedReader.readLine()) != null) {
		//while(line != null) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines;
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
		
		if(x[0].equals("wear")) {
			Game_Objects.player.wear(x);
		}
		
		if(x[0].equals("equipments")) {
			Game_Objects.player.equipments();
		}
		
		if(x[0].equals("remove")) {
			Game_Objects.player.remove(x);
		}
		
		if(x[0].equals("attack")) {
			Game_Objects.combat.attack(x);
		}
		
		if(x[0].equals("move")) {
			move(x);
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
			
			// look through the rooms
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
	
	// move the player between rooms
	public void move(String [] x) {
		// if the user just typed move
		if(x.length == 1) {
			System.out.println("Move where?");
		}
		
		// if the user typed 'move south' for example
		if(x.length == 2) {
			// looping through all the rooms in the game
			for(int i=0; i<Game_Objects.room.size(); i++) {
				// if the room is the same as the player is in
				if(Game_Objects.room.get(i).getNumber() == Game_Objects.player.getRoom()) {
					
					// loop through all the exits present in that room
					for(int y =0; y<Game_Objects.room.get(i).getArrayExitsSize(); y++) {
						
						// it will print: South links to room 2
						//System.out.println(Game_Objects.room.get(i).getExits().get(y));
						// now I need to get the first word 'South' and save it in a variable
						
						String exitWholeString = Game_Objects.room.get(i).getExits().get(y);
						String[] exitArray = exitWholeString.split(" ");
						
						// exitArray[0] == South
												
						// if the exit the user typed exists in that room
						if(x[1].equalsIgnoreCase(exitArray[0])) {
							//System.out.println("You leave " + Game_Objects.player.getRoom());
							// print player's room
							System.out.println("You just leave the room number: "+Game_Objects.player.getRoom());
							
							// I need to move the player for the room 2 when he calls for south
							// exitArray[-1] == 2 (number of the room present in the South exit)	
							// South links to room 2 - I need the number 2
							String lastWordOfExit =  exitArray[exitArray.length - 1];							
							int exitInInt = Integer.parseInt(lastWordOfExit);
							
							// set new player's room number
							Game_Objects.player.setRoom(exitInInt);
							
							System.out.println("Now you are at the room number: "+Game_Objects.player.getRoom());
							
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
