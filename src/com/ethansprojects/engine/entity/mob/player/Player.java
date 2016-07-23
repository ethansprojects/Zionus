package com.ethansprojects.engine.entity.mob.player;

import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.entity.mob.Mob;
import com.ethansprojects.engine.entity.projectile.MagicProjectile;
import com.ethansprojects.engine.entity.projectile.Projectile;
import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;
import com.ethansprojects.engine.physics.input.Keyboard;
import com.ethansprojects.engine.physics.input.Mouse;
import com.ethansprojects.features.debug.Commands;
import com.ethansprojects.features.dialogue.Dialogue;
import com.ethansprojects.features.story.Prologue;

/**
 * A player entity.
 * @author ethansprojects
 */

public class Player extends Mob {
	
	public Keyboard input;
	public Direction dir = Direction.DOWN;
	
	private boolean npcOverride = false;
	public boolean talking = false;
	public boolean frozen = false;
	
	public static boolean playerHasMoved = false;
	public static boolean playerHasShot = false;
	public boolean keyboardInput = true;
	
	private int anim = 0;
	public int xa = 0;
	public int ya = 0;
	private int speed = 1;
	private int frequency = 0;
	private int hitIconTimer = -1;
	private int hitIconTimerStart = 0;
	
	public String personSpeaking = "";
	public String sendMessage = "";
	
	public static int health = 10;
	public static int attack = 1;
	public static int power = 1;
	public static int defense = 1;
	public static int ranged = 1;
	public static int magic = 1;
	
	public static int attackBonus = 0;
	public static int powBonus = 0;
	public static int defBonus = 0;
	public static int rangeBonus = 0;
	public static int magBonus = 0;
	public static int speedBonus = 0;
	
	public static int totalAttack = 1;
	public static int totalPower = 1;
	public static int totalDefense = 1;
	public static int totalRanged = 1;
	public static int totalMagic = 1;
	public static int totalSpeed = 2;
	
	public Player(Keyboard input) {
		this.input = input;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		frequency = MagicProjectile.FREQUENCY;
		
	}
	
	public void setMoving(boolean isMoving) {
		moving = isMoving;
	}
	
	public void setDirection(String enteredDirection) {
		enteredDirection = enteredDirection.toLowerCase();
		Direction newD;
		
		switch (enteredDirection) {
			case "left":
				newD = Direction.LEFT;
				break;
			case "right":
				newD = Direction.RIGHT;
				break;
			case "up":
				newD = Direction.UP;
			default:
				newD = Direction.DOWN;
				break;
		}
		dir = newD;
	}
	
	public void setnpcOverride(boolean Override) {
		npcOverride = Override;
	}
	
	public void update() throws InterruptedException {
		speed = 2;
		xa = ya = 0;
		animationClock();
		controlPlayerDuringActs();
		walk();
		clearProjectiles();
		shootProjectiles();
		overrides();
	}
	
	private void getKeyboardInput() {
		if (input.shift && keyboardInput) {
			// weapons.equip(1, screen);
		}
		if (input.left && keyboardInput) {
			// weapons.loot(1);
			xa = (xa - 1) * speed;
			if (!npcOverride) dir = Direction.LEFT;
			playerHasMoved = true;
		}
		if (input.right && keyboardInput) {
			// weapons.loot(4);
			xa = (xa + 1) * speed;
			if (!npcOverride) dir = Direction.RIGHT;
			playerHasMoved = true;
		}
		if (input.up && keyboardInput) {
			ya = (ya - 1) * speed;
			if (!npcOverride) dir = Direction.UP;
			playerHasMoved = true;
		}
		if (input.down && keyboardInput) {
			ya = (ya + 1) * speed;
			if (!npcOverride) dir = Direction.DOWN;
			playerHasMoved = true;
		}
	}
	
	public void walk() {
		if (!talking && !frozen && !Commands.commandHUD) {
			getKeyboardInput();
			if (xa != 0 || ya != 0) {
				if (!talking) {
					move(xa, ya, false);
				}
				if (!npcOverride && !talking) {
					moving = true;
				}
			} else {
				if (!npcOverride) {
					moving = false;
				} else {
					moving = true;
				}
			}
	//		if (x >= 544 && x <= 594 && y <= 1003 && y >= 978) {
	//			Zionus.setLevel(new PrologueLevel(), 101, 36);
	//		}
		}
		
	}
	
	
	private void overrides() {
		if (talking && !npcOverride) {
			moving = false;
			dir = Direction.LEFT;
			// return;
		}
	}
	
	private void animationClock() {
		if (anim < 7500) {
			anim++;
		} else {
			anim = 0; 
		}
	}
	
	private void shootProjectiles() {
		if (!talking) {
			if (frequency > 0) {
				frequency--;
			}
			if (Mouse.getButton() == 1 && frequency <= 0) {
				playerHasShot = true;
				// Retrieve mouse angle with respect to player
				double dx = (Mouse.getX() - Zionus.getWindowWidth() / 2);
				double dy = (Mouse.getY() - Zionus.getWindowHeight() / 2);
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir);
				frequency = MagicProjectile.FREQUENCY;
			}
		}
	}
	
	private void clearProjectiles() {
		for (int i = 0; i < level.projectiles.size(); i++) {
			Projectile p = level.projectiles.get(i);
			if (p.isRemoved()) level.projectiles.remove(i);
		}
	}
	
	public void takeDamage(int damage) {
		hitIconTimer = 3;
		health = health - damage;
		if (health <= 0) {
			// TODO: Teleport all dead entities to area on the map with grim reaper
			System.out.println("die mothafucka");
		}
	}
	
	private void controlPlayerDuringActs() throws InterruptedException {
		// TODO: Save ACT_stage and Zionus.ACT in character file at checkpoints.
		switch (Zionus.ACT) {
			case 0: // Prologue
				Prologue.prologueActions();
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
		}
		
	}
	
	public void render(Screen screen) {
		if (!personSpeaking.equals("")) {
			Dialogue.dialogueBox(personSpeaking, screen);
		} else {
			for (int i = 0; i < 1000000; i++) {
			}
		}
		if (!sendMessage.equals("")) {
			Dialogue.sendMessage(sendMessage, screen);
		}
		renderAnimations(screen);
		renderUserHealthBar(screen);
		renderHitIcon(screen);
		renderCursor(screen);
		Zionus.commands.render(screen);
	}
	
	private void renderCursor(Screen screen) {
		switch (Zionus.cursorType) {
			case 0:
				screen.renderSprite((int) (Mouse.getX() / Zionus.scale), (int) (Mouse.getY() / Zionus.scale), Sprite.cursor_new, false);
//				screen.renderSprite((int) (Mouse.getX() / Zionus.scale), (int) (Mouse.getY() / Zionus.scale), Sprite.cursor, false);
				break;
			case 2:
				screen.renderSprite((int) (Mouse.getX() / Zionus.scale), (int) (Mouse.getY() / Zionus.scale), Sprite.cursor_hover, false);
				break;
			case 3:
				screen.renderSprite((int) (Mouse.getX() / Zionus.scale), (int) (Mouse.getY() / Zionus.scale), Sprite.cursor_options, false);
				break;
		}
	}
	
	private void renderHitIcon(Screen screen) {
		
		if (hitIconTimer > 0 && hitIconTimer < 11) {
			int timerEnd = Zionus.oneSecondTimer;
			int hitIconOffset = 22;
			screen.renderSprite(x - hitIconOffset, y - hitIconOffset, Sprite.demon_damage, true);
			if (timerEnd - hitIconTimerStart == hitIconTimer) {
				hitIconTimer = -1;
			}
		} else {
			hitIconTimerStart = Zionus.oneSecondTimer;
		}
		
	}
	
	private void renderAnimations(Screen screen) {
		int xx = x - 32;
		int yy = y - 32;
		
		if (dir == Direction.UP) {
			if (moving) {
				if (anim % 20 > 10) {
					screen.renderSprite(xx, yy, Sprite.warrior_upsway1, true);
				} else {
					screen.renderSprite(xx, yy, Sprite.warrior_upsway2, true);
				}
			} else {
				screen.renderSprite(xx, yy, Sprite.warrior_up, true);
			}
		}
		if (dir == Direction.RIGHT) {
			if (moving) {
				if (anim % 20 > 10) {
					screen.renderSprite(xx, yy, Sprite.warrior_rightsway1, true);
				} else {
					screen.renderSprite(xx, yy, Sprite.warrior_rightsway2, true);
				}
			} else {
				screen.renderSprite(xx, yy, Sprite.warrior_right, true);
			}
		}
		if (dir == Direction.DOWN) {
			if (moving) {
				if (anim % 20 > 10) {
					screen.renderSprite(xx, yy, Sprite.warrior_downsway1, true);
				} else {
					screen.renderSprite(xx, yy, Sprite.warrior_downsway2, true);
				}
			} else {
				screen.renderSprite(xx, yy, Sprite.warrior_down, true);
			}
		}
		if (dir == Direction.LEFT) {
			if (moving) {
				if (anim % 20 > 10) {
					screen.renderSprite(xx, yy, Sprite.warrior_leftsway1, true);
				} else {
					screen.renderSprite(xx, yy, Sprite.warrior_leftsway2, true);
				}
			} else {
				screen.renderSprite(xx, yy, Sprite.warrior_left, true);
			}
		}
	}
	
	private void renderUserHealthBar(Screen screen) {
		screen.renderSprite(Zionus.middleScreenX - 20, Zionus.middleScreenY - 40, Sprite.player_health, false);
	}
	
}

