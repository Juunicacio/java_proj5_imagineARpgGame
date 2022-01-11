package imagineARpgGame;

public class Axe extends Item{
	private int accuracy = 10;
	private int damage = 30;
	
	public Axe() {
		this.name = "A Axe";
		this.id = "Axe";
		this.desc = "A Axe lies here";
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
