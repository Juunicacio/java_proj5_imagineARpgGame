package imagineARpgGame;

public class Axe extends Weapon {	
	public Axe() {
		this.name = "A Axe";
		this.id = "Axe";
		this.desc = "A Axe lies here";
		this.isWearable = true;
		this.wearloc = "weapon";
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getAccuracy() {
		return this.accuracy;
	}
	
	

}
