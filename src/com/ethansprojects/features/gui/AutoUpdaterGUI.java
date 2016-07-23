package com.ethansprojects.features.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.ethansprojects.features.net.AutoUpdater;
import com.ethansprojects.engine.Zionus;

/**
 * GUI for the auto-updater.
 * @author ethansprojects
 */

public class AutoUpdaterGUI {
	
	static JFrame updaterFrame = new JFrame();
	static JPanel updaterWindow = new JPanel();
	static JLabel complete;
	static Rectangle rComplete;
	static boolean proceedToGame = false;
	static JProgressBar loading;
	static boolean error = false;
	
	static final int minimum = 0;
	static final int maximum = 100;
	
	public AutoUpdaterGUI() {
		addLoadingBar();
	}
	
	public static void autoUpdaterInterface() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		updaterFrame.setTitle("Checking for client updates...");
		updaterFrame.setResizable(false);
		JFrame.setDefaultLookAndFeelDecorated(true);
		updaterFrame.pack();
		updaterFrame.setSize(new Dimension(500, 100));
		updaterFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		updaterFrame.setLocationRelativeTo(null);
		updaterFrame.setVisible(true);
		updaterWindow.setLayout(null);
		updaterFrame.getContentPane().add(updaterWindow);
		
		try {
			if (!AutoUpdater.getLatestVersion().equals(Zionus.version)) {
				update();
				System.out.println("Client latest version: " + AutoUpdater.getLatestVersion());
			} else {
				updaterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				updaterFrame.setTitle("Client up to date. Starting client...");
				proceedToGame = true;
			}
		} catch (Exception ex) {
			error = true;
			ex.printStackTrace();
		}
		
		System.out.println("Client initially opened.");
		
		if (error) {
			updaterFrame.setResizable(false);
			JFrame.setDefaultLookAndFeelDecorated(true);
			updaterFrame.pack();
			updaterFrame.setSize(new Dimension(500, 100));
			updaterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			updaterFrame.setLocationRelativeTo(null);
			updaterFrame.setVisible(true);
			updaterFrame.setLocationRelativeTo(null);
			updaterFrame.requestFocus();
			updaterWindow.setLayout(null);
			updaterFrame.getContentPane().add(updaterWindow);
			updaterFrame.setTitle("Error: Please connect to the internet then restart the client.");
		}
		
		if (proceedToGame) {
			Zionus.startLauncher();
			updaterFrame.dispose();
		}
		
	}
	
	private static void addLoadingBar() {
		loading = new JProgressBar();
		loading.setMinimum(minimum);
		loading.setMaximum(maximum);
		loading.setBounds(5, 5, 485, 62);
		updaterWindow.add(loading);
	}
	
	private void updateLoadingBar(int newValue) {
		loading.setValue(newValue);
	}
	
	private static void update() throws IOException {
		
		final AutoUpdaterGUI it = new AutoUpdaterGUI();
		
		updaterFrame.setResizable(false);
		JFrame.setDefaultLookAndFeelDecorated(true);
		updaterFrame.pack();
		updaterFrame.setSize(new Dimension(500, 100));
		updaterFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		updaterFrame.setVisible(true);
		updaterFrame.requestFocus();
		updaterWindow.setLayout(null);
		updaterFrame.getContentPane().add(updaterWindow);
		
		for (int i = minimum; i <= maximum; i = i + 4) {
			final int percent = i;
			try {
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						it.updateLoadingBar(percent);
						updaterFrame.setTitle("Updating Zionus client " + percent + "% (1 of 1)");
						if (percent == 100) {
							proceedToGame = true;
							
							System.out.println("100%");
						}
					}
				});
				java.lang.Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.err.println("InterruptedException: " + e.getMessage());
			}
			
		}
		new AutoUpdater();
		updaterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updaterFrame.setTitle("Updating complete! Please restart your client.");
	}
	
}
