package imagineARpgGame;

public class Wallet {
	private String name;
	private String id = "Wallet";	
	private int gold;
	private int silver;
	private final int goldSilverRatio = 10;
	
	public Wallet() {
		gold = 0;
		silver =0;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public int getSilver() {
		return this.silver;
	}
	
	public void addGold(int value) {
		gold = gold + value;
	}
	
	public void addSilver(int value) {
		silver = silver + value;
		if(silver > goldSilverRatio) {
			silver = silver - goldSilverRatio;
			addGold(1);
		}
	}
	
}
