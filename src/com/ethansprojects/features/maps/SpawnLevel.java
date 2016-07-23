package com.ethansprojects.features.maps;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.audio.AudioPlayer;
import com.ethansprojects.engine.entity.mob.npc.Nephyr;

/**
 * Loads spawn level. (inactive)
 * @author ethansprojects
 */

public class SpawnLevel extends Level {
	
	private AudioPlayer spawnMusic;
	
	public SpawnLevel() {
		
		super("/levels/spawn.png", "spawn");
	}
	
	protected void loadLevel(String path) {
		try {
			spawnMusic = new AudioPlayer("/audio/music/levels/spawn0.mp3");
			if (Zionus.music) {
				spawnMusic.play(true);
			}
			
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception; couldn't load level file.");
		}
		
		for (int i = 0; i < 1; i++) {
			add(new Nephyr(35, 85, 0));
		}
		
	}
	
	protected void generateLevel() {
		
	}
	
}
