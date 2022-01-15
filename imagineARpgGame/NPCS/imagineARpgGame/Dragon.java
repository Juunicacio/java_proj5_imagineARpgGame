package imagineARpgGame;

public class Dragon extends NPC {	
	public Dragon() {
		this.name = "A Dragon";
		this.id = "Dragon";
		this.desc = "A Dragon stands here";
		this.hp = 100;
		this.accuracy = 50;	
		this.baseDamage = 3;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
}
