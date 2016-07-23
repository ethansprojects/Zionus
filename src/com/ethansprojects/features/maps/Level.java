package com.ethansprojects.features.maps;

import java.util.ArrayList;
import java.util.List;
import com.ethansprojects.features.maps.tile.Tile;
import com.ethansprojects.engine.entity.Entity;
import com.ethansprojects.engine.entity.Interactive;
import com.ethansprojects.features.dialogue.Dialogue;
import com.ethansprojects.engine.entity.mob.npc.Nephyr;
import com.ethansprojects.engine.entity.mob.player.Player;
import com.ethansprojects.engine.entity.particle.Particle;
import com.ethansprojects.engine.entity.projectile.Projectile;
import com.ethansprojects.engine.graphics.Screen;

/**
 * Adds & removes entities and tiles for the level.
 * @author ethansprojects
 */

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	
	private String levelName;
	
	private List<Player> players = new ArrayList<Player>();
	private List<Nephyr> nephyrs = new ArrayList<Nephyr>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	public Level(String path, String name) {
		loadLevel(path);
		generateLevel();
		levelName = name;
	}
	
	public String getLevelName() {
		return levelName;
	}
	
	// Random level generator
	protected void generateLevel() {
		for (int y = 0; y < 64; y++) {
			for (int x = 0; x < 64; x++) {
				getTile(x, y);
			}
		}
		tile_size = 16;
	}
	
	protected void loadLevel(String path) {
		
	}
	
	// Entity position and data updating; AI, NPCs, etc
	public void update() throws InterruptedException {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		for (int i = 0; i < nephyrs.size(); i++) {
			//			nephyrs.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}
		for (int i = 0; i < nephyrs.size(); i++) {
			if (nephyrs.get(i).isRemoved()) nephyrs.remove(i);
		}
	}
	
	// private void time() {
	// }
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size - xOffset) >> 4;
			int yt = (y - c / 2 * size - yOffset) >> 4;
			// System.out.println("(" +xt+ ", " +yt+ ")");
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		// Below 4 ints define render region of screen; corner pins
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
		for (int i = 0; i < nephyrs.size(); i++) {
			nephyrs.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else if (e instanceof Nephyr) {
			nephyrs.add((Nephyr) e);
		} else {
			entities.add(e);
		}
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Nephyr getNephyrAt(int index) {
		return nephyrs.get(index);
	}
	
	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = entity.getX();
			int y = entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(entity);
			
		}
		return result;
	}
	
	public List<Entity> getInteractiveEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		List<Entity> entities = getEntities(e, radius);
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Interactive) {
				result.add(entities.get(i));
			}
		}
		return result;
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = player.getX();
			int y = player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(player);
		}
		return result;
	}
	
	// Find out which tile to render
	public Tile getTile(int x, int y) {
		
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
		if (tiles[x + y * width] == 0xFF000000) return Tile.blackTile;
		
		if (tiles[x + y * width] == 0xFF00AF00) {
			if (Dialogue.ethanTutorialDialogueState <= 17) return Tile.dirt_wall;
			else return Tile.light_grass0;
		}
		
		if (tiles[x + y * width] == 0xFF006500) return Tile.grassplain; // Original: grass
		if (tiles[x + y * width] == 0xFFFFFF00) return Tile.yellow_flower;
		
		if (tiles[x + y * width] == 0xFF00FF00) return Tile.light_grass0;
		
		if (tiles[x + y * width] == 0xFF009F00) return Tile.light_grass0;
		if (tiles[x + y * width] == 0xFF008E00) return Tile.light_grass3;
		if (tiles[x + y * width] == 0xFF005600) return Tile.light_grass4;
		if (tiles[x + y * width] == 0xFF3EA83E) return Tile.light_grass8;
		
		if (tiles[x + y * width] == 0xFFFF0000) return Tile.red_flower0;
		if (tiles[x + y * width] == 0xFFEA0000) return Tile.red_flower1;
		if (tiles[x + y * width] == 0xFFD60000) return Tile.red_flower2;
		if (tiles[x + y * width] == 0xFFC10000) return Tile.red_flower3;
		if (tiles[x + y * width] == 0xFFAD0000) return Tile.red_flower4;
		if (tiles[x + y * width] == 0xFF990000) return Tile.red_flower5;
		if (tiles[x + y * width] == 0xFF840000) return Tile.red_flower6;
		if (tiles[x + y * width] == 0xFF8F0505) return Tile.red_flower7;
		if (tiles[x + y * width] == 0xFF5B0000) return Tile.red_flower8;
		if (tiles[x + y * width] == 0xFFFF383E) return Tile.red_flower9;
		if (tiles[x + y * width] == 0xFFFF003E) return Tile.red_flower10;
		if (tiles[x + y * width] == 0xFFFF009F) return Tile.red_flower11;
		
		if (tiles[x + y * width] == 0xFF606060) return Tile.better_rock; // Original: rock
		if (tiles[x + y * width] == 0xFF3C1E00) return Tile.dirt_wall;
		if (tiles[x + y * width] == 0xFF2D1600) return Tile.dirt_wall_top;
		if (tiles[x + y * width] == 0x00FFFF) return Tile.spawnTile;
		
		if (tiles[x + y * width] == 0xC5B59D) {
			System.out.println("4");
			return Tile.red_flower4;
		}
		
		if (tiles[x + y * width] == 0xF9BC90) {
			System.out.println("6");
			return Tile.red_flower6;
		}
		
		return Tile.voidTile;
	}
}
