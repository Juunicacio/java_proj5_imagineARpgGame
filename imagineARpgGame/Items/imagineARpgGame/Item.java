package imagineARpgGame;

public class Item {
	protected String name;
	protected String id = "Item";
	// desc for Item the player will see
	protected String desc;
	protected boolean isWearable;
	protected String wearloc;	
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getWearloc() {
		return this.wearloc;
	}
	

}
