package com.ethansprojects.engine.entity;

import com.ethansprojects.engine.graphics.Screen;
import java.util.Random;
import com.ethansprojects.features.maps.Level;

/**
 * Configures all entities.
 * @author ethansprojects
 */

public class Entity {
	
	public int x;
	public int y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() throws InterruptedException {
	}
	
	public void render(Screen screen) {
	}
	
	public void remove() {
		// Remove from level
		removed = true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
}
