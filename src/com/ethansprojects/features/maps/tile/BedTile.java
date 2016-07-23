package com.ethansprojects.features.maps.tile;

import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * A bed tile. (may change to object)
 * @author ethansprojects
 */

public class BedTile extends Tile {
	
	public BedTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public void render(int x, int y, Screen screen) {
		System.out.println("hi");
		screen.renderTile(x << 4, y << 4, sprite);
	}
	
}
