package com.ethansprojects.features.items;

import com.ethansprojects.engine.graphics.Screen;
import java.io.FileNotFoundException;

/**
 * Configures and renders items.
 * @author ethansprojects
 */

public class Item {
	
	public static int seconds = 0;
	
	public static String[] itemList;
	
	//	public Item(String name, String desc, int value, String spriteName, boolean stackable, boolean equippable, int reqCbt, int attBonus, int defBonus, int rangeBonus, int magBonus, int speedBonus) {
	//		name = Search.getItemDetail(, detailType)
	//	}
	
	public static void spawnOnGround(int itemID, int amount, int x, int y, Screen screen) {
		
		for (int i = 0; i < amount; i++) {
			
			render(itemID, x - (i * 10), y + (i * 10), screen);
			
			try {
				System.out.println("Spawned " + (i + 1) + "/" + amount + " " + Search.getItemDetail(itemID, 12).toLowerCase() + "s.");
			} catch (FileNotFoundException e) {
				System.err.println("Item data is corrupted or could not be found.");
				e.printStackTrace();
			}
		}
		
	}
	
	private static void render(int itemID, int x, int y, Screen screen) {
		
		try {
			// Search.getItemDetail(itemID, 2) uses the entered item ID to find the sprite name to render.
			screen.renderItemSprite(x, y, Search.getItemDetail(itemID, 2), true);
		} catch (FileNotFoundException e) {
			System.err.println("Item data is corrupted or could not be found.");
			e.printStackTrace();
		}
	}
	
}
