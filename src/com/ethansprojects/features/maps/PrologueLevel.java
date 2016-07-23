package com.ethansprojects.features.maps;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.entity.mob.npc.Demon;
import com.ethansprojects.engine.entity.mob.npc.Nephyr;
import com.ethansprojects.engine.audio.AudioPlayer;
import com.ethansprojects.engine.graphics.Sprite;
import com.ethansprojects.features.objects.Door;

/**
 * Loads the prologue level.
 * @author ethansprojects
 */

public class PrologueLevel extends Level {
	
	private AudioPlayer prologueMusic;
	
	public PrologueLevel() {
		super("/levels/prologue.png", "tutorial");
	}
	
	protected void loadLevel(String path) {
		try {
			prologueMusic = new AudioPlayer("/audio/music/prologue/The_Last_Encounter_(Extended Version).mp3"); // special/arcade-action0.mp3
			if (Zionus.music) {
				prologueMusic.play(true);
			}
			
			BufferedImage image = ImageIO.read(PrologueLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception; couldn't load level file.");
		}
		addCharacters();
		addObjects();
		
	}
	
	private void addCharacters() {
		for (int i = 0; i < 1; i++) {
			add(new Nephyr(49, 9, i));
			add(new Demon(6, 8, i));
		}
		
	}
	
	private void addObjects() {
		add(new Door(102, 11, 0, Sprite.silver_door));
	}
	
	protected void generateLevel() {
	}
	
}
