package com.ethansprojects.engine.audio;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Plays music and SFX from an audio file.
 * @author ethansprojects
 */

public class AudioPlayer {
	
	private Clip clip;
	
	public AudioPlayer(String soundFileName) {
		// MP3 and WAV files
		try {
			if (soundFileName.contains("sfx")) {
				System.out.println("[SFX] Playing sound " + soundFileName);
			} else if (soundFileName.contains("music")) {
				System.out.println("[Music] Now playing: " + soundFileName);
			}
			
			InputStream audioSrc = getClass().getResourceAsStream(soundFileName);
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
			
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void play(boolean loop) {
		if (clip == null) return;
		stop();
		clip.getFramePosition();
		clip.start();
		
		if (loop) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			System.out.println("     ---[Audio track set to loop]");
		}
		
	}
	
	public void stop() {
		if (clip.isRunning()) clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
}
