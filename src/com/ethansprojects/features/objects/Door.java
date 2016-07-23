package com.ethansprojects.features.objects;

import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.entity.Entity;
import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * A door object.
 * @author ethansprojects
 */

public class Door extends Entity {
	
	private int memoryID;
	private Sprite door;
	
	public Door(int x, int y, int memoryID, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.memoryID = memoryID;
		this.door = sprite;
	}
	
	@Override
	public void update() throws InterruptedException {
		checkForCollsion();
	}
	
	private void checkForCollsion() {
		if ((Zionus.player.y / 16) <= y + 2 && (Zionus.player.y / 16) >= y + 1) {
			if ((Zionus.player.x / 16) <= x + 2 && (Zionus.player.x / 16) >= x - 2) {
				if (Zionus.player.collision(Zionus.player.xa, Zionus.player.ya)) {
					switch (Zionus.ACT) {
						case 0:
							prologueLevelDoors();
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
			}
		}
	}
	
	private void prologueLevelDoors() {
		switch (memoryID) {
			case 0:
				Zionus.player.x = 1202;
				Zionus.player.y = 148;
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
		}
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(x * 16, y * 16, door, true);
	}
	
}
