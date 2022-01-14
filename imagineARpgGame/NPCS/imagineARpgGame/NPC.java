package imagineARpgGame;

public class NPC {
	protected String name;
	protected String id = "NPC";
	// what desc about this NPC
	protected String desc;
	protected int hp;
	protected int accuracy;
	protected int baseDamage;
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getBaseDamage() {
		return this.baseDamage;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void look() {
		System.out.println(this.name);
		System.out.println("hp:" + this.hp);
		System.out.println("accuracy:" + this.accuracy);
		// it will also shows the player the player's items
	}

}

