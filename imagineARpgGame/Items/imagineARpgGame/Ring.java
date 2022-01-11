package imagineARpgGame;

public class Ring extends Item {
	
	public Ring() {
		this.name = "A Ring";
		this.id = "Ring";
		this.desc = "A Ring lies here";
		this.isWearable = true;
		this.wearloc = "finger";		
	}		
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

}
