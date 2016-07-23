package com.ethansprojects.features.debug;

import java.awt.event.KeyEvent;
import com.ethansprojects.engine.Zionus;
import com.ethansprojects.engine.entity.Entity;
import com.ethansprojects.engine.graphics.Font;
import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.physics.input.Keyboard;
import com.ethansprojects.features.dialogue.Dialogue;

/**
 * Debugging/developer commands.
 * @author ethansprojects
 */

public class Commands {
	
	private Keyboard input;
	public static boolean commandHUD = false;
	
	public static Font blackFont = new Font("/fonts/system_black.png", 8);
	public static Font redFont = new Font("/fonts/system_red.png", 8);
	public static Font yellowFont = new Font("/fonts/system_yellow.png", 8);
	
	private String command = "";
	private String output = "";
	private boolean displayInfo = false;
	
	public Commands(Keyboard input) {
		this.input = input;
	}
	
	public void update() {
		toggleCommandLine();
		checkForCommands();
	}
	
	private void toggleCommandLine() {
		if (Zionus.player.personSpeaking.equals("")) {
			if (input.keyTyped(KeyEvent.VK_BACK_SLASH)) {
				if (!commandHUD) {
					commandHUD = true;
					System.out.println("HUD on");
				} else {
					commandHUD = false;
					System.out.println("HUD off");
				}
			}
		} else {
			commandHUD = false;
		}
	}
	
	private void checkForCommands() {
		if (commandHUD) {
			decipherInput();
		} else {
			Zionus.player.frozen = false;
		}
	}
	
	private void decipherInput() {
		String newInput = "";
		
		if (input.keyTyped(KeyEvent.VK_0)) {
			newInput = "0";
		} else if (input.keyTyped(KeyEvent.VK_1)) {
			newInput = "1";
		} else if (input.keyTyped(KeyEvent.VK_2)) {
			newInput = "2";
		} else if (input.keyTyped(KeyEvent.VK_3)) {
			newInput = "3";
		} else if (input.keyTyped(KeyEvent.VK_4)) {
			newInput = "4";
		} else if (input.keyTyped(KeyEvent.VK_5)) {
			newInput = "5";
		} else if (input.keyTyped(KeyEvent.VK_6)) {
			newInput = "6";
		} else if (input.keyTyped(KeyEvent.VK_7)) {
			newInput = "7";
		} else if (input.keyTyped(KeyEvent.VK_8)) {
			newInput = "8";
		} else if (input.keyTyped(KeyEvent.VK_9)) {
			newInput = "9";
		} else if (input.keyTyped(KeyEvent.VK_A)) {
			newInput = "a";
		} else if (input.keyTyped(KeyEvent.VK_B)) {
			newInput = "b";
		} else if (input.keyTyped(KeyEvent.VK_C)) {
			newInput = "c";
		} else if (input.keyTyped(KeyEvent.VK_D)) {
			newInput = "d";
		} else if (input.keyTyped(KeyEvent.VK_E)) {
			newInput = "e";
		} else if (input.keyTyped(KeyEvent.VK_F)) {
			newInput = "f";
		} else if (input.keyTyped(KeyEvent.VK_G)) {
			newInput = "g";
		} else if (input.keyTyped(KeyEvent.VK_H)) {
			newInput = "h";
		} else if (input.keyTyped(KeyEvent.VK_I)) {
			newInput = "i";
		} else if (input.keyTyped(KeyEvent.VK_J)) {
			newInput = "j";
		} else if (input.keyTyped(KeyEvent.VK_K)) {
			newInput = "k";
		} else if (input.keyTyped(KeyEvent.VK_L)) {
			newInput = "l";
		} else if (input.keyTyped(KeyEvent.VK_M)) {
			newInput = "m";
		} else if (input.keyTyped(KeyEvent.VK_N)) {
			newInput = "n";
		} else if (input.keyTyped(KeyEvent.VK_O)) {
			newInput = "o";
		} else if (input.keyTyped(KeyEvent.VK_P)) {
			newInput = "p";
		} else if (input.keyTyped(KeyEvent.VK_Q)) {
			newInput = "q";
		} else if (input.keyTyped(KeyEvent.VK_R)) {
			newInput = "r";
		} else if (input.keyTyped(KeyEvent.VK_S)) {
			newInput = "s";
		} else if (input.keyTyped(KeyEvent.VK_T)) {
			newInput = "t";
		} else if (input.keyTyped(KeyEvent.VK_U)) {
			newInput = "u";
		} else if (input.keyTyped(KeyEvent.VK_V)) {
			newInput = "v";
		} else if (input.keyTyped(KeyEvent.VK_W)) {
			newInput = "w";
		} else if (input.keyTyped(KeyEvent.VK_X)) {
			newInput = "x";
		} else if (input.keyTyped(KeyEvent.VK_Y)) {
			newInput = "y";
		} else if (input.keyTyped(KeyEvent.VK_Z)) {
			newInput = "z";
		} else if (input.keyTyped(KeyEvent.VK_SPACE)) {
			newInput = " ";
		} else if (input.keyTyped(KeyEvent.VK_COMMA)) {
			newInput = ",";
		}
		
		if (input.shift) {
			newInput = newInput.toUpperCase();
			
			switch (newInput) {
				case "1":
					newInput = "!";
					break;
				case "2":
					newInput = "@";
					break;
				case "4":
					newInput = "$";
					break;
				case "7":
					newInput = "&";
					break;
				case "9":
					newInput = "(";
					break;
				case "0":
					newInput = ")";
					break;
			}
			
			if (command.length() > 0 && input.keyTyped(KeyEvent.VK_BACK_SPACE)) {
				command = "";
			}
		} else {
			if (command.length() > 0 && input.keyTyped(KeyEvent.VK_BACK_SPACE)) {
				command = command.substring(0, command.length() - 1);
			}
		}
		
		if (input.keyTyped(KeyEvent.VK_ENTER)) {
			runCommand();
		} else {
			
			if (command.length() <= 99) {
				command = command + newInput;
			}
		}
	}
	
	private void runCommand() {
		System.out.println("[debug-command] " + command);
		
		if (command.toLowerCase().startsWith("spawn")) {
			try {
				int endOfObjectName = 6 + (command.substring(6).toLowerCase().indexOf(" "));
				int endOfAmount = (endOfObjectName + 1) + (command.substring(endOfObjectName + 1).toLowerCase().indexOf("("));
				int xLoc = endOfAmount + 1;
				int yLoc = xLoc + (command.substring(xLoc).toLowerCase().indexOf(",") + 2);
				
				String object = command.toLowerCase().substring(6, endOfObjectName);
				String amount = command.toLowerCase().substring(endOfObjectName + 1, xLoc - 2);
				String x = command.toLowerCase().substring(xLoc, yLoc - 2);
				String y = command.toLowerCase().substring(yLoc, (yLoc + command.substring(yLoc).toLowerCase().indexOf(")")));
				
				spawn(object, Integer.parseInt(amount), Integer.parseInt(x), Integer.parseInt(y));
			} catch (Exception e) {
				e.printStackTrace();
				output = "Enter exactly as:\nspawn -entity -amount (-x, -y)";
				System.out.println("Enter exactly as: 'spawn [entity] [amount] ([x], [y])'");
			}
		} else if (command.toLowerCase().equalsIgnoreCase("info")) {
			
			if (!displayInfo) {
				output = "-enabled- information display";
				displayInfo = true;
			} else {
				output = "-disabled- information display";
				displayInfo = false;
			}
			
		} else if (command.toLowerCase().startsWith("tele")) {
			try {
				int xLoc = 5;
				int yLoc = xLoc + (command.substring(xLoc).toLowerCase().indexOf(" ") + 1);
				
				String x = command.toLowerCase().substring(xLoc, yLoc - 1);
				String y = command.toLowerCase().substring(yLoc);
				
				tele(Integer.parseInt(x), Integer.parseInt(y));
			} catch (Exception e) {
				e.printStackTrace();
				output = "Enter exactly as:\ntele -x -y";
				System.out.println("Enter exactly as: 'tele [x] [y]'");
			}
		} else if (command.toLowerCase().startsWith("setstage")) {
			try {
				String newStage = command.toLowerCase().substring(9);
				
				setStage(Integer.parseInt(newStage));
			} catch (Exception e) {
				e.printStackTrace();
				output = "Enter exactly as:\nsetstage -stage";
				System.out.println("Enter exactly as: 'setstage [new stage]'");
			}
		} else {
			output = "Command does not exist.";
		}
		command = "";
	}
	
	private void setStage(int newStage) {
		Zionus.ACT_stage = newStage;
	}
	
	private void tele(int x, int y) {
		Zionus.player.x = x;
		Zionus.player.y = y;
	}
	
	private void spawn(String object, int amount, int x, int y) {
		Entity e = null;
		
		switch (object) {
			case "nephyr":
				//				e = new Nephyr(x, y);
				break;
			
			case "demon":
				//				e = new Demon(x, y);
				break;
			
			default:
				output = "Entity '" + object + "' does not exist.";
				break;
		}
		
		for (int i = 0; i < amount; i++) {
			if (e != null) {
				output = "";
				Zionus.level.add(e);
			}
		}
		
	}
	
	public void render(Screen screen) {
		if (commandHUD) {
			Dialogue.dialogueBox("command", screen);
			renderSeparateLines(screen);
			renderOutputMessages(screen);
		}
		renderInfoDisplay(screen);
	}
	
	private void renderInfoDisplay(Screen screen) {
		if (displayInfo) {
			yellowFont.render("X: " + Zionus.player.getX() + ", Y: " + Zionus.player.getY(), 10, 10, screen);
			yellowFont.render("Chapter " + Zionus.ACT, 10, 20, screen);
			yellowFont.render("Act stage: " + Zionus.ACT_stage, 10, 30, screen);
		}
	}
	
	private void renderOutputMessages(Screen screen) {
		if (!output.equals("")) {
			redFont.render(output, Dialogue.fontXscreenLoc, Dialogue.fontYscreenLoc + 38, screen);
		}
	}
	
	private void renderSeparateLines(Screen screen) {
		if (command.length() < 33) {
			blackFont.render(command, Dialogue.fontXscreenLoc, Dialogue.fontYscreenLoc, screen);
			if (Zionus.oneSecondTimer % 2 == 0) {
				blackFont.render("_", (Dialogue.fontXscreenLoc + (command.length() * 8)) + 3, Dialogue.fontYscreenLoc, screen);
			}
		} else if (command.length() >= 33 && command.length() < 66) {
			blackFont.render(command.substring(0, 33), Dialogue.fontXscreenLoc, Dialogue.fontYscreenLoc, screen);
			blackFont.render(command.substring(33), Dialogue.fontXscreenLoc, Dialogue.fontYscreenLoc + 12, screen);
			if (Zionus.oneSecondTimer % 2 == 0) {
				blackFont.render("_", (Dialogue.fontXscreenLoc + (command.substring(33).length() * 8)) + 3, Dialogue.fontYscreenLoc + 12, screen);
			}
		} else if (command.length() >= 66) {
			blackFont.render(command.substring(0, 33), Dialogue.fontXscreenLoc, Dialogue.fontYscreenLoc, screen);
			blackFont.render(command.substring(33, 66), Dialogue.fontXscreenLoc, Dialogue.fontYscreenLoc + 12, screen);
			blackFont.render(command.substring(66), Dialogue.fontXscreenLoc, Dialogue.fontYscreenLoc + 24, screen);
			if (Zionus.oneSecondTimer % 2 == 0) {
				blackFont.render("_", (Dialogue.fontXscreenLoc + (command.substring(66).length() * 8)) + 3, Dialogue.fontYscreenLoc + 24, screen);
			}
		}
	}
	
}
