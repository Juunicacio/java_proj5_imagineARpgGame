package imagineARpgGame;

public class Combat {
	
	public void attack(String[] x) {
		// if the user typed just attack
		if(x.length == 1) {
			System.out.println("Attack what exactly?");
		}
		
		// check what that second word is
		if(x.length == 2) {
			for(int i = 0; i < Game_Objects.room.size(); i++) {				
				// if the room is the same as the player
				if(Game_Objects.room.get(i).getNumber() == Game_Objects.player.getRoom()) {					
					// list of npcs
					for(int y = 0; y < Game_Objects.room.get(i).getNpcs().size(); y++) {						
						// if the player wants to attack a monster that is in that room
						if(Game_Objects.room.get(i).getNpcs().get(y).getId().equalsIgnoreCase(x[1])) {
							
							//System.out.println("Do you wanna attack?");
							
							playersAttack(i, y);
							
							npcsAttack(i, y);
							
							if(Game_Objects.room.get(i).getNpcs().get(y).getHp() > 0 && Game_Objects.player.getHp() > 0) {
								System.out.println(" ");
								System.out.println(Game_Objects.room.get(i).getNpcs().get(y).getId() + " is still alive.");
								System.out.println("Do you wanna attack again?");
								System.out.println(" ");
							}
							if(Game_Objects.player.getHp() <= 0) {
								System.out.println("You lost");
								
								// reset game
								Game_Objects.player.setRoom(0);
							}
						}
					}
				}				
			}
		}		
	}
	
	public void playersAttack(int i, int y) {
		System.out.println("________________");
		System.out.println("You are attacking the " + Game_Objects.room.get(i).getNpcs().get(y).getId());
		
		int playerHit = Game_Objects.rr.returnRandomN(100);
		
		//System.out.println("playerHit: "+ playerHit); // 10
		//System.out.println("playerAccuracy: "+ Game_Objects.player.getAccuracy()); // 75
		
		playerHit = playerHit + (Game_Objects.player.getAccuracy() / 2);
		
		//System.out.println("playerHit: "+ playerHit); // 47
		
		if(playerHit > 50) {
			
			int playerDamage = Game_Objects.rr.returnRandomN(10) + 1; // to not return 0 damage points
											
			//System.out.println("playerDamage: " + playerDamage); // 2
			
			// get the npc actual hp and remove the damage points
			int newNPCHp = Game_Objects.room.get(i).getNpcs().get(y).getHp() - playerDamage;
			
			//System.out.println("what shoul be new npc hp: " + newNPCHp);
			
			Game_Objects.room.get(i).getNpcs().get(y).setHp(newNPCHp);
			
			//System.out.println("You hit the " + Game_Objects.room.get(i).getNpcs().get(y).getId() + " for " + playerDamage);
			//System.out.println("The " + Game_Objects.room.get(i).getNpcs().get(y).getId() + "'s Hp now is: " + Game_Objects.room.get(i).getNpcs().get(y).getHp());																
			
											
			String pointWord;
			if(playerDamage == 1) {
				pointWord = "point";
			}
			else {
				pointWord = "points";
			}
		
			System.out.println("You hit " + playerDamage + " " + pointWord + " of damage in the " + Game_Objects.room.get(i).getNpcs().get(y).getId());
			
			if(Game_Objects.room.get(i).getNpcs().get(y).getHp() <= 0) {
				// npc died
				// I need to implement it
				// when implement, pass the npc index and the room index 
				npcDeath(i, y);
			}						
		}
		
		else {
			System.out.println("You missed the " + Game_Objects.room.get(i).getNpcs().get(y).getId());
		}
	}
	
	public void npcsAttack(int i, int y) {
		int npcHit = Game_Objects.rr.returnRandomN(100);
		
		//System.out.println("npcHit: " + npcHit); // 93
		//System.out.println("npcAccuracy: " + Game_Objects.room.get(i).getNpcs().get(y).accuracy); // 20
		
		npcHit = npcHit + (Game_Objects.room.get(i).getNpcs().get(y).accuracy / 2);
		
		//System.out.println("npcHit: " + npcHit); // 103
		System.out.println("________________");
		System.out.println(Game_Objects.room.get(i).getNpcs().get(y).getId() + " is attacking you!");
		
		if(npcHit > 50) { // 103
			int npcDamage = Game_Objects.rr.returnRandomN(10) + 1; // to not return 0 damage points
			
			
			//System.out.println("npcDamage: " + npcDamage); // 9
			
			// get the player actual hp and remove the damage points
			// 100-9
			int newPlayerHp = Game_Objects.player.getHp() - npcDamage;
			
			//System.out.println("newPlayerHp: " + newPlayerHp); // 91
			
			Game_Objects.player.setHp(newPlayerHp);
			
			String pointWord;
			if(npcDamage == 1) {
				pointWord = "point";
			}
			else {
				pointWord = "points";
			}
			
			System.out.println("The " + Game_Objects.room.get(i).getNpcs().get(y).getId() + " hits you " + npcDamage + " " + pointWord + " of damage!");
			System.out.println("You Hp now is: " + Game_Objects.player.getHp());																
			
			
			if(Game_Objects.player.getHp() <= 0) {
				// you died
				// I also need to implement it
				System.out.println("You died!");
			}
		}
		else {
			System.out.println("The " + Game_Objects.room.get(i).getNpcs().get(y).getId() + " missed you.");
		}
	}
	
	public void npcDeath(int roomIndex, int npcIndex) {
		System.out.println("The " + Game_Objects.room.get(roomIndex).getNpcs().get(npcIndex).getId() + " has died");
		// remove the npc from the room list
		Game_Objects.room.get(roomIndex).getNpcs().remove(npcIndex);
	}

}
