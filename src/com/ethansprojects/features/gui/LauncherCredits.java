package com.ethansprojects.features.gui;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.ethansprojects.engine.Zionus;

/**
 * Launcher "credits" menu.
 * @author ethansprojects
 */

public class LauncherCredits {
	
	private int width = 800;
	private int height = 400;
	private JButton close;
	private JLabel lsounds, lhelp, soundContributor1, soundContributor2, progammingContributor1;
	private Rectangle rClose;
	private Choice screenSize = new Choice();
	
	Config config = new Config();
	
	int w = 0;
	int h = 0;
	private int button_width = 80;
	private int button_height = 40;
	
	JFrame frame = new JFrame();
	JPanel window = new JPanel();
	
	public LauncherCredits() {
		frame.setUndecorated(false);
		frame.setTitle("Zionus - Credits");
		frame.setSize(new Dimension(width, height));
		frame.add(window);
		frame.getContentPane().add(window);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.requestFocus();
		window.setLayout(null);
		creditsMenu();
		window.repaint();
		System.out.println("Credits menu opened");
		
	}
	
	public void creditsMenu() {
		
		lsounds = new JLabel("Sounds");
		lsounds.setBounds(8, 8, 100, 20);
		lsounds.setFont(new Font("Serif", Font.BOLD, 20));
		window.add(lsounds);
		
		soundContributor1 = new JLabel("Wind sound effect - Lanea Zimmerman");
		soundContributor1.setBounds(8, 28, 300, 20);
		window.add(soundContributor1);
		
		soundContributor2 = new JLabel("Projectile impact sound effect - dklon");
		soundContributor2.setBounds(8, 40, 300, 20);
		window.add(soundContributor2);
		
		lhelp = new JLabel("Programming Help");
		lhelp.setBounds(8, 60, 300, 20);
		lhelp.setFont(new Font("Serif", Font.BOLD, 20));
		window.add(lhelp);
		
		progammingContributor1 = new JLabel("Game structure - Yan Chernikov (thecherno.com)");
		progammingContributor1.setBounds(8, 78, 300, 20);
		window.add(progammingContributor1);
		
		soundContributor1 = new JLabel("Artificial Intelligence - Jeffrey S. (instaedu.com/online-tutors/Jeffrey-S-220013)");
		soundContributor1.setBounds(8, 90, 400, 20);
		window.add(soundContributor1);
		
		close = new JButton("Close");
		rClose = new Rectangle((width - 98), (height - 70), button_width, button_height - 10);
		close.setBounds(rClose);
		window.add(close);
		
		close.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Zionus.selection = screenSize.getSelectedIndex();
				frame.dispose();
				Launcher.creditsOpen = false;
			}
		});
		
	}
	
}
