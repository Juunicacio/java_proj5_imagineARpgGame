package imagineARpgGame;

public class Sword extends Weapon {	
	public Sword() {
		this.name = "A Sword";
		this.id = "Sword";
		this.desc = "A Sword lies here";
		this.isWearable = true;
		this.wearloc = "weapon";	
		this.damage = 20;
		this.accuracy = 10;
	}	
}
