package com.ethansprojects.features.debug;

import com.ethansprojects.engine.graphics.Screen;

/**
 * Developer/debugging features.
 * @author ethansprojects
 */

public class Debug {
	
	public static void drawRect(int x, int y, int width, int height, boolean fixed, Screen screen) {
		screen.drawRect(x, y, width, height, fixed);
	}
	
}
