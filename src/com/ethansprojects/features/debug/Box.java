package com.ethansprojects.features.debug;

/**
 * Draws a box on the game screen.
 * @author ethansprojects
 */

public class Box {
	
	public Vector2i x0, x1, y0, y1;
	
	public int x, y, width, height;
	
	public Box(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Box(Vector2i x0, Vector2i y0, Vector2i x1, Vector2i y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public static boolean inBox(int x, int y, Box box) {
		if (x >= box.x) {
			if (x <= box.x + box.width) {
				if (y >= box.y) {
					if (y <= box.y + box.height) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean inBox(Vector2i coordinate, Box box) {
		if (coordinate.x >= box.x0.x) {
			if (coordinate.x <= box.x1.x) {
				if (coordinate.y >= box.y0.y) {
					if (coordinate.y <= box.y1.y) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
