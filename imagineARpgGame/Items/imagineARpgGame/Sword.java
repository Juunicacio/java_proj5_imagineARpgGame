package imagineARpgGame;

public class Sword extends Item {
	private int accuracy = 10;
	private int damage = 20;
	
	public Sword() {
		this.name = "A Sword";
		this.id = "Sword";
		this.desc = "A Sword lies here";
		this.isWearable = true;
		this.wearloc = "wield";		
	}		
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
