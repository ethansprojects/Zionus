package com.ethansprojects.features.maps.tile;

import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * A red flower tile.
 * @author ethansprojects
 */

public class RedFlowerTile extends Tile {
	
	public RedFlowerTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
	}
	
}
