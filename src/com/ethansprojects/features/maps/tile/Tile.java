package com.ethansprojects.features.maps.tile;

import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;

/**
 * All tiles.
 * @author ethansprojects
 */

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile grassplain = new GrassTile(Sprite.grassplain); // Original: Sprite.grassplain
	public static Tile grass_shadow = new GrassTile(Sprite.grass_shadow);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile yellow_flower = new FlowerTile(Sprite.flower);
	
	public static Tile red_flower0 = new RedFlowerTile(Sprite.red_flower0);
	public static Tile red_flower1 = new RedFlowerTile(Sprite.red_flower1);
	public static Tile red_flower2 = new RedFlowerTile(Sprite.red_flower2);
	public static Tile red_flower3 = new RedFlowerTile(Sprite.red_flower3);
	public static Tile red_flower4 = new RedFlowerTile(Sprite.red_flower4);
	public static Tile red_flower5 = new RedFlowerTile(Sprite.red_flower5);
	public static Tile red_flower6 = new RedFlowerTile(Sprite.red_flower6);
	public static Tile red_flower7 = new RedFlowerTile(Sprite.red_flower7);
	public static Tile red_flower8 = new RedFlowerTile(Sprite.red_flower8);
	public static Tile red_flower9 = new RedFlowerTile(Sprite.red_flower9);
	public static Tile red_flower10 = new RedFlowerTile(Sprite.red_flower10);
	public static Tile red_flower11 = new RedFlowerTile(Sprite.red_flower11);
	
	public static Tile light_grass0 = new GrassTile(Sprite.light_grass0);
	public static Tile light_grass1 = new GrassTile(Sprite.light_grass1);
	public static Tile light_grass2 = new GrassTile(Sprite.light_grass2);
	public static Tile light_grass3 = new GrassTile(Sprite.light_grass3);
	public static Tile light_grass4 = new GrassTile(Sprite.light_grass4);
	public static Tile light_grass5 = new GrassTile(Sprite.light_grass5);
	public static Tile light_grass6 = new GrassTile(Sprite.light_grass6);
	public static Tile light_grass7 = new GrassTile(Sprite.light_grass7);
	public static Tile light_grass8 = new GrassTile(Sprite.light_grass8);
	public static Tile light_grass9 = new GrassTile(Sprite.light_grass9);
	public static Tile light_grass10 = new GrassTile(Sprite.light_grass10);
	public static Tile light_grass11 = new GrassTile(Sprite.light_grass11);
	
	public static Tile better_rock = new RockTile(Sprite.better_rock);
	
	public static Tile short_grass0 = new GrassTile(Sprite.short_grass0);
	public static Tile short_grass1 = new GrassTile(Sprite.short_grass1);
	public static Tile short_grass2 = new GrassTile(Sprite.short_grass2);
	public static Tile short_grass3 = new GrassTile(Sprite.short_grass3);
	
	public static Tile med_grass0 = new GrassTile(Sprite.med_grass0);
	public static Tile med_grass1 = new GrassTile(Sprite.med_grass1);
	public static Tile med_grass2 = new GrassTile(Sprite.med_grass2);
	public static Tile med_grass3 = new GrassTile(Sprite.med_grass3);
	
	public static Tile long_grass0 = new GrassTile(Sprite.long_grass0);
	public static Tile long_grass1 = new GrassTile(Sprite.long_grass1);
	public static Tile long_grass2 = new GrassTile(Sprite.long_grass2);
	public static Tile long_grass3 = new GrassTile(Sprite.long_grass3);
	
	public static Tile dirt_wall = new RockTile(Sprite.dirt_wall);
	public static Tile dirt_wall_top = new RockTile(Sprite.dirt_wall_top);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile blackTile = new BlackTile(Sprite.blackSprite);
	
	public static Tile spawnTile = new SpawnTile(Sprite.better_rock);
	
	public static Tile bed = new SpawnTile(Sprite.bed);
	public static Tile black_diamond = new BedTile(Sprite.black_diamond);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean spawnPortal = false;
	
}
