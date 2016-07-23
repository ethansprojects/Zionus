package com.ethansprojects.engine.entity.mob;

import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.entity.Entity;
import com.ethansprojects.engine.entity.mob.player.Player;
import com.ethansprojects.engine.entity.projectile.MagicProjectile;
import com.ethansprojects.engine.entity.projectile.Projectile;
import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * All mobile entities.
 * @author Ethan
 */

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected boolean moving = false;
	
	private int duplicateSecondChecker = 0;
	
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	protected Direction dir;
	
	// If this works I deserve a free hooker.
	public boolean startAttackTimer(int hitDelay, int npcID) {
		boolean delayReached = false;
		delayReached = pingForHitDelay(hitDelay);
		return delayReached;
	}
	
	private boolean pingForHitDelay(int hitDelay) {
		boolean delayReached = false;
		
		if (duplicateSecondChecker != Zionus.combatTimer) {
			if (Zionus.combatTimer % hitDelay == 0) {
				delayReached = true;
				duplicateSecondChecker = Zionus.combatTimer;
			}
		}
		return delayReached;
	}
	
	
	public void move(int xa, int ya, boolean dirOverride) {
		if (xa != 0 && ya != 0) {
			move(xa, 0, dirOverride);
			move(0, ya, dirOverride);
			return;
		}
		
		if (!dirOverride) {
			if (ya < 0) dir = Direction.UP;
			if (ya > 0) dir = Direction.DOWN;
			if (xa < 0) dir = Direction.LEFT;
			if (xa > 0) dir = Direction.RIGHT;
		}
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
		
	}
	
	
	@Override
	public abstract void update() throws InterruptedException;
	
	public int getXProximityToPlayer() {
		Player player = level.getClientPlayer();
		int proximity = Math.abs(player.getX() - x);
		return proximity;
	}
	
	protected void shoot(int x, int y, double dir) {
		//dir *= 180 / Math.PI;
		Projectile p = new MagicProjectile(x, y, dir);
		level.add(p);
		//System.out.println("Angle: " + dir);
		
	}
	
	//IMPROVED 3/14/2014 I AM AMAZING
	public boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 28 - 14) / 16;
			int yt = ((y + ya) + c / 2 * 16 + 15) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public abstract void render(Screen screen);
	
}
