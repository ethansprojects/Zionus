package com.ethansprojects.engine.physics.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Keyboard input handling.
 * @author ethansprojects
 */

public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right, space, esc, enter, tab, backSlash;
	public boolean shift;
	
	private List<Integer> pressed = new ArrayList<Integer>();
	
	public void update() {
		// Accepts key inputs for WASD and arrow keys
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		shift = keys[KeyEvent.VK_SHIFT];
		space = keys[KeyEvent.VK_SPACE];
		enter = keys[KeyEvent.VK_ENTER];
		tab = keys[KeyEvent.VK_TAB];
		esc = keys[KeyEvent.VK_ESCAPE];
		backSlash = keys[KeyEvent.VK_BACK_SLASH];
		
	}
	
	public void keyTyped(KeyEvent ke) {
	}
	
	public boolean keyTyped(int keycode) {
		if (pressed.contains(new Integer(keycode))) return false;
		boolean keyPressed = keys[keycode];
		if (keyPressed) {
			pressed.add(new Integer(keycode));
		}
		return keyPressed;
	}
	
	public void keyPressed(KeyEvent ke) {
		keys[ke.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent ke) {
		keys[ke.getKeyCode()] = false;
		if (pressed.contains(ke.getKeyCode())) {
			pressed.remove(new Integer(ke.getKeyCode()));
		}
	}
	
}
