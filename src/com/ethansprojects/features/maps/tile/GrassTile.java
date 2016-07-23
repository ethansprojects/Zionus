package com.ethansprojects.features.maps.tile;

import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * A grass tile.
 * @author ethansprojects
 */

public class GrassTile extends Tile {
	
	public GrassTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
		
	}
	
}
