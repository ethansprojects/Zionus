package com.ethansprojects.features.maps.tile;

import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * A spawn tile.
 * @author ethansprojects
 */

public class SpawnTile extends Tile {
	
	public SpawnTile(Sprite sprite) {
		super(sprite);
		super.spawnPortal = true;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
		
	}
	
}
