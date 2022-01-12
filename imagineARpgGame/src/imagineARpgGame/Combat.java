package imagineARpgGame;

public class Combat {
	
	public void attack(String[] x) {
		for(int i=0; i<Game_Objects.room.size(); i++) {
			// if the room is the same as the player
			if(Game_Objects.room.get(i).getNumber() == Game_Objects.player.getRoom()) {
				// list of npcs
				for(int y=0; y<Game_Objects.room.get(i).getNpcs().size(); y++) {
					// if the player wants to attack a monster that is in that room
					if(Game_Objects.room.get(i).getNpcs().get(y).getId().equalsIgnoreCase(x[1])) {
						
						int npcHit = Game_Objects.rr.returnRandomN(100);
						npcHit = npcHit + (Game_Objects.room.get(i).getNpcs().get(y).accuracy / 2);
						
						if(npcHit > 50) {
							int npcDamage = Game_Objects.rr.returnRandomN(10);
							// get the player actual hp and remove the damage points
							int newPlayerHp = Game_Objects.player.getHp() - npcDamage;
							Game_Objects.player.setHp(newPlayerHp);
							System.out.println("The " + Game_Objects.room.get(i).getNpcs().get(y).getName() + " hits you for " + npcDamage);	
							
							if(Game_Objects.player.getHp() <= 0) {
								// you died
								// I also need to implement it
							}
						}
						else {
							System.out.println("The " + Game_Objects.room.get(i).getNpcs().get(y).getName() + " missed you.");
						}
						
						int playerHit = Game_Objects.rr.returnRandomN(100);
						playerHit = playerHit + (Game_Objects.player.getAccuracy() / 2);
						
						if(playerHit > 50) {
							int playerDamage = Game_Objects.rr.returnRandomN(10);
							// get the npc actual hp and remove the damage points
							int newNPCHp = Game_Objects.room.get(i).getNpcs().get(y).getHp() - playerDamage;
							Game_Objects.room.get(i).getNpcs().get(i).setHp(newNPCHp);
							
							String pointWord;
							if(playerDamage == 1) {
								pointWord = "point";
							}
							else {
								pointWord = "points";
							}
							
							System.out.println("You hit " + playerDamage + pointWord + " of damage in " + Game_Objects.room.get(i).getNpcs().get(y).getName());
							
							if(Game_Objects.room.get(i).getNpcs().get(y).getHp() <= 0) {
								// npc died
								// I need to implement it
								// when implement, pass the npc index and the room index 
								npcDeath(i, y);
							}
							
						}
						else {
							System.out.println("You missed");
						}
					}
				}
			}
				
		}
	}
	
	public void npcDeath(int roomIndex, int npcIndex) {
		System.out.println("A " + Game_Objects.room.get(roomIndex).getNpcs().get(npcIndex).getName() + " has died");
		// remove the npc from the room list
		Game_Objects.room.get(roomIndex).getNpcs().remove(npcIndex);
	}

}
