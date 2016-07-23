package com.ethansprojects.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Loads spritesheets.
 * @author ethansprojects
 */

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	// Basic
	public static SpriteSheet main = new SpriteSheet("/textures/spritesheet.png", 850);
	public static SpriteSheet terrain = new SpriteSheet("/textures/terrain.png", 1024);
	public static SpriteSheet cursors = new SpriteSheet("/textures/cursors.png", 192);
	public static SpriteSheet infrastructure = new SpriteSheet("/textures/infrastructure.png", 3872);
	
	// Projectiles
	public static SpriteSheet magic_projectiles = new SpriteSheet("/textures/projectiles/magic.png", 48);
	
	// Player interface
	public static SpriteSheet dialoguebox = new SpriteSheet("/textures/interface/hud/dialoguebox.png", 400);
	public static SpriteSheet npc_closeups = new SpriteSheet("/textures/npc/closeups.png", 500);
	public static SpriteSheet space_talk = new SpriteSheet("/textures/interface/prompts/space_talk.png", 100);
	public static SpriteSheet hud_main = new SpriteSheet("/textures/interface/hud/main.png", 477);
	public static SpriteSheet hud_second = new SpriteSheet("/textures/interface/hud/second.png", 850);
	public static SpriteSheet minimaps = new SpriteSheet("/levels/minimaps.png", 520);
	public static SpriteSheet arrows = new SpriteSheet("/textures/interface/pointers/arrows.png", 500);
	public static SpriteSheet keyboard = new SpriteSheet("/textures/interface/prompts/keyboard.png", 256);
	public static SpriteSheet mouses = new SpriteSheet("/textures/interface/prompts/mouses.png", 500);
	
	// Mobs & mob-related
	public static SpriteSheet warrior = new SpriteSheet("/textures/char/warrior.png", 256);
	public static SpriteSheet warmage = new SpriteSheet("/textures/char/warmage.png", 256);
	public static SpriteSheet assassin = new SpriteSheet("/textures/char/assassin.png", 256);
	public static SpriteSheet tank = new SpriteSheet("/textures/char/tank.png", 256);
	
	public static SpriteSheet health_bars = new SpriteSheet("/textures/npc/healthbars.png", 440);
	public static SpriteSheet npc_labels = new SpriteSheet("/textures/npc/labels.png", 500);
	public static SpriteSheet nephyr = new SpriteSheet("/textures/npc/nephyr.png", 256);
	public static SpriteSheet demon = new SpriteSheet("/textures/npc/demon.png", 512);
	
	// Pause menu
	public static SpriteSheet pause_menu = new SpriteSheet("/textures/interface/menus/pause.png", 850);
	public static SpriteSheet pause_buttons = new SpriteSheet("/textures/interface/menus/pause_buttons.png", 1872);
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
	}
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		loadImage();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		loadImage();
	}
	
	// Loads sprites into pixels
	private void loadImage() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w); //translates image into pixels
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
