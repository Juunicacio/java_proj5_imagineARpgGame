package imagineARpgGame;

import java.util.ArrayList;

public class PlayerCharacter {
	// you can add what you want in here
	private String name;
	private int hp;
	private int accuracy;
	// if room == 0, this player don't exist in the game
	private int inRoom = 0;
	
	// list of player items
	private ArrayList<Item> items = new ArrayList<Item>();
	
	// list of player carried items
	private ArrayList<Item> carriedItems = new ArrayList<Item>();
	
	public void setName(String name) {
		this.name = name;		
	}
	
	public void setHp(int hpPoints) {
		this.hp = hpPoints;		
	}
	
	public String getName() {
		return this.name;		
	}
	
	public int getHp() {
		return this.hp;		
	}	
	
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;		
	}
	
	public int getAccuracy() {
		return this.accuracy;		
	}
	
	public void setRoom(int room) {
		this.inRoom = room;		
	}
	
	// get the number of the room of the player
	public int getRoom() {
		return inRoom;		
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public void look() {
		System.out.println("hp:" + this.hp);
		System.out.println("accuracy:" + this.accuracy);
		// it will also shows the player the player's items
	}
	
	// removing items
	public void remove(String[] x) {
		// loop through carriedItems
		for(int i=0; i < carriedItems.size(); i++) {
			// check to see what the user typed in, example 'remove ring'
			if(carriedItems.get(i).getId().equals(x[1])) {
				// if they matches
				System.out.println("You removed a " + carriedItems.get(i).getId());
				// add it back to the list of items
				items.add(carriedItems.get(i));
				// remove it from the carriedItems list
				carriedItems.remove(i);
			}			
		}
	}
	
	// see equipments
	public void equipments() {
		// loop through all the carriedItems list, and print them and their location
		for(int i=0; i < carriedItems.size(); i++) {
			System.out.println(carriedItems.get(i).getName() + " : " + carriedItems.get(i).wearloc);
		}
	}
	
	
	public void wear(String[] x) {
		if(carriedItems.size() == 0) {
			// loop through all the items in my inventory
			
			for(int i=0; i<items.size();i++) {
				// check to see the word the user typed, like 'wear ring'
				// if that matches with the existed items and also if it is wearable
				if(x[1].equalsIgnoreCase(items.get(i).getId()) && items.get(i).isWearable) {
					// so add the item to the carriedItems list
					carriedItems.add(items.get(i));
					System.out.println("You wore a " + items.get(i).getName());
					items.remove(i);
					break;	
					// now see if the carried items have stuff in it
				}				
			}
		}			
		else {
			// if the carriedItems has stuff in it, set the isWearing to false
			boolean isWearing = false;
			// loop through all the carriedItems, to see what the player is wearing
			// because we don't wanna put two things in the same spot
			for(int i=0; i<carriedItems.size();i++) {
				// loop through all the items in the inventory, to see if what the user typed matches
				for(int z=0; z < items.size(); z++) {
					if(x[1].equalsIgnoreCase(items.get(z).getId()) && items.get(z).isWearable) {
						// check if the item has a wear location that is equal to the carried items wear location
						// cheching every carried items agains the item you wanna wear
						if(items.get(z).wearloc.equals(carriedItems.get(i).wearloc)) {
							// if those two above matches, the player already have some item in that wear location
							System.out.println("You already have something worn in that location.");
							isWearing = true;
						}
						// if you are not wearing anything in that location
						if(!isWearing) {
							carriedItems.add(items.get(i));
							System.out.println("You wore a " + items.get(i).getName());
							items.remove(i);
							break;
						}
					}
				}								
			}
		}
	}
}
