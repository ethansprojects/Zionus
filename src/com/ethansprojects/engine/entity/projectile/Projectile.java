package com.ethansprojects.engine.entity.projectile;

import com.ethansprojects.engine.entity.Entity;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * A general projectile entity.
 * @author ethansprojects
 */

public class Projectile extends Entity {
	
	protected final int xCenter, yCenter;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double newx, newy;
	protected double distance;
	protected double speed, range, damage;
	
	public Projectile(int x, int y, double dir) {
		xCenter = x;
		yCenter = y;
		angle = dir;
		this.x = x;
		this.y = y;
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	
	protected void move() {
		
	}
	
}
