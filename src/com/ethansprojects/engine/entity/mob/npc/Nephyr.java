package com.ethansprojects.engine.entity.mob.npc;

import java.util.ArrayList;
import java.util.List;
import com.ethansprojects.features.debug.Box;
import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.entity.Entity;
import com.ethansprojects.engine.entity.Interactive;
import com.ethansprojects.features.debug.Debug;
import com.ethansprojects.features.dialogue.Dialogue;
import com.ethansprojects.features.story.Prologue;
import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;
import com.ethansprojects.engine.entity.mob.Mob;
import com.ethansprojects.engine.entity.mob.player.Player;

/**
 * A Nephyr entity.
 * @author ethansprojects
 */

public class Nephyr extends Mob implements Interactive {
	
	public static int npcID = 1;
	public static int memoryID;
	
	private int anim = 0;
	
	public int xa = 0;
	public int ya = 0;
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	private int pxa = 0;
	private int pya = 0;
	
	private Box box;
	
	public static boolean talking = false;
	
	private static boolean dirOverride = false;
	
	private boolean[] attackingNPC = new boolean[100];
	private static int healthPercent = 100;
	
	public static int magic = 40;
	public static int defense = 15;
	public static int attackSpeed = 6;
	
	
	private List<Dialogue> dialogues = new ArrayList<Dialogue>();
	
	public Nephyr(int x, int y, int memoryID) {
		this.x = x << 4;
		this.y = y << 4;
		Nephyr.memoryID = memoryID;
		dir = Direction.DOWN;
		// 697, 1303
		box = new Box(410, 990, 350, 400);
	}
	
	public void update() {
		xa = ya = 0;
		Prologue.prologueActions();
//		walkRandomly();
		animationClock();
		setDirections();
		moveToTalk();
		walk();
		if (Zionus.ACT == 1) {
			createBoxBoundaries();
		}
	}
	
	
	private void animationClock() {
		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}
	}
	
	private void setDirections() {
		if (ya < 0) {
			dir = Direction.UP;
		} else if (ya > 0) {
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			dir = Direction.LEFT;
		} else if (xa > 0) {
			dir = Direction.RIGHT;
		}
	}
	
	private void walk() {
		if (xa != 0 || ya != 0) {
			move(xa, ya, dirOverride);
			moving = true;
		} else {
			moving = false;
		}
	}
	
	private void moveToTalk() {
		Player player = level.getClientPlayer();
		if (pxa != 0 || pya != 0) {
			player.move(pxa, pya, false);
			player.setnpcOverride(true);
			player.setMoving(true);
			if (pya != 0) {
				if (pya > 0) player.setDirection("down");
				if (pya < 0) player.setDirection("up");
			} else {
				if (pxa > 0) player.setDirection("right");
				if (pxa < 0) player.setDirection("left");
			}
		} else {
			player.setnpcOverride(false);
			player.setMoving(false);
		}
		if (talking) {
			xa = ya = 0;
			if (player.dir == Direction.LEFT) {
				dir = Direction.RIGHT;
			}
			if (player.getX() < x + 40) {
				pxa = 1;
			} else if (player.getX() > x + 40) {
				pxa = -1;
			} else {
				pxa = 0;
			}
			if (player.getY() < y) {
				pya = 1;
			} else if (player.getY() > y) {
				pya = -1;
			} else {
				pya = 0;
			}
			
		}
	}
	
	private void createBoxBoundaries() {
		if (x + xa > box.x + box.width) {
			xa *= -1;
		}
		if (x + xa < box.x) {
			xa *= -1;
		}
		if (y + ya > box.y + box.height) {
			ya *= -1;
		}
		if (y + ya < box.y) {
			ya *= -1;
		}
	}
	
	public void chasePlayer(int proximity, double speed) {
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
	
	
	public void attackPlayer(int attackerID) {
		attackingNPC[attackerID] = true;
//		chasePlayer(45, 1);
//		if (getXProximityToPlayer() < 80) {
			determineHits(attackerID, getXProximityToPlayer(), 0);
//		}
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
	
	
	private int determineHits(int attackerID, int enemyProximity, int targetNpcID) {
		int min = 5;
		int max = enemyProximity;
		int scale = (random.nextInt(max - min + 1) + min);
		
		switch (targetNpcID) {
			case 0: // Player
				break;
			case 1: // Nephyr
				break;
			case 2: // Demon
				break;
		}
		int targetDefense = (Player.defense + Player.defBonus) / 4;
		if (targetDefense < 4) {
			targetDefense = 4;
		}
		
		int damage = (int) ((magic + magic) / targetDefense);
		int result = (int) (damage / (scale));
		
		if (startAttackTimer(attackSpeed, npcID)) {
			System.out.println("Demon hits " + result + " on enemy.");
			return result;
		} else {
			return -1;
		}
	}
	
	@Override
	public void render(Screen screen) {
		
		renderAnimations(screen);
		renderNameLabel(screen);
		renderHealthBar(screen);
		
		boolean drawRect = false;
		if (drawRect) Debug.drawRect(box.x, box.y, box.width, box.height, true, screen);
		
		if (dialogues.size() > 0) {
			dialogues.get(0);
			Dialogue.dialogueBox("Nephyr", screen);
		}
	}
	
	private void renderAnimations(Screen screen) {
		xOffset = x - 32;
		yOffset = y - 32;
		
		if (dir == Direction.UP) {
			if (moving) {
				if (anim % 20 > 10) {
					screen.renderSprite(xOffset, yOffset, Sprite.nephyr_upsway1, true);
				} else {
					screen.renderSprite(xOffset, yOffset, Sprite.nephyr_upsway2, true);
				}
			} else {
				screen.renderSprite(xOffset, yOffset, Sprite.nephyr_up, true);
			}
		}
		if (dir == Direction.RIGHT) {
			if (moving) {
				if (anim % 20 > 10) {
					screen.renderSprite(xOffset, yOffset, Sprite.nephyr_rightsway1, true);
				} else {
					screen.renderSprite(xOffset, yOffset, Sprite.nephyr_rightsway2, true);
				}
			} else {
				screen.renderSprite(xOffset, yOffset, Sprite.nephyr_right, true);
			}
		}
		if (dir == Direction.DOWN) {
			if (moving) {
				if (anim % 20 > 10) {
					screen.renderSprite(xOffset, yOffset, Sprite.nephyr_downsway1, true);
				} else {
					screen.renderSprite(xOffset, yOffset, Sprite.nephyr_downsway2, true);
				}
			} else {
				screen.renderSprite(xOffset, yOffset, Sprite.nephyr_down, true);
			}
		}
		if (dir == Direction.LEFT) {
			if (moving) {
				if (anim % 20 > 10) {
					screen.renderSprite(xOffset, yOffset, Sprite.nephyr_leftsway1, true);
				} else {
					screen.renderSprite(xOffset, yOffset, Sprite.nephyr_leftsway2, true);
				}
			} else {
				screen.renderSprite(xOffset, yOffset, Sprite.nephyr_left, true);
			}
		}
	}
	
	private void renderNameLabel(Screen screen) {
		if (moving && (anim % 20 > 10)) {
			screen.renderSprite(x - 16, y - 57, Sprite.nephyr_label, true);
		} else {
			screen.renderSprite(x - 16, y - 55, Sprite.nephyr_label, true);
		}
	}
	
	private void renderHealthBar(Screen screen) {
		if (healthPercent >= 100) {
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health100, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health100, true);
			}
		} else if (healthPercent >= 90 && healthPercent < 100) {
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health90, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health90, true);
			}
		} else if (healthPercent >= 80 && healthPercent < 90) {
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health80, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health80, true);
			}
		} else if (healthPercent >= 70 && healthPercent < 80) {
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health70, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health70, true);
			}
			
		} else if (healthPercent >= 60 && healthPercent < 70) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health60, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health60, true);
			}
			
		} else if (healthPercent >= 50 && healthPercent < 60) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health50, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health50, true);
			}
			
		} else if (healthPercent >= 40 && healthPercent < 50) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health40, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health40, true);
			}
			
		} else if (healthPercent >= 30 && healthPercent < 40) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health30, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health30, true);
			}
			
		} else if (healthPercent >= 20 && healthPercent < 30) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health20, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health20, true);
			}
			
		} else if (healthPercent >= 1 && healthPercent < 20) {
			
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health10, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health10, true);
			}
			
		} else if (healthPercent <= 0 && healthPercent < 10) {
			if (moving && (anim % 20 > 10)) {
				screen.renderSprite(x - 20, y - 36, Sprite.ethan_health0, true);
			} else {
				screen.renderSprite(x - 20, y - 34, Sprite.ethan_health0, true);
			}
			
		}
		
	}
	
	public void interact(Entity e) {
		
		if (e instanceof Player) {
			talking = true;
			((Player) e).talking = true;
			if (dialogues.size() <= 0) {
				dialogues.add(new Dialogue("asd"));
			} else {
				if (dialogues.size() == 1) {
					talking = false;
					((Player) e).talking = false;
				}
				
				dialogues.remove(0);
			}
		}
	}
}
