package com.ethansprojects.features.gui;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Launcher "options" menu.
 * @author ethansprojects
 */

public class LauncherOptions {

	private int width = 600;
	private int height = 300;
	private int button_width = 80;
	private int button_height = 40;
	private JButton save, cancel, create;
	private Rectangle rSave, rCancel, rRes;
	private JLabel lcreate, lcharacters, lresolution, lresabout1, lresabout2, lwidth, lheight;
	private JTextField newName;
	private Choice screenSize = new Choice();
	private Choice newClass = new Choice();
	
	Properties properties = new Properties();
	
	Config config = new Config();

	int w = 0;
	int h = 0;

	JFrame frame = new JFrame();
	JPanel window = new JPanel();
	
	
	public LauncherOptions() {
		frame.setUndecorated(false);
		frame.setTitle("Zionus - Select Loadout");
		frame.setSize(new Dimension(width, height));
		frame.add(window);
		frame.getContentPane().add(window);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.requestFocus();
		frame.toFront();
		window.setLayout(null);
		optionsMenu();
		window.repaint();
		System.out.println("Options menu opened");
		
		
	}

	public void optionsMenu() {
		
		
		lcharacters = new JLabel("Select a character to play as:");
		lcharacters.setBounds(350, 4, 250, 20);
		lcharacters.setFont(new Font("Serif", Font.PLAIN, 20));
		window.add(lcharacters);

		lcreate = new JLabel("Create a new character:");
		lcreate.setBounds(8, 135, 250, 20);
		lcreate.setFont(new Font("Serif", Font.PLAIN, 20));
		window.add(lcreate);

		newName = new JTextField("Enter new character name");
		newName.setBounds(8, 170, 250, 20); 
		window.add(newName);
		
		newClass.add("--Select a Class--");
		newClass.add("Warmage");
		newClass.add("Warrior");
		newClass.add("Tank");
		newClass.add("Assassin");
		newClass.setBounds(8, 200, 250, 40);
		window.add(newClass);
		
		lwidth = new JLabel("Custom width: (not yet supported)");
		lwidth.setBounds(8, 32, 300, 20);
		window.add(lwidth);
		
		lheight = new JLabel("Custom height: (not yet supported)");
		lheight.setBounds(8, 57, 300, 20);
		window.add(lheight);
		
		lresolution = new JLabel("Screen size: ");
		lresolution.setBounds(8, 4, 102, 20);
		lresolution.setFont(new Font("Serif", Font.PLAIN, 20));
		window.add(lresolution);
		
		lresabout1 = new JLabel("Note: Resolution will stay consistent at 850 x 478 pixels.");
		lresabout2 = new JLabel("This simply changes the size of the screen.");
		lresabout1.setBounds(8, 80, 300, 20);
		lresabout2.setBounds(8, 94, 300, 20);
		window.add(lresabout1);
		window.add(lresabout2);

		rRes = new Rectangle(109, 8, 75, 25);
		screenSize.setBounds(rRes);
		screenSize.add("650 x 366"); //scale = 0.7647058823529412
		screenSize.add("850 x 478"); //scale = 1 (RECOMMENDED, NATIVE, POPULAR)
		screenSize.add("1000 x 563"); //scale = 1.176470588235294
		screenSize.add("1700 x 956"); //scale = 2 (RECOMMENDED, POPULAR)
		screenSize.add("2400 x 1350"); //scale = 2.823529411764706 (POPULAR)
		screenSize.add("3700 x 2081"); //scale = 4.352941176470588
		screenSize.add("7500 x 4219"); //scale = 8.823529411764706
		screenSize.add("15000 x 8640"); //scale = 17.64705882352941
		screenSize.select(1);		
		window.add(screenSize);
		
		create = new JButton("Create character");
		create.setBounds(8, 230, 120, 20);
		window.add(create);
		
		save = new JButton("Save");
		rSave = new Rectangle((width - 175), (height - 63), button_width, button_height - 10);
		save.setBounds(rSave);
		window.add(save);
		
		cancel = new JButton("Cancel");
		rCancel = new Rectangle((width - 90), (height - 63), button_width, button_height - 10);
		cancel.setBounds(rCancel);
		window.add(cancel);
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.saveConfiguration("selection", screenSize.getSelectedIndex());
				frame.dispose();
				Launcher.optionsOpen = false;

			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Launcher.optionsOpen = false;

			}
		});
		
	}

}
