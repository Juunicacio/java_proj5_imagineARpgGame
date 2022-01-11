package imagineARpgGame;

import java.util.ArrayList;
import java.util.List;

// it will contains all the npcs and items, for example, in the game

public class Game_Objects {
	static PlayerCharacter player = new PlayerCharacter();
	// first we are gonna add an arraylist
	// it is gonna contain just Room type objects
	// now we can start adding rooms
	static ArrayList<Room> room = new ArrayList<Room>();
	
	// creating a static array list to put the npcs database
	static List<Object> NPCDataBase = new ArrayList<Object>();
	
	// list to put items in the database
	static List<Object> ItemDataBase = new ArrayList<Object>();
	
	
	
	// everytime you create a creature, you need to add it in this init array
	// the NPCDataBase will contain all the creature in the game
	public static void initNPCArray() {
		NPCDataBase.add(new NPC());
		NPCDataBase.add(new Troll());		
		NPCDataBase.add(new Dragon());	
	}
	
	// init item array
	public static void initItemArray() {
		ItemDataBase.add(new Item());
		ItemDataBase.add(new Sword());
		ItemDataBase.add(new Axe());
		ItemDataBase.add(new Ring());
	}
	

}
