package com.ethansprojects.features.maps;

import java.util.Random;

/**
 * Loads a random level.
 * @author ethansprojects
 */

public class RandomLevel extends Level {
	
	private static final Random random = new Random();
	
	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	@Override
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(4);
			}
		}
	}
	
}
