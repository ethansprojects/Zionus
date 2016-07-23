package com.ethansprojects.features.items;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Finds data indexes.
 * @ethansprojects
 */

public class Search {
	
	private static File itemData = new File("res/data/item.dat");
	
	public static String getItemDetail(int enteredID, int detailType) throws FileNotFoundException {
		
		Scanner in = new Scanner(itemData);
		
		String answer = "";
		
		String line = "";
		
		// This was way harder than it needed to be.
		while (in.hasNextLine()) {
			line = in.nextLine();
			// [ID] Name ~ Description. (Money value/Sprite name for icon/Stackable? (y/n)/Equippable? (y/n)/Required combat/Attack bonus/Power bonus/Defense bonus/Range bonus/Magic bonus/Speed bonus)
			// (use -1 if any of the item stats are not applicable. ex: an untradable item would have a money value of -1)
			
			int secondSlash = (line.indexOf('/') + 1) + (line.substring(line.indexOf('/') + 1)).indexOf('/'); // I AM GOD.
			int thirdSlash = (secondSlash + 1) + (line.substring(secondSlash + 1)).indexOf('/'); // Yep, still a fucking genius.
			int fourthSlash = (thirdSlash + 1) + (line.substring(thirdSlash + 1)).indexOf('/'); // I bet you're so fucking confused. Little did you know this is amazing code.
			int fifthSlash = (fourthSlash + 1) + (line.substring(fourthSlash + 1)).indexOf('/');
			int sixthSlash = (fifthSlash + 1) + (line.substring(fifthSlash + 1)).indexOf('/');
			int seventhSlash = (sixthSlash + 1) + (line.substring(sixthSlash + 1)).indexOf('/');
			int eighthSlash = (seventhSlash + 1) + (line.substring(seventhSlash + 1)).indexOf('/');
			int ninthSlash = (eighthSlash + 1) + (line.substring(eighthSlash + 1)).indexOf('/');
			int tenthSlash = (ninthSlash + 1) + (line.substring(ninthSlash + 1)).indexOf('/');
			
			String itemID = line.substring(1, line.indexOf(']'));
			String itemName = line.substring(line.indexOf(']') + 2, line.indexOf('~') - 1);
			String itemDesc = line.substring(line.indexOf('~') + 2, line.indexOf('.') + 1);
			String itemValue = line.substring(line.indexOf('(') + 1, line.indexOf('/'));
			String spriteName = line.substring(line.indexOf('/') + 1, secondSlash);
			String stackable = line.substring(secondSlash + 1, thirdSlash);
			String equippable = line.substring(thirdSlash + 1, fourthSlash);
			String reqCombat = line.substring(fourthSlash + 1, fifthSlash);
			String attBonus = line.substring(fifthSlash + 1, sixthSlash);
			String powBonus = line.substring(sixthSlash + 1, seventhSlash);
			String defBonus = line.substring(seventhSlash + 1, eighthSlash);
			String rangeBonus = line.substring(eighthSlash + 1, ninthSlash);
			String magBonus = line.substring(ninthSlash + 1, tenthSlash);
			String speedBonus = line.substring(tenthSlash + 1, line.length() - 1);
			
			if (enteredID == Integer.parseInt(itemID)) {
				
				switch (detailType) {
					case 1: // Money value
						answer = itemValue;
						break;
					case 2: // Sprite name for icon
						answer = spriteName;
						break;
					case 3: // Stackable?
						answer = stackable;
						break;
					case 4: // Equippable?
						answer = equippable;
						break;
					case 5: // Required combat
						answer = reqCombat;
						break;
					case 6: // Attack bonus
						answer = attBonus;
						break;
					case 7: // Power bonus
						answer = powBonus;
						break;
					case 8: // Defense bonus
						answer = defBonus;
						break;
					case 9: // Range bonus
						answer = rangeBonus;
						break;
					case 10: // Magic bonus
						answer = magBonus;
						break;
					case 11: // Speed bonus
						answer = speedBonus;
						break;
					case 12: // Item name
						answer = itemName;
						break;
					case 13: // Item description
						answer = itemDesc;
						break;
				}
				
			}
		}
		
		in.close();
		return answer;
	}
	
}
