package com.ethansprojects.engine;

import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.features.debug.Commands;
import com.ethansprojects.features.gui.Launcher;
import com.ethansprojects.engine.physics.input.Keyboard;
import com.ethansprojects.engine.physics.input.Mouse;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import com.ethansprojects.features.maps.Level;
import com.ethansprojects.features.maps.PrologueLevel;
import com.ethansprojects.features.maps.TileCoordinate;
import com.ethansprojects.engine.audio.AudioPlayer;
import com.ethansprojects.engine.entity.mob.npc.Nephyr;
import com.ethansprojects.engine.entity.mob.player.Player;

/**
 * Game engine.
 * @author ethansprojects
 */

public class Zionus extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static int ACT = 0; // Prologue = act 0, Final act = act 7
	public static int ACT_stage = 0; // Start of game = 0;
	
	public static String version = "1.0";
	
	public static int cursorType = 0;
	
	public static boolean music = true;
	
	public static boolean SFX = true;
	
	public static String title = "Zionus Pre-Alpha";
	
	public static String username = "Jamison"; // 1 - 16 characters, no punctuation
												// assassin
	public static int user_ttl_lvl; // Total level; sum of all skills
									// combined (15 - 600)
	public static String user_rank = "user rank N-A."; // Based on level; look
														// below
	public static String user_status = "Lead Developer";
	
	public static int xScroll;
	
	public static int yScroll;
	
	// Game window resolution
	private static int width = 850;
	private static int height = width / 16 * 9;
	
	public static int selection = 1;
	
	public static double scale = 1; // Size of the window;
									// independent of dimensions
	private Thread thread;
	
	public static AudioPlayer tutorialMusic;
	public AudioPlayer windNoise;
	
	public JFrame frame;
	
	private static Keyboard key;
	public static Level level;
	public static Commands commands;
	
	public static boolean devMode = true;
	
	public static Player player;
	public static Nephyr nephyr;
	
	public static boolean mapDarkness = false; // Set in Screen.java
	
	private boolean running = false;
	
	public static int middleScreenX = (int) (((Zionus.getWindowWidth() / Zionus.scale)) / 2);
	public static int middleScreenY = (int) (((Zionus.getWindowHeight() / Zionus.scale)) / 2);
	
	public static Screen screen;
	
	private static Launcher Launcher;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Creates image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Accesses image
	
	public static int oneSecondTimer = 0;
	public static int combatTimer = 0;
	public static int demonTimer = 0;

	private long lastTime = System.nanoTime();
	private static long timerForSeconds = System.currentTimeMillis();
	private static long timerForHalfSeconds = System.currentTimeMillis();
	private static long timerForThirdSeconds = System.currentTimeMillis();
	private final double ns = 1000000000.0 / 60.0;
	private double delta = 0;
	private int frames = 0;
	private int updates = 0;
	
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Point point = new Point(0, 0);

	
	public Zionus() {
		// new MainMenu();
		setParameters();
		level.add(player);
		addKeyListener(key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
	}
	
	private void setParameters() {
		cursorType = 0;
		Dimension size = new Dimension((int) (width * getScale()), (int) (height * getScale()));
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		commands = new Commands(key);
		
		switch (ACT) {
			case 0:
				setLevel(new PrologueLevel(), 116, 14);
				break;
			case 1:
				// Load act 1 level
				break;
			case 2:
				// Load act 2 level
				break;
			case 3:
				// Load act 3 level
				break;
			case 4:
				// Load act 4 level
				break;
			case 5:
				// Load act 5 level
				break;
			case 6:
				// Load act 6 level
				break;
			case 7:
				// Load final act level
				break;
		}
		
	}

	public static void setLevel(Level lvl, int spawnX, int spawnY) {
		if (getLevel() == null) {
			level = lvl;
			TileCoordinate levelSpawn = new TileCoordinate(spawnX, spawnY);
			player = new Player(levelSpawn.x(), levelSpawn.y(), key);
			nephyr = Zionus.level.getNephyrAt(Nephyr.memoryID);
		} else {
			// Load new level
		}
		
	}
	
	public static Level getLevel() {
		return level;
	}
	
	public static double getScale() {
		if (selection == 0) scale = 0.7647058823529412;
		if (selection == 1 || selection == -1) scale = 1;
		if (selection == 2) scale = 1.176470588235294;
		if (selection == 3) scale = 2;
		if (selection == 4) scale = 2.823529411764706;
		if (selection == 5) scale = 4.352941176470588;
		if (selection == 6) scale = 8.823529411764706;
		if (selection == 7) scale = 17.64705882352941;
		return scale;
	}
	
	public static double getWindowWidth() {
		return width * getScale();
	}
	
	public static double getWindowHeight() {
		return height * getScale();
	}
	
	public synchronized void start() throws FileNotFoundException {
		running = true;
		thread = new Thread(this, "game");
		thread.start();
		//		CharacterSaving.load();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		requestFocus(); // Highlights window automatically
		
		while (running) {
			loop();
		}
		windNoise.close();
		System.out.println("Wind SFX closed");
		stop();
	}
	
	private void loop() {
		systemTimers();
		setFrames();
	}

	private void setFrames() {
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		while (delta >= 1) {
			update();
			updates++;
			delta--;
		}
		render();
		frames++;
	}
	
	private void systemTimers() {
		int SFXtimer = 0;
		int random = 1 + (int) (Math.random() * ((100 - 1) + 1));
		
		if (combatTimer > 7500) {
			combatTimer = 0;
		}
		
		if (demonTimer > 7500) {
			demonTimer = 0;
		}
		
		// One second
		if (System.currentTimeMillis() - timerForSeconds > 1000) {
			oneSecondTimer++;
			combatTimer++;
			timerForSeconds += 1000; // Makes the timer increase once per second
			frame.setTitle(title + " | DEVELOPER TOOLS: ~~~ " + updates + " UPS, " + frames + " FPS ~~~ ");
			updates = 0;
			frames = 0;
			SFXtimer++;
		}

		// Half seconds
		if (System.currentTimeMillis() - timerForHalfSeconds > 500) {
			timerForHalfSeconds += 500;
		}
		
		// Quarter seconds
		if (System.currentTimeMillis() - timerForThirdSeconds > 333) {
			demonTimer++;
			timerForThirdSeconds += 333;
		}
		
		if (random * SFXtimer == 2) {
			windNoise = new AudioPlayer("/audio/sfx/ambient/wind0.wav");
			if (SFX) windNoise.play(false);
			System.out.println("Wind SFX played");
		}		
	}

	public void update() {
		key.update();
		try {
			level.update();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		tripleBuffer();
		xScroll = player.getX() - screen.width / 2;
		yScroll = player.getY() - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		commands.update();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
	}
	
	/**
	 * Triple buffering is rendering the two next frames before displaying them. Anything beyond 3 buffers is superfluous.
	 * This supposedly is good for performance.
	 */
	private void tripleBuffer() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Image blank_cursor = toolkit.getImage("res/cursor_hover.png");
		Cursor cursor_default = toolkit.createCustomCursor(blank_cursor, point, "Cursor (blank)");
		frame.getContentPane().setCursor(cursor_default);
		
		Graphics g = bs.getDrawGraphics(); // Links buffering and graphics drawing
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); // Removes previous unused graphics frames
		bs.show(); // Displays the current graphics frame
		screen.clear(); // Clears pixels after each buffer to maximize performance
	}

	public static Launcher startLauncher() {
		if (Launcher == null) {
			Launcher = new Launcher();
			System.out.println("Welcome to Zionus. [programmed by Ethan Crist]");
		}
		return Launcher;
	}
	
	public static void main(String[] args) {
		// AutoUpdaterGUI.autoUpdaterInterface();
		startLauncher();
	}
	
}