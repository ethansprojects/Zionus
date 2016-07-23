package com.ethansprojects.engine.entity.spawner;

import com.ethansprojects.engine.entity.Entity;
import com.ethansprojects.features.maps.Level;

/**
 * An entity spawner.
 * @author ethansprojects
 */

public abstract class Spawner extends Entity {
	
	public enum Type {
		MOB, PARTICLE;
	}
	
	Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		
	}
	
}
