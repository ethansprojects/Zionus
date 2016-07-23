package com.ethansprojects.engine.graphics;

/**
 * Loads and pixel-renders sprites from a spritesheet.
 * @author ethansprojects
 */

public class Sprite {
	
	public final int SIZE; //Size of particular sprite
	private int x, y; //
	public int[] pixels;
	protected SpriteSheet sheet;
	private int width, height;
	
	// Cursors
	public static Sprite cursor = new Sprite(32, 0, 0, SpriteSheet.cursors);
	public static Sprite cursor_options = new Sprite(32, 1, 0, SpriteSheet.cursors);
	public static Sprite cursor_hover = new Sprite(32, 2, 0, SpriteSheet.cursors);
	public static Sprite cursor_new = new Sprite(16, 0, 2, SpriteSheet.cursors);
	
	// Basic map sprites
	public static Sprite portalSpawn = new Sprite(16, 0x00000);
	public static Sprite voidSprite = new Sprite(16, 0x000000);
	public static Sprite blackSprite = new Sprite(16, 0x000000);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.main);
	public static Sprite grassplain = new Sprite(16, 0, 1, SpriteSheet.main);
	public static Sprite rock = new Sprite(16, 1, 0, SpriteSheet.main);
	public static Sprite flower = new Sprite(16, 2, 0, SpriteSheet.main);
	public static Sprite grass_shadow = new Sprite(16, 2, 1, SpriteSheet.main);
	public static Sprite dirt_wall = new Sprite(16, 1, 1, SpriteSheet.main);
	public static Sprite dirt_wall_top = new Sprite(16, 3, 0, SpriteSheet.main);
	public static Sprite tall_bush0 = new Sprite(112, 2, 7, SpriteSheet.terrain);
	public static Sprite pond0 = new Sprite(79, 24, 1, SpriteSheet.terrain);
	public static Sprite better_rock = new Sprite(16, 58, 34, SpriteSheet.terrain);
	public static Sprite light_grass0 = new Sprite(16, 0, 22, SpriteSheet.terrain);
	public static Sprite light_grass1 = new Sprite(16, 1, 22, SpriteSheet.terrain);
	public static Sprite light_grass2 = new Sprite(16, 2, 22, SpriteSheet.terrain);
	public static Sprite light_grass3 = new Sprite(16, 3, 22, SpriteSheet.terrain);
	public static Sprite light_grass4 = new Sprite(16, 4, 22, SpriteSheet.terrain);
	public static Sprite light_grass5 = new Sprite(16, 5, 22, SpriteSheet.terrain);
	public static Sprite light_grass6 = new Sprite(16, 0, 23, SpriteSheet.terrain);
	public static Sprite light_grass7 = new Sprite(16, 1, 23, SpriteSheet.terrain);
	public static Sprite light_grass8 = new Sprite(16, 2, 23, SpriteSheet.terrain);
	public static Sprite light_grass9 = new Sprite(16, 3, 23, SpriteSheet.terrain);
	public static Sprite light_grass10 = new Sprite(16, 4, 23, SpriteSheet.terrain);
	public static Sprite light_grass11 = new Sprite(16, 5, 23, SpriteSheet.terrain);
	public static Sprite red_flower0 = new Sprite(16, 6, 22, SpriteSheet.terrain);
	public static Sprite red_flower1 = new Sprite(16, 7, 22, SpriteSheet.terrain);
	public static Sprite red_flower2 = new Sprite(16, 8, 22, SpriteSheet.terrain);
	public static Sprite red_flower3 = new Sprite(16, 9, 22, SpriteSheet.terrain);
	public static Sprite red_flower4 = new Sprite(16, 10, 22, SpriteSheet.terrain);
	public static Sprite red_flower5 = new Sprite(16, 11, 22, SpriteSheet.terrain);
	public static Sprite red_flower6 = new Sprite(16, 6, 23, SpriteSheet.terrain);
	public static Sprite red_flower7 = new Sprite(16, 7, 23, SpriteSheet.terrain);
	public static Sprite red_flower8 = new Sprite(16, 8, 23, SpriteSheet.terrain);
	public static Sprite red_flower9 = new Sprite(16, 9, 23, SpriteSheet.terrain);
	public static Sprite red_flower10 = new Sprite(16, 10, 23, SpriteSheet.terrain);
	public static Sprite red_flower11 = new Sprite(16, 11, 23, SpriteSheet.terrain);
	public static Sprite short_grass0 = new Sprite(16, 30, 22, SpriteSheet.terrain);
	public static Sprite short_grass1 = new Sprite(16, 31, 22, SpriteSheet.terrain);
	public static Sprite short_grass2 = new Sprite(16, 30, 23, SpriteSheet.terrain);
	public static Sprite short_grass3 = new Sprite(16, 31, 23, SpriteSheet.terrain);
	public static Sprite med_grass0 = new Sprite(16, 32, 22, SpriteSheet.terrain);
	public static Sprite med_grass1 = new Sprite(16, 33, 22, SpriteSheet.terrain);
	public static Sprite med_grass2 = new Sprite(16, 32, 23, SpriteSheet.terrain);
	public static Sprite med_grass3 = new Sprite(16, 33, 23, SpriteSheet.terrain);
	public static Sprite long_grass0 = new Sprite(16, 34, 22, SpriteSheet.terrain);
	public static Sprite long_grass1 = new Sprite(16, 35, 22, SpriteSheet.terrain);
	public static Sprite long_grass2 = new Sprite(16, 34, 23, SpriteSheet.terrain);
	public static Sprite long_grass3 = new Sprite(16, 35, 23, SpriteSheet.terrain);
	
	public static Sprite brown_squares = new Sprite(32, 1, 1, SpriteSheet.main);
	public static Sprite gray_diamond = new Sprite(32, 1, 2, SpriteSheet.main);
	public static Sprite brown_diamond = new Sprite(32, 1, 3, SpriteSheet.main);
	public static Sprite black_diamond = new Sprite(32, 1, 4, SpriteSheet.main);
	
	public static Sprite silver_door = new Sprite(64, 1, 1, SpriteSheet.main);
	public static Sprite brown_door = new Sprite(64, 1, 2, SpriteSheet.main);
	public static Sprite bed = new Sprite(64, 1, 0, SpriteSheet.main);
	
	// Dialogue box
	public static Sprite dialogue_box_far_left = new Sprite(100, 0, 0, SpriteSheet.dialoguebox);
	public static Sprite dialogue_box_mid_left = new Sprite(100, 1, 0, SpriteSheet.dialoguebox);
	public static Sprite dialogue_box_far_right = new Sprite(100, 2, 0, SpriteSheet.dialoguebox);
	public static Sprite dialogue_box_mid_right = new Sprite(100, 3, 0, SpriteSheet.dialoguebox);
	public static Sprite exit_image = new Sprite(100, 0, 1, SpriteSheet.dialoguebox);
	public static Sprite continue_image = new Sprite(100, 1, 1, SpriteSheet.dialoguebox);
	
	// Dialogue box icons
	public static Sprite nephyr_closeup = new Sprite(60, 0, 1, SpriteSheet.npc_closeups);
	public static Sprite ethan_closeup = new Sprite(60, 0, 0, SpriteSheet.npc_closeups);
	public static Sprite notification_icon = new Sprite(60, 1, 0, SpriteSheet.npc_closeups);
	public static Sprite commands_icon = new Sprite(60, 1, 1, SpriteSheet.npc_closeups);
	
	// Prompts
	public static Sprite space_talk = new Sprite(100, 0, 0, SpriteSheet.space_talk);
	public static Sprite enter = new Sprite(64, 0, 0, SpriteSheet.keyboard);
	public static Sprite tab = new Sprite(64, 1, 0, SpriteSheet.keyboard);
	public static Sprite mouse_white = new Sprite(100, 0, 0, SpriteSheet.mouses);
	public static Sprite mouse_white_leftclick = new Sprite(100, 1, 0, SpriteSheet.mouses);
	
	// HUD
	public static Sprite hud = new Sprite(477, 0, 0, SpriteSheet.hud_main);
	public static Sprite hud_second = new Sprite(477, 0, 0, SpriteSheet.hud_second);
	public static Sprite minimap_prologue = new Sprite(104, 0, 0, SpriteSheet.minimaps);
	
	// Menus
	public static Sprite pause = new Sprite(850, 0, 0, SpriteSheet.pause_menu);
	public static Sprite resume_off = new Sprite(234, 6, 0, SpriteSheet.pause_buttons);
	public static Sprite resume_on = new Sprite(234, 7, 0, SpriteSheet.pause_buttons);
	public static Sprite options_off = new Sprite(234, 4, 0, SpriteSheet.pause_buttons);
	public static Sprite options_on = new Sprite(234, 5, 0, SpriteSheet.pause_buttons);
	public static Sprite help_off = new Sprite(234, 2, 0, SpriteSheet.pause_buttons);
	public static Sprite help_on = new Sprite(234, 3, 0, SpriteSheet.pause_buttons);
	public static Sprite exitmain_off = new Sprite(234, 0, 0, SpriteSheet.pause_buttons);
	public static Sprite exitmain_on = new Sprite(234, 1, 0, SpriteSheet.pause_buttons);
	
	// Arrows
	public static Sprite arrow_right = new Sprite(64, 0, 0, SpriteSheet.arrows);
	public static Sprite arrow_down = new Sprite(64, 1, 0, SpriteSheet.arrows);
	public static Sprite arrow_up = new Sprite(64, 2, 0, SpriteSheet.arrows);
	public static Sprite arrow_left = new Sprite(64, 3, 0, SpriteSheet.arrows);
	public static Sprite top_halfcircle_left = new Sprite(112, 0, 1, SpriteSheet.arrows);
	public static Sprite top_halfcircle_right = new Sprite(112, 3, 1, SpriteSheet.arrows);
	public static Sprite bot_halfcircle_left = new Sprite(112, 0, 3, SpriteSheet.arrows);
	public static Sprite bot_halfcircle_right = new Sprite(112, 0, 2, SpriteSheet.arrows);
	public static Sprite circle_clockwise = new Sprite(112, 1, 2, SpriteSheet.arrows);
	public static Sprite circle_counterclockwise = new Sprite(112, 1, 1, SpriteSheet.arrows);
	public static Sprite arrowcurve_topleft = new Sprite(112, 2, 1, SpriteSheet.arrows);
	public static Sprite arrowcurve_topright = new Sprite(112, 2, 2, SpriteSheet.arrows);
	public static Sprite arrowcurve_botleft = new Sprite(112, 2, 3, SpriteSheet.arrows);
	public static Sprite arrowcurve_botright = new Sprite(112, 1, 3, SpriteSheet.arrows);
	
	// PROJECTILE SPRITES
	public static Sprite magic_ball = new Sprite(16, 0, 0, SpriteSheet.magic_projectiles);
	
	// Weapons (doomblade, infamy, blackout, shadowsteel
	public static Sprite oathkeeper = new Sprite(32, 1, 5, SpriteSheet.main);
	
	// PARTICLE SPRITES
	public static Sprite particle_default = new Sprite(3, 0xAAAAAA);
	
	// Health Bars
	public static Sprite player_health = new Sprite(40, 0, 0, SpriteSheet.health_bars);
	
	public static Sprite ethan_health100 = new Sprite(40, 1, 0, SpriteSheet.health_bars);
	public static Sprite ethan_health90 = new Sprite(40, 1, 1, SpriteSheet.health_bars);
	public static Sprite ethan_health80 = new Sprite(40, 1, 2, SpriteSheet.health_bars);
	public static Sprite ethan_health70 = new Sprite(40, 1, 3, SpriteSheet.health_bars);
	public static Sprite ethan_health60 = new Sprite(40, 1, 4, SpriteSheet.health_bars);
	public static Sprite ethan_health50 = new Sprite(40, 1, 5, SpriteSheet.health_bars);
	public static Sprite ethan_health40 = new Sprite(40, 1, 6, SpriteSheet.health_bars);
	public static Sprite ethan_health30 = new Sprite(40, 1, 7, SpriteSheet.health_bars);
	public static Sprite ethan_health20 = new Sprite(40, 1, 8, SpriteSheet.health_bars);
	public static Sprite ethan_health10 = new Sprite(40, 1, 9, SpriteSheet.health_bars);
	public static Sprite ethan_health0 = new Sprite(40, 1, 10, SpriteSheet.health_bars);
	
	// All sprites for Warriors    
	public static Sprite warrior_up = new Sprite(64, 0, 2, SpriteSheet.warrior);
	public static Sprite warrior_upsway1 = new Sprite(64, 0, 1, SpriteSheet.warrior);
	public static Sprite warrior_upsway2 = new Sprite(64, 1, 1, SpriteSheet.warrior);
	
	public static Sprite warrior_right = new Sprite(64, 1, 2, SpriteSheet.warrior);
	public static Sprite warrior_rightsway1 = new Sprite(64, 2, 1, SpriteSheet.warrior);
	public static Sprite warrior_rightsway2 = new Sprite(64, 3, 1, SpriteSheet.warrior);
	
	public static Sprite warrior_down = new Sprite(64, 2, 2, SpriteSheet.warrior);
	public static Sprite warrior_downsway1 = new Sprite(64, 0, 0, SpriteSheet.warrior);
	public static Sprite warrior_downsway2 = new Sprite(64, 1, 0, SpriteSheet.warrior);
	
	public static Sprite warrior_left = new Sprite(64, 3, 2, SpriteSheet.warrior);
	public static Sprite warrior_leftsway1 = new Sprite(64, 2, 0, SpriteSheet.warrior);
	public static Sprite warrior_leftsway2 = new Sprite(64, 3, 0, SpriteSheet.warrior);
	
	// All sprites for Warmages
	public static Sprite warmage_up = new Sprite(64, 0, 2, SpriteSheet.warmage);
	public static Sprite warmage_upsway1 = new Sprite(64, 0, 1, SpriteSheet.warmage);
	public static Sprite warmage_upsway2 = new Sprite(64, 1, 1, SpriteSheet.warmage);
	
	public static Sprite warmage_right = new Sprite(64, 1, 2, SpriteSheet.warmage);
	public static Sprite warmage_rightsway1 = new Sprite(64, 2, 1, SpriteSheet.warmage);
	public static Sprite warmage_rightsway2 = new Sprite(64, 3, 1, SpriteSheet.warmage);
	
	public static Sprite warmage_down = new Sprite(64, 2, 2, SpriteSheet.warmage);
	public static Sprite warmage_downsway1 = new Sprite(64, 0, 0, SpriteSheet.warmage);
	public static Sprite warmage_downsway2 = new Sprite(64, 1, 0, SpriteSheet.warmage);
	
	public static Sprite warmage_left = new Sprite(64, 3, 2, SpriteSheet.warmage);
	public static Sprite warmage_leftsway1 = new Sprite(64, 2, 0, SpriteSheet.warmage);
	public static Sprite warmage_leftsway2 = new Sprite(64, 3, 0, SpriteSheet.warmage);
	
	// All sprites for Assassins
	public static Sprite assassin_up = new Sprite(64, 0, 2, SpriteSheet.assassin);
	public static Sprite assassin_upsway1 = new Sprite(64, 0, 1, SpriteSheet.assassin);
	public static Sprite assassin_upsway2 = new Sprite(64, 1, 1, SpriteSheet.assassin);
	
	public static Sprite assassin_right = new Sprite(64, 1, 2, SpriteSheet.assassin);
	public static Sprite assassin_rightsway1 = new Sprite(64, 2, 1, SpriteSheet.assassin);
	public static Sprite assassin_rightsway2 = new Sprite(64, 3, 1, SpriteSheet.assassin);
	
	public static Sprite assassin_down = new Sprite(64, 2, 2, SpriteSheet.assassin);
	public static Sprite assassin_downsway1 = new Sprite(64, 0, 0, SpriteSheet.assassin);
	public static Sprite assassin_downsway2 = new Sprite(64, 1, 0, SpriteSheet.assassin);
	
	public static Sprite assassin_left = new Sprite(64, 3, 2, SpriteSheet.assassin);
	public static Sprite assassin_leftsway1 = new Sprite(64, 2, 0, SpriteSheet.assassin);
	public static Sprite assassin_leftsway2 = new Sprite(64, 3, 0, SpriteSheet.assassin);
	
	// All sprites for Tanks
	public static Sprite tank_up = new Sprite(64, 0, 2, SpriteSheet.tank);
	public static Sprite tank_upsway1 = new Sprite(64, 0, 1, SpriteSheet.tank);
	public static Sprite tank_upsway2 = new Sprite(64, 1, 1, SpriteSheet.tank);
	
	public static Sprite tank_right = new Sprite(64, 1, 2, SpriteSheet.tank);
	public static Sprite tank_rightsway1 = new Sprite(64, 2, 1, SpriteSheet.tank);
	public static Sprite tank_rightsway2 = new Sprite(64, 3, 1, SpriteSheet.tank);
	
	public static Sprite tank_down = new Sprite(64, 2, 2, SpriteSheet.tank);
	public static Sprite tank_downsway1 = new Sprite(64, 0, 0, SpriteSheet.tank);
	public static Sprite tank_downsway2 = new Sprite(64, 1, 0, SpriteSheet.tank);
	
	public static Sprite tank_left = new Sprite(64, 3, 2, SpriteSheet.tank);
	public static Sprite tank_leftsway1 = new Sprite(64, 2, 0, SpriteSheet.tank);
	public static Sprite tank_leftsway2 = new Sprite(64, 3, 0, SpriteSheet.tank);
	
	// All sprites for Ethan
	public static Sprite nephyr_label = new Sprite(32, 0, 0, SpriteSheet.npc_labels);
	
	public static Sprite nephyr_up = new Sprite(64, 0, 2, SpriteSheet.nephyr);
	public static Sprite nephyr_upsway1 = new Sprite(64, 0, 1, SpriteSheet.nephyr);
	public static Sprite nephyr_upsway2 = new Sprite(64, 1, 1, SpriteSheet.nephyr);
	
	public static Sprite nephyr_right = new Sprite(64, 1, 2, SpriteSheet.nephyr);
	public static Sprite nephyr_rightsway1 = new Sprite(64, 2, 1, SpriteSheet.nephyr);
	public static Sprite nephyr_rightsway2 = new Sprite(64, 3, 1, SpriteSheet.nephyr);
	
	public static Sprite nephyr_down = new Sprite(64, 2, 2, SpriteSheet.nephyr);
	public static Sprite nephyr_downsway1 = new Sprite(64, 0, 0, SpriteSheet.nephyr);
	public static Sprite nephyr_downsway2 = new Sprite(64, 1, 0, SpriteSheet.nephyr);
	
	public static Sprite nephyr_left = new Sprite(64, 3, 2, SpriteSheet.nephyr);
	public static Sprite nephyr_leftsway1 = new Sprite(64, 2, 0, SpriteSheet.nephyr);
	public static Sprite nephyr_leftsway2 = new Sprite(64, 3, 0, SpriteSheet.nephyr);
	
	// All sprites for demon
	public static Sprite demon_up = new Sprite(64, 2, 0, SpriteSheet.demon);
	public static Sprite demon_upsway1 = new Sprite(64, 3, 0, SpriteSheet.demon);
	public static Sprite demon_upsway2 = new Sprite(64, 3, 1, SpriteSheet.demon);
	
	public static Sprite demon_right = new Sprite(64, 4, 0, SpriteSheet.demon);
	public static Sprite demon_rightsway1 = new Sprite(64, 5, 0, SpriteSheet.demon);
	public static Sprite demon_rightsway2 = new Sprite(64, 5, 1, SpriteSheet.demon);
	
	public static Sprite demon_down = new Sprite(64, 0, 0, SpriteSheet.demon);
	public static Sprite demon_downsway1 = new Sprite(64, 1, 0, SpriteSheet.demon);
	public static Sprite demon_downsway2 = new Sprite(64, 1, 1, SpriteSheet.demon);
	public static Sprite demon_down_ignite1 = new Sprite(64, 4, 2, SpriteSheet.demon);
	public static Sprite demon_down_ignite2 = new Sprite(64, 4, 3, SpriteSheet.demon);
	
	public static Sprite demon_left = new Sprite(64, 6, 0, SpriteSheet.demon);
	public static Sprite demon_leftsway1 = new Sprite(64, 7, 0, SpriteSheet.demon);
	public static Sprite demon_leftsway2 = new Sprite(64, 7, 1, SpriteSheet.demon);
	
	public static Sprite demon_up_attack1 = new Sprite(64, 1, 3, SpriteSheet.demon);
	public static Sprite demon_up_attack2 = new Sprite(64, 1, 2, SpriteSheet.demon);
	
	public static Sprite demon_right_attack1 = new Sprite(64, 2, 3, SpriteSheet.demon);
	public static Sprite demon_right_attack2 = new Sprite(64, 2, 2, SpriteSheet.demon);
	public static Sprite demon_right_attack3 = new Sprite(64, 6, 2, SpriteSheet.demon);
	public static Sprite demon_right_attack4 = new Sprite(64, 6, 3, SpriteSheet.demon);
	
	public static Sprite demon_down_attack1 = new Sprite(64, 0, 3, SpriteSheet.demon);
	public static Sprite demon_down_attack2 = new Sprite(64, 0, 2, SpriteSheet.demon);
	
	public static Sprite demon_left_attack1 = new Sprite(64, 3, 3, SpriteSheet.demon);
	public static Sprite demon_left_attack2 = new Sprite(64, 3, 2, SpriteSheet.demon);
	public static Sprite demon_left_attack3 = new Sprite(64, 5, 2, SpriteSheet.demon);
	public static Sprite demon_left_attack4 = new Sprite(64, 5, 3, SpriteSheet.demon);
	
	public static Sprite demon_damage = new Sprite(64, 4, 5, SpriteSheet.demon);
	
	// Item sprites
	public static Sprite coin_bag = new Sprite(16, 3, 1, SpriteSheet.main);
	public static Sprite silver_sword = new Sprite(32, 5, 1, SpriteSheet.main);
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE]; //Creates new pixel array the size of sprite
		this.x = x * size; //Sets location of target sprite in sprite sheet
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	//Extracting a single sprite out of given sprite sheet
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
	
}
