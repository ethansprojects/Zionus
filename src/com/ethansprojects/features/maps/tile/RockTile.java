package com.ethansprojects.features.maps.tile;

import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * A rock tile.
 * @author ethansprojects
 */

public class RockTile extends Tile {
	
	public RockTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
	}
	
	//Makes rocks a collidable tile
	public boolean solid() {
		return true;
	}
	
}