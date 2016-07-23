package com.ethansprojects.engine.graphics;

import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.physics.input.Mouse;

/**
 * Renders the HUD.
 * @author ethansprojects
 */

public class Hud {
	
	public static void renderHUD(Screen screen) {
		
		boolean defaultHUD = true;
		if (defaultHUD) screen.renderSprite(638, 2, Sprite.hud, false);
		else screen.renderSprite(0, 0, Sprite.hud_second, false);
		
		if (Zionus.ACT == 0) {
			
			screen.renderSprite(701, 21, Sprite.minimap_prologue, false);
		}
		
		// Code below determines if the player's mouse is hovering over the HUD; if so, change the cursor to the hover cursor
		Zionus.cursorType = 0;
		if (defaultHUD && Mouse.getX() >= 640 * Zionus.scale && Mouse.getX() <= 842 * Zionus.scale) {
			if (Mouse.getY() >= 165 * Zionus.scale && Mouse.getY() <= 199 * Zionus.scale) Zionus.cursorType = 1; // First sidebox
				
			if (Mouse.getY() >= 208 * Zionus.scale && Mouse.getY() <= 241 * Zionus.scale) Zionus.cursorType = 1; // Second sidebox
				
			if (Mouse.getY() >= 253 * Zionus.scale && Mouse.getY() <= 286 * Zionus.scale) Zionus.cursorType = 1; //Third sidebox
				
			if (Mouse.getX() >= 665 * Zionus.scale && Mouse.getY() >= 118 * Zionus.scale && Mouse.getY() <= 460 * Zionus.scale) Zionus.cursorType = 1;
			if (Mouse.getX() >= 687 * Zionus.scale && Mouse.getX() <= 820 * Zionus.scale && Mouse.getY() >= 4 * Zionus.scale && Mouse.getY() <= 460 * Zionus.scale) Zionus.cursorType = 1;
			
		}
		
	}
	
}