package com.ethansprojects.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Loads and renders custom fonts.
 * @author ethansprojects
 */

public class Font {
	
	private int[] pixels;
	private int w, h;
	private int[][] chars;
	private final int SIZE;
	
	// TODO: Add more characters here!
	private String characters = "ABCDEFGHIJKLM" + //
			"NOPQRSTUVWXYZ" + //
			"0123456789!. " + //
			// Lower characters and more punctuations
			"abcdefghijklm" + //
			"nopqrstuvwxyz" + //
			"'?,;:/-()&$@_";
	
	private int fontRows = 6, fontColumns = 13; // Rows and columns of the font image file!!
			
	public Font(String path, int size) {
		SIZE = size;
		load(path);
		generateChars();
	}
	
	private void load(String path) {
		try {
			BufferedImage image = ImageIO.read(Font.class.getResource(path));
			w = image.getWidth();
			h = image.getHeight();
			pixels = new int[w * h];
			// Change 6 * 13 to how many characters you have in the image!
			chars = new int[fontRows * fontColumns][SIZE * SIZE];
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void generateChars() {
		for (int y = 0; y < fontRows; y++) {
			for (int x = 0; x < fontColumns; x++) {
				for (int yy = 0; yy < SIZE; yy++) {
					int yo = yy + y * SIZE;
					for (int xx = 0; xx < SIZE; xx++) {
						int xo = xx + x * SIZE;
						chars[x + y * fontColumns][xx + yy * SIZE] = pixels[xo + yo * w];
					}
				}
			}
		}
	}
	
	public void render(String text, int x, int y, Screen screen) {
		int xOffset = 0;
		int yOffset = 0;
		for (int i = 0; i < text.length(); i++) {
			char currentChar = text.charAt(i);
			int index = characters.indexOf(currentChar);
			if (currentChar == '\n') {
				xOffset = 0;
				yOffset += SIZE + 2;
				continue;
			}
			if (index == -1) {
				System.err.println("Unable to find character '" + currentChar + "'");
				continue;
			}
			int[] pixels = chars[index];
			screen.renderChar(x + xOffset, y + yOffset, SIZE, pixels);
			xOffset += SIZE;
		}
	}
}
