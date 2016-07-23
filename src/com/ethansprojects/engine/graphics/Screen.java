package com.ethansprojects.engine.graphics;

import com.ethansprojects.engine.entity.projectile.Projectile;
import java.util.Random;
import com.ethansprojects.engine.Zionus;

/**
 * The game screen.
 * @author ethansprojects
 */

public class Screen {
	
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public static boolean yellowText = false;
	public int xOffset, yOffset;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // Map size
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	// Pixel updating; clears previous unused pixels
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				if (col != 0xFFFF00FF) pixels[xa + ya * width] = col; // Transparent color: FF00FF (Pink)
			}
		}
	}
	
	public void renderItemSprite(int xp, int yp, String spriteName, boolean fixed) {
		
		switch (spriteName) {
			case "coins":
				renderSprite(xp, yp, Sprite.coin_bag, fixed);
				System.out.println("Rendering coin bag at (" + xp + ", " + yp + ")");
				break;
			case "silver_sword":
				//				renderSprite(xp, yp, Sprite.silver_sword, true);
				System.out.println("Rendering silver sword at (" + xp + ", " + yp + ")");
				break;
			default:
				System.out.println("The sprite name '" + spriteName + "' does not exist.");
		}
		
	}
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sheet.pixels[x + y * sheet.WIDTH];
				if (col != 0xFFFF00FF) pixels[xa + ya * sheet.WIDTH] = col; // Transparent color: FF00FF (Pink)
			}
		}
	}
	
	// Pixel mapping; how individual pixels render on the screen
	public void renderTile(int xp, int yp, Sprite sprite) {
		Zionus.mapDarkness = false;
		
		xp = xp - xOffset;
		yp = yp - yOffset;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; // Makes pixels render only when
				if (xa < 0) xa = 0; // Ensures that the left side of the screen will always have a rendered pixel so the
									// screen is full with proper graphics on screen
				int col = sprite.pixels[x + y * sprite.SIZE];
				
				if (!Zionus.mapDarkness) {
					if (col == 0xFFFF00FF) continue;
					else pixels[xa + ya * width] = col;
				} else {
					col = pixels[xa + ya * width];
				}
				
			}
		}
	}
	
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp = xp - xOffset;
		yp = yp - yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break; // Makes pixels render only
																								// when on screen
				if (xa < 0) xa = 0; // Ensures that the left side of the screen will always have a rendered pixel so the
									// screen is full with proper graphics
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if (col != 0xFFFF00FF) pixels[xa + ya * width] = col; // Transparent color: FF00FF (Pink)
			}
		}
	}
	
	public void renderNPC(int xp, int yp, Sprite sprite) {
		xp = xp - xOffset;
		yp = yp - yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break; // Makes pixels render only when on screen
				if (xa < 0) xa = 0; // Ensures that the left side of the screen will always have a rendered pixel so the
									// screen is full with proper graphics
					
				int col = sprite.pixels[xs + y * 32];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col; // Transparent color: FF00FF (Pink)
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void drawRect(int x, int y, int width, int height, boolean fixed) {
		if (fixed) {
			x -= xOffset;
			y -= yOffset;
		}
		for (int yy = y; yy < y + height; yy++) {
			if (x >= this.width || yy < 0 || yy >= this.height) continue;
			if (x > 0) pixels[x + yy * this.width] = 0xff0000;
			if (x + width >= this.width) continue;
			if (x + width > 0) pixels[x + width + yy * this.width] = 0xff0000;
		}
		for (int xx = x; xx < x + width; xx++) {
			if (xx < 0 || xx >= this.width || y >= this.height) continue;
			if (y > 0) pixels[xx + y * this.width] = 0xff0000;
			if (y + height >= this.height) continue;
			if (y + height > 0) pixels[xx + (y + height) * this.width] = 0xff0000;
		}
		
	}
	
	public void renderChar(int x, int y, int size, int[] character) {
		for (int yy = 0; yy < size; yy++) {
			int yp = yy + y;
			for (int xx = 0; xx < size; xx++) {
				int xp = xx + x;
				if (xp < 0 || xp >= width || yp < 0 || yp >= height) continue;
				int col = character[xx + yy * size];
				if (col == 0xffff00ff || col == 0xff7f00f7) continue;
				if (yellowText) col = 0xffFFD800;
				pixels[xp + yp * width] = col;
			}
		}
		
	}
}
