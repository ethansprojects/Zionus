package com.ethansprojects.engine.entity.mob.npc;

import com.ethansprojects.features.debug.Debug;
import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;
import java.util.Random;
import com.ethansprojects.features.debug.Box;
import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.entity.mob.Mob;
import com.ethansprojects.engine.entity.mob.player.Player;

/**
 * A demon entity.
 * @author ethansprojects
 */

public class Demon extends Mob {
	
	public static int npcID = 2;
	private int memoryID;
	
	private int anim = 0;
	
	private int xa = 0;
	private int ya = 0;
	private boolean dirOverride = false;
	
	private int randomWalkTime = 0;
	private int randomWalkTimer = 0;
	
	private Box firstDemonRoom;
	
	public static boolean talking = false;
	private static int healthPercent = 100;
	
	private int targetNpcID = -1;
	
	private static int attackSpeed = 5;
	private static int power = 30;
	public static int defense = 15;
	
	private boolean[] attackingPlayer = new boolean[100];
	//	private boolean[] attackingNPC = new boolean[100];
	private boolean swinging = false;
	
	private boolean walkingRandomly = false;
	private boolean outOfBox = false;
	
	private Random random = new Random();
	
	public Demon(int x, int y, int memoryID) {
		this.x = x << 4;
		this.y = y << 4;
		this.memoryID = memoryID;
		dir = Direction.DOWN;
		System.out.println("new demon");
		//		697, 1303
		//		48, 48, 1200, 192
		//		box = new Box(p.getX() - ((proximity + xOffset) / 2),	p.getY() - ((proximity + yOffset) / 2), proximity + xOffset, proximity + yOffset);
	}
	
	public void update() {
		prologueActions();
		animationClock();
		walk();
	}
	
	private void animationClock() {
		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}
	}
	
	private void walk() {
		if (!swinging) {
			if (xa != 0 || ya != 0) {
				move(xa, ya, dirOverride);
				moving = true;
			} else {
				moving = false;
			}
			
			if (!walkingRandomly) {
				xa = ya = 0;
			}
		}
	}
	
	public void walkRandomly(double speed, Box boundaries) {
		if (randomWalkTimer > 0) {
			randomWalkTimer--;
		}
		if (randomWalkTime < 7500) {
			randomWalkTime++;
		} else {
			randomWalkTime = 0;
		}
		if (randomWalkTimer <= 0) {
			if (randomWalkTime % (random.nextInt(100) + 10) == 0) {
				xa = (random.nextInt(3) - 1);
				ya = (random.nextInt(3) - 1);
				
				if (xa != 0 || ya != 0) {
					walkingRandomly = true;
				}
			}
			if (randomWalkTime % (random.nextInt(100) + 200) == 0) {
				xa = ya = 0;
				randomWalkTime = (random.nextInt(5) + 1) * 60;
			}
		}
		
		if (boundaries != null) {
			stayWithinBox(speed, boundaries);
		} else {
			if (randomWalkTime % 200 == 0) {
				move(xa, ya, dirOverride);
			}
		}
	}
	
	private void stayWithinBox(double speed, Box boundaries) {
		if ((x + xa) > (boundaries.x + boundaries.width)) {
			xa = (int) -speed;
		} else if ((x + xa) < boundaries.x) {
			xa = (int) speed;
		} else if ((y + ya) > (boundaries.y + boundaries.height)) {
			ya = (int) -speed;
		} else if ((y + ya) < boundaries.y) {
			ya = (int) speed;
		}
		
		if (walkingRandomly) {
			if (randomWalkTime % 200 == 0) {
				move(xa, ya, dirOverride);
			}
		} else {
			move(xa, ya, dirOverride);
		}
	}
	
	private boolean predictIfOutOfBox(Box boundaries) {
		
		if ((x + xa) >= (boundaries.x + boundaries.width) - 10) {
			outOfBox = true;
		} else if ((x + xa) <= boundaries.x + 10) {
			outOfBox = true;
		} else if ((y + ya) >= (boundaries.y + boundaries.height) - 10) {
			outOfBox = true;
		} else if ((y + ya) <= boundaries.y + 10) {
			outOfBox = true;
		} else {
			outOfBox = false;
		}
		
		return outOfBox;
	}
	
	private void prologueActions() {
		switch (Zionus.ACT_stage) {
			case 14:
				// use ignite and walk toward player
				attackPlayer(0);
				if (getXProximityToPlayer() <= 250) {
					Zionus.ACT_stage = 15;
				}
				break;
			
			case 15:
				attackPlayer(0);
				break;
			case 16:
				attackPlayer(0);
				break;
		}
	}
	
	private void chasePlayer(int proximity, double speed) {
		Player p = level.getClientPlayer();
		firstDemonRoom = new Box(70, 48, 1100, 192);
		facePlayer();
		if (p.getX() < (x + proximity) && p.getX() > (x - proximity)) {
			GTFOplayer(speed);
		} else {
			if (predictIfOutOfBox(firstDemonRoom)) {
				stayWithinBox(speed, firstDemonRoom);
			} else {
				followPlayer(proximity, speed);
			}
		}
		//		if (collision(xa, ya) && (p.getX() < (x + proximity) || (p.getX() > (x - proximity)))) {
		//			demonIsStuck = true;
		//			getUnstuck(proximity, speed);
		//		}
		
	}
	
	private void facePlayer() {
		Player p = level.getClientPlayer();
		
		if (swinging) {
			dirOverride = true;
			if (p.getX() > x) {
				dir = Direction.RIGHT;
			} else if (p.getX() < x) {
				dir = Direction.LEFT;
			}
		} else {
			dirOverride = false;
			if (p.getY() > y) {
				dir = Direction.DOWN;
			} else if (p.getY() < y) {
				dir = Direction.UP;
			}
		}
		
	}
	
	private void GTFOplayer(double speed) {
		Player p = level.getClientPlayer();
		
		if (p.getX() >= x) {
			xa = (int) -speed;
		}
		if (p.getX() < x) {
			xa = (int) speed;
		}
	}
	
	private void followPlayer(int proximity, double speed) {
		Player p = level.getClientPlayer();
		if (p.getX() - proximity > x) {
			xa = (int) speed;
		}
		if (p.getX() + proximity < x) {
			xa = (int) -speed;
		}
		if (p.getY() > y) {
			ya = (int) speed;
		}
		if (p.getY() < y) {
			ya = (int) -speed;
		}
	}
	
	private void attackPlayer(int attackerID) {
		attackingPlayer[attackerID] = true;
		targetNpcID = 0;
		chasePlayer(50, 1);
		attemptHit();
	}
	
	//	private void attackNPC(int attackerID, int targetMemoryID, int targetNpcID) {
	//		attackingNPC[attackerID] = true;
	//		int proximityToNPC = 0;
	////		chaseNPC(45, 1);
	////		if (getXProximityToNPC < 80) {
	//			determineHits(attackerID, proximityToNPC, targetNpcID);
	////		}
	//		
	//		switch (targetMemoryID) {
	//			// Affect specific target memory ID's health
	//		}
	//		
	//	}
	
	private void attemptHit() {
		if (getXProximityToPlayer() < 100) {
			if (startAttackTimer(attackSpeed, npcID)) {
				swinging = true;
			}
		}
	}
	
	private int determineHits(int enemyProximity) {
		int min = 5;
		int max = enemyProximity;
		if (max < min) {
			max = min + 1;
		}
		int scale = (random.nextInt(max - min + 1) + min);
		int targetDefense = 0;
		
		switch (targetNpcID) {
			case 0: // Player
				targetDefense = (Player.defense + Player.defBonus) / 4;
				break;
			case 1: // Nephyr
				targetDefense = (Nephyr.defense) / 4;
				break;
			case 2: // Demon
				break;
		}
		if (targetDefense < 4) {
			targetDefense = 4;
		}
		
		int damage = (int) ((power + power) / targetDefense);
		int result = (int) (damage / (scale));
		
		System.out.println("Demon hits " + result + " on enemy.");
		return result;
	}
	
	@Override
	public void render(Screen screen) {
		renderAttackAnimations(screen, Zionus.player);
		renderWalkAnimations(screen);
		renderHealthBar(screen);
		boolean drawRect = true;
		if (drawRect && firstDemonRoom != null) {
			Debug.drawRect(firstDemonRoom.x, firstDemonRoom.y, firstDemonRoom.width, firstDemonRoom.height, true, screen);
		}
		
	}
	
	private void renderAttackAnimations(Screen screen, Player p) {
		int xx = x - 32;
		int yy = y - 32;
		
		if (swinging) {
			
			if (dir == Direction.RIGHT) {
				
				switch (Zionus.demonTimer) {
					case 1:
						screen.renderSprite(xx, yy, Sprite.demon_right_attack1, true);
						break;
					case 2:
						screen.renderSprite(xx, yy, Sprite.demon_right_attack2, true);
						break;
					case 3:
						screen.renderSprite(xx, yy, Sprite.demon_right_attack3, true);
						break;
					case 4:
						screen.renderSprite(xx, yy, Sprite.demon_right_attack4, true);
						break;
					case 5:
						screen.renderSprite(xx, yy, Sprite.demon_right_attack4, true);
						switch (targetNpcID) {
							// Player
							case 0:
								p.takeDamage(determineHits(getXProximityToPlayer()));
								break;
						}
						swinging = false;
						break;
					default:
						Zionus.demonTimer = 1;
						screen.renderSprite(xx, yy, Sprite.demon_right, true);
						break;
				}
				
			} else if (dir == Direction.LEFT) {
				
				switch (Zionus.demonTimer) {
					case 1:
						screen.renderSprite(xx, yy, Sprite.demon_left_attack1, true);
						break;
					case 2:
						screen.renderSprite(xx, yy, Sprite.demon_left_attack2, true);
						break;
					case 3:
						screen.renderSprite(xx, yy, Sprite.demon_left_attack3, true);
						break;
					case 4:
						screen.renderSprite(xx, yy, Sprite.demon_left_attack4, true);
						switch (targetNpcID) {
							// Player
							case 0:
								p.takeDamage(determineHits(getXProximityToPlayer()));
								break;
						}
						swinging = false;
						break;
					
					default:
						Zionus.demonTimer = 1;
						screen.renderSprite(xx, yy, Sprite.demon_left, true);
						break;
				}
				
			}
			
		}
	}
	
	private void renderWalkAnimations(Screen screen) {
		int xx = x - 32;
		int yy = y - 32;
		if (!swinging) {
			
			if (dir == Direction.UP) {
				if (moving) {
					if (anim % 20 > 10) {
						screen.renderSprite(xx, yy, Sprite.demon_upsway1, true);
					} else {
						screen.renderSprite(xx, yy, Sprite.demon_upsway2, true);
					}
				} else {
					screen.renderSprite(xx, yy, Sprite.demon_up, true);
				}
			}
			if (dir == Direction.RIGHT) {
				if (moving) {
					if (anim % 20 > 10) {
						screen.renderSprite(xx, yy, Sprite.demon_rightsway1, true);
					} else {
						screen.renderSprite(xx, yy, Sprite.demon_rightsway2, true);
					}
				} else {
					screen.renderSprite(xx, yy, Sprite.demon_right, true);
				}
			}
			if (dir == Direction.DOWN) {
				if (moving) {
					if (anim % 20 > 10) {
						screen.renderSprite(xx, yy, Sprite.demon_downsway1, true);
					} else {
						screen.renderSprite(xx, yy, Sprite.demon_downsway2, true);
					}
				} else {
					if (anim % 1000 < 200) {
						if (anim % 100 > 50) {
							screen.renderSprite(xx, yy, Sprite.demon_down_ignite2, true);
						} else {
							screen.renderSprite(xx, yy, Sprite.demon_down_ignite1, true);
						}
					} else {
						screen.renderSprite(xx, yy, Sprite.demon_down, true);
					}
				}
			}
			if (dir == Direction.LEFT) {
				if (moving) {
					if (anim % 20 > 10) {
						screen.renderSprite(xx, yy, Sprite.demon_leftsway1, true);
					} else {
						screen.renderSprite(xx, yy, Sprite.demon_leftsway2, true);
					}
				} else {
					screen.renderSprite(xx, yy, Sprite.demon_left, true);
				}
			}
		}
		
	}
	
	private void renderHealthBar(Screen screen) {
		int xHealthBar = x - 20;
		int yHealthBar = y - 40;
		
		if (healthPercent >= 100) {
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health100, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health100, true);
			}
			
		} else if (healthPercent >= 90 && healthPercent < 100) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health90, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health90, true);
			}
			
		} else if (healthPercent >= 80 && healthPercent < 90) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health80, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health80, true);
			}
			
		} else if (healthPercent >= 70 && healthPercent < 80) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health70, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health70, true);
			}
			
		} else if (healthPercent >= 60 && healthPercent < 70) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health60, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health60, true);
			}
			
		} else if (healthPercent >= 50 && healthPercent < 60) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health50, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health50, true);
			}
			
		} else if (healthPercent >= 40 && healthPercent < 50) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health40, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health40, true);
			}
			
		} else if (healthPercent >= 30 && healthPercent < 40) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health30, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health30, true);
			}
			
		} else if (healthPercent >= 20 && healthPercent < 30) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health20, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health20, true);
			}
			
		} else if (healthPercent >= 1 && healthPercent < 20) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health10, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health10, true);
			}
			
		} else if (healthPercent <= 0 && healthPercent < 10) {
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(xHealthBar, yHealthBar + 1, Sprite.ethan_health0, true);
			} else {
				screen.renderSprite(xHealthBar, yHealthBar, Sprite.ethan_health0, true);
			}
			
		}
		
	}
}