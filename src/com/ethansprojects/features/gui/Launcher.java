package com.ethansprojects.features.gui;

import com.ethansprojects.engine.physics.input.Keyboard;
import com.ethansprojects.engine.physics.input.Mouse;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.audio.AudioPlayer;

/**
 * The game launcher.
 * @author ethansprojects
 */

public class Launcher extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean characterChosen = true;
	
	Config config = new Config();
	private JPanel window = new JPanel();
	private int width = 900;
	private int height = 500;
	public static boolean optionsOpen = false, creditsOpen = false;
	private static AudioPlayer launchMusic;
	boolean running = false;
	boolean siteOpened = false;
	Thread thread;
	
	private boolean startGame = false;
	
	//	static JFrame frame = new JFrame();
	
	public Launcher() {
		setParameters();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Zionus.music) {
			launchMusic.play(true);
		}
		startMenu();
		
	}
	
	private void setParameters() {
		setUndecorated(true);
		setTitle("Zionus - Launcher");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(window);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		window.setLayout(null);
		
		Mouse mouse = new Mouse();
		Keyboard key = new Keyboard();
		
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		launchMusic = new AudioPlayer("/audio/music/loading.wav");
	}
	
	public void renderMenu() {
		if (startGame) {
			startGame();
		}
		tripleBuffer();
	}
	
	private void tripleBuffer() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // Triple buffering
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 900, 500);
		try {
			g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/launcher_background.png")), 0, 0, 900, 500, null);
			
			if (characterChosen && Mouse.getX() >= 30 && Mouse.getX() <= 125 && Mouse.getY() >= 250 && Mouse.getY() <= 286) {
				
				g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/buttons/play_on.png")), -68, 245, 300, 50, null);
				
				if (Mouse.getButton() == 1) {
					if (!optionsOpen && !creditsOpen) {
						startGame = true;
					} else {
						System.err.println("Close the current menu before doing that!");
					}
				}
				
			} else {
				
				g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/buttons/play_off.png")), -68, 245, 300, 50, null);
				
			}
			
			if (Mouse.getX() >= 30 && Mouse.getX() <= 212 && Mouse.getY() >= 314 && Mouse.getY() <= 348) {
				
				g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/buttons/options_on.png")), -31, 307, 300, 50, null);
				
				if (Mouse.getButton() == 1) {
					if (!optionsOpen && !creditsOpen) {
						new LauncherOptions();
						optionsOpen = true;
					} else {
						System.err.println("Close the current menu before doing that!");
					}
				}
			} else {
				
				g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/buttons/options_off.png")), -31, 307, 300, 50, null);
				
			}
			
			if (Mouse.getX() >= 30 && Mouse.getX() <= 199 && Mouse.getY() >= 377 && Mouse.getY() <= 413) {
				
				g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/buttons/credits_on.png")), -40, 372, 300, 50, null);
				
				if (Mouse.getButton() == 1) {
					if (!optionsOpen && !creditsOpen) {
						new LauncherCredits();
						creditsOpen = true;
					} else {
						System.err.println("Close the current menu before doing that!");
					}
				}
				
			} else {
				g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/buttons/credits_off.png")), -40, 372, 300, 50, null);
			}
			
			if (Mouse.getX() >= 30 && Mouse.getX() <= 120 && Mouse.getY() >= 438 && Mouse.getY() <= 474) {
				
				g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/buttons/exit_on.png")), -80, 432, 300, 50, null);
				
				if (Mouse.getButton() == 1) {
					launchMusic.close();
					System.exit(0);
				}
			} else {
				g.drawImage(ImageIO.read(Zionus.class.getResource("/launcher/buttons/exit_off.png")), -80, 432, 300, 50, null);
			}
			
			if ((Mouse.getX() >= 739 && Mouse.getX() <= 889 && Mouse.getY() >= 483 && Mouse.getY() <= 492) || (Mouse.getX() >= 721 && Mouse.getX() <= 889 && Mouse.getY() >= 468 && Mouse.getY() <= 483)) {
				if (Mouse.getButton() == 1) {
					
					if (Desktop.isDesktopSupported()) {
						final URI uri = new URI("http://ethansprojects.com");
						if (!siteOpened) Desktop.getDesktop().browse(uri);
						siteOpened = true;
						
					} else {
						System.err.println("General error: desktop not supported. :(");
					}
					
				}
				
			} else {
				siteOpened = false;
			}
			
		} catch (IOException ex) {
			System.err.println("IO Exception: " + ex.getMessage());
			ex.printStackTrace();
		} catch (URISyntaxException e) {
			System.err.println("URISyntaxException: " + e.getMessage());
		}
		
		g.dispose();
		bs.show();
	}
	
	private void startGame() {
		config.loadConfiguration(Config.configFile);
		launchMusic.close();
		dispose();
		try {
			new StartGame();
			System.out.println("Game screen size set to: " + (int) (Zionus.getWindowWidth()) + " x " + (int) (Zionus.getWindowHeight()));
		} catch (IOException e) {
			System.err.println("Failed to start the game.");
			e.printStackTrace();
		}
	}
	
	public void updateInterface() {
		if (Mouse.dragged) {
			Point p = getLocation();
			setLocation(p.x + Mouse.mouseDragX - Mouse.mousePressX, p.y + Mouse.mouseDragY - Mouse.mousePressY);
		}
		
	}
	
	public void startMenu() {
		running = true;
		thread = new Thread(this, "menu");
		thread.start();
		
	}
	
	public void stopMenu() {
		try {
			thread.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public void run() {
		requestFocus();
		while (running) {
			try {
				renderMenu();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			updateInterface();
		}
		
	}
	
}
