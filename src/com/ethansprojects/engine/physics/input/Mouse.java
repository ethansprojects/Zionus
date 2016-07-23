package com.ethansprojects.engine.physics.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Mouse input handling.
 * @author ethansprojects
 */

public class Mouse implements MouseListener, MouseMotionListener {
	
	private static int mouseX = -1;
	private static int mouseY = -1;
	public static int mouseDragX = -1;
	public static int mouseDragY = -1;
	public static int mousePressX = -1;
	public static int mousePressY = -1;
	public static int mouseB = -1;
	private static int mouseClick = -1;
	public static boolean dragged = false;
	
	public static int getX() {
		return mouseX;
	}
	
	public static int getY() {
		return mouseY;
	}
	
	public static int getButton() {
		return mouseB;
	}
	
	public void mouseClicked(MouseEvent me) {
		mouseClick = me.getClickCount();
	}
	
	public static int mouseClicked() {
		return mouseClick;
	}
	
	public void mousePressed(MouseEvent me) {
		mouseX = me.getX();
		mouseY = me.getY();
		mousePressX = me.getX();
		mousePressY = me.getY();
		mouseB = me.getButton();
	}
	
	public void mouseReleased(MouseEvent me) {
		mouseB = -1;
		dragged = false;
	}
	
	public void mouseEntered(MouseEvent me) {
	}
	
	public void mouseExited(MouseEvent me) {
	}
	
	public void mouseDragged(MouseEvent me) {
		mouseX = me.getX();
		mouseY = me.getY();
		mouseDragX = me.getX();
		mouseDragY = me.getY();
		dragged = true;
		
	}
	
	public void mouseMoved(MouseEvent me) {
		mouseX = me.getX();
		mouseY = me.getY();
		
	}
	
}
