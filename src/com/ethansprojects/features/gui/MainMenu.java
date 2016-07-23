package com.ethansprojects.features.gui;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The main menu screen after the game loads.
 * @author ethansprojects
 */

public class MainMenu {
	
	private static ImageIcon menu = new ImageIcon(MainMenu.class.getResource("main/background.png"));
	
	public MainMenu() {
		getMenuSplash();
	}
	
	public static Image getMenuSplash() {
		return menu.getImage();
	}
	
}
