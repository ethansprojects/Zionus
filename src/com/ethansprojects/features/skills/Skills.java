package com.ethansprojects.features.skills;

import com.ethansprojects.engine.Zionus;
import com.ethansprojects.features.data.CharacterSaving;

/**
 * Defines and configures skills.
 * @author ethansprojects
 */

public class Skills {
	
	public void giveXP(int givenXP, int skillID) {
		int currentXP = 0;
		
		// 
		switch (skillID) {
			case 0: // Health
				currentXP = CharacterSaving.healthXP;
				break;
			case 1: // Attack
				currentXP = CharacterSaving.attackXP;
				break;
			case 2: // Power
				currentXP = CharacterSaving.strengthXP;
				break;
			case 3: // Defense
				currentXP = CharacterSaving.defenseXP;
				break;
			case 4: // Ranged
				currentXP = CharacterSaving.rangedXP;
				break;
			case 5: // Magic
				currentXP = CharacterSaving.magicXP;
				break;
			case 6:
				break;
		}
		
		if (givenXP + currentXP > 100000) {
			
		}
	}
	
	public void levelUp(int skillID) {
		int level = 0;
		
		CharacterSaving.total_level++;
		
		switch (skillID) {
			case 0: // Health
				CharacterSaving.maxHealth++;
				level = CharacterSaving.maxHealth;
				break;
			case 1: // Attack
				CharacterSaving.attack++;
				level = CharacterSaving.attack;
				break;
			case 2: // Strength
				CharacterSaving.strength++;
				level = CharacterSaving.strength;
				break;
			case 3: // Defense
				CharacterSaving.defense++;
				level = CharacterSaving.defense;
				break;
			case 4: // Ranged
				CharacterSaving.ranged++;
				level = CharacterSaving.ranged;
				break;
			case 5: // Magic
				CharacterSaving.magic++;
				level = CharacterSaving.magic;
				break;
		}
		
		Zionus.player.sendMessage = "Congratulations! Your " + IDtoName(skillID) + " is now level " + level + "!";
		
	}
	
	public String IDtoName(int skillID) {
		
		switch (skillID) {
			case 0:
				return "Health";
			case 1:
				return "Attack";
			case 2:
				return "Strength";
			case 3:
				return "Defense";
			case 4:
				return "Ranged";
			case 5:
				return "Magic";
		}
		
		return null;
		
	}
	
}
