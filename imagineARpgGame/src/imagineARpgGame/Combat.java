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
							
							if(Game_Objects.room.get(i).getNpcs().get(y).getHp() <= 0) {
								// npc died
								npcDeath(i, y);
								
								// give the player coins
								// put that the coins are random 
								Game_Objects.playerWallet.addSilver(ReturnRandom.returnRandomN(15));
								
								// give the player experience
								Game_Objects.player.addExperience(500);
								
								System.out.println("Player has: " + Game_Objects.playerWallet.getGold() + "-gold " + Game_Objects.playerWallet.getSilver() + "-silver ");
								System.out.println("Your level is: " + Game_Objects.player.getPlayerLvl());
								
								
							}else {
								npcsAttack(i, y);
								if(Game_Objects.room.get(i).getNpcs().get(y).getHp() > 0 && Game_Objects.player.getHp() > 0) {
									System.out.println(" ");
									System.out.println(Game_Objects.room.get(i).getNpcs().get(y).getId() + " is still alive.");
									System.out.println("Do you wanna attack again?");
									System.out.println(" ");
								}
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
		
		int playerHit = ReturnRandom.returnRandomN(100);		
		playerHit = playerHit + (Game_Objects.player.getAccuracy() / 2);
		
		if(playerHit > 50) {
			
			//System.out.println("how many items the player is wearing: " + Game_Objects.player.getCarriedItems().size());
			
			int damageOfWeapons = 0;
			if(Game_Objects.player.getCarriedItems().isEmpty() == false) {
				for(int z=0; z < Game_Objects.player.getCarriedItems().size(); z++) {
					//System.out.println("See name of items the player has: " + Game_Objects.player.getCarriedItems().get(z).getId()); // sword
					// Cast
					Item item = Game_Objects.player.getCarriedItems().get(z);
					
					if(item.getWearloc() == "weapon") {
						Weapon weapon = (Weapon) item;
						//System.out.println("Weapon damage: " + weapon.getDamage());
						damageOfWeapons = weapon.getDamage();
					}
				}
			}
			
			// if the player has a weapon, the damage of the weapon will be add to the damage
			int playerDamage = ReturnRandom.returnRandomN(10) + 1 + Game_Objects.player.getBaseDamage() + damageOfWeapons; // + 1 to not return 0 damage points
			
			// get the npc actual hp and remove the damage points
			int newNPCHp = Game_Objects.room.get(i).getNpcs().get(y).getHp() - playerDamage;			
			Game_Objects.room.get(i).getNpcs().get(y).setHp(newNPCHp);
			
			String pointWord;
			if(playerDamage == 1) {
				pointWord = "point";
			}
			else {
				pointWord = "points";
			}
		
			System.out.println("You hit " + playerDamage + " " + pointWord + " of damage in the " + Game_Objects.room.get(i).getNpcs().get(y).getId());
			
			/*
			if(Game_Objects.room.get(i).getNpcs().get(y).getHp() <= 0) {
				// npc died
				npcDeath(i, y);
			}	*/					
		}
		
		else {
			System.out.println("You missed the " + Game_Objects.room.get(i).getNpcs().get(y).getId());
		}
	}
	
	public void npcsAttack(int i, int y) { // 1, 0 for example
		int npcHit = ReturnRandom.returnRandomN(100);
		
		//System.out.println("npcHit: " + npcHit); // 93
		//System.out.println("npcAccuracy: " + Game_Objects.room.get(i).getNpcs().get(y).accuracy); // 20
		
		npcHit = npcHit + (Game_Objects.room.get(i).getNpcs().get(y).accuracy / 2);
		
		//System.out.println("npcHit: " + npcHit); // 103
		System.out.println("________________");
		System.out.println(Game_Objects.room.get(i).getNpcs().get(y).getId() + " is attacking you!");
		
		if(npcHit > 50) { // 103
			int npcDamage = ReturnRandom.returnRandomN(10) + 1 + Game_Objects.room.get(i).getNpcs().get(y).getBaseDamage(); // to not return 0 damage points
			
			
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
