package com.ethansprojects.features.gui;

import com.ethansprojects.engine.physics.input.Mouse;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.ethansprojects.engine.Zionus;

/**
 * Creates new instance of the main game.
 * @author ethansprojects
 */

public class StartGame {
	
	public StartGame() throws IOException {
		Mouse.mouseB = -1;
		Zionus game = new Zionus();
		game.frame.setResizable(true);
		game.frame.setTitle(Zionus.title);
		game.frame.add(game);
		game.frame.pack(); //Makes dimensions be the same as the Java window dimensions
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		ImageIcon logo = new ImageIcon("res/textures/interface/logo.png");
		game.frame.setIconImage(logo.getImage());
		
		game.start();
		stopMenuThread();
		
	}
	
	private void stopMenuThread() {
		Zionus.startLauncher().stopMenu();
	}
	
}
