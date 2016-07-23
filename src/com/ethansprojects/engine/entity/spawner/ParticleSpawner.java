package com.ethansprojects.engine.entity.spawner;

import com.ethansprojects.engine.entity.particle.Particle;
import com.ethansprojects.features.maps.Level;

/**
 * Spawns particles.
 * @author ethansprojects
 */

public class ParticleSpawner extends Spawner {
	
	int duration = 0;
	
	public ParticleSpawner(int x, int y, int duration, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.duration = duration;
		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, duration));
		}
	}
	
}
