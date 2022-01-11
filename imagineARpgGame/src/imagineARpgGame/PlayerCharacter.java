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
	
	public void setName(String name) {
		this.name = name;		
	}
	
	public String getName() {
		return this.name;		
	}
	
	public void setHp(int hpPoints) {
		this.hp = hpPoints;		
	}
	
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;		
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
}
