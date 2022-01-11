package imagineARpgGame;

public class NPC {
	protected String name;
	protected String id = "NPC";
	// what desc about this NPC
	protected String desc;
	protected int hp;
	protected int accuracy;
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}	
	
	public void look() {
		System.out.println(this.name);
		System.out.println("hp:" + this.hp);
		System.out.println("accuracy:" + this.accuracy);
		// it will also shows the player the player's items
	}

}

