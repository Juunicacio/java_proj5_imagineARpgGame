package imagineARpgGame;

public class Troll extends NPC{	
	public Troll() {
		this.name = "A Troll";
		this.id = "Troll";
		this.desc = "A Troll stands here";
		this.hp = 50;
		this.accuracy = 20;		
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
}
