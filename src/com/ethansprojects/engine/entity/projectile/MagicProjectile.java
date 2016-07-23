package com.ethansprojects.engine.entity.projectile;

import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.entity.spawner.ParticleSpawner;
import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;
import com.ethansprojects.engine.audio.AudioPlayer;

/**
 * A magic projectile entity.
 * @author ethansprojects
 */

public class MagicProjectile extends Projectile {
	
	public static final int FREQUENCY = 8; // Time in between each projectile shot
	
	public static boolean projectileHitMob = false;
	
	private AudioPlayer impactNoise = new AudioPlayer("/audio/sfx/projectiles/impact0.wav");
	
	public MagicProjectile(int x, int y, double dir) {
		super(x, y, dir);
		speed = 8;
		range = random.nextInt(100) + 275;
		damage = 20;
		sprite = Sprite.magic_ball;
		newx = speed * Math.cos(angle);
		newy = speed * Math.sin(angle);
	}
	
	public void update() {
		if (level.tileCollision((int) (x + newx), (int) (y + newy), 14, 1, 1)) {
			
			if (Zionus.SFX) {
				impactNoise.play(false);
			}
			
			level.add(new ParticleSpawner((int) x, (int) y, 60, 30, level));
			remove();
		}
		
		if (projectileHitMob) {
			System.out.println("Hits the NPC.");
		}
		move();
	}
	
	protected void move() {
		x += newx;
		y += newy;
		if (distance() > range) remove(); //Removes projectiles after they exceed their range (prevents major portential performance issues)
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xCenter - x) * (xCenter - x) + (yCenter - y) * (yCenter - y)));
		return dist;
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int) x - 12, (int) y - 15, this);
	}
	
}
