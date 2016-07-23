package com.ethansprojects.features.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import com.ethansprojects.engine.Zionus;

/**
 * Player save stating.
 * @author ethansprojects
 */

public class CharacterSaving {
	
	static File inputFile = new File("character.txt");
	
	// Set these variables equal to whatever they happen to equal the moment the player saves
	public static String username = "Player";
	public static int total_level = 16;
	public static String user_class = "";
	public static int currentHealth = 200;
	public static int maxHealth = 200;
	public static int attack = 1;
	public static int strength = 1;
	public static int defense = 1;
	public static int ranged = 1;
	public static int magic = 1;
	public static int healthXP = 0;
	public static int attackXP = 0;
	public static int defenseXP = 0;
	public static int strengthXP = 0;
	public static int rangedXP = 0;
	public static int magicXP = 0;
	public static int coins = 0;
	
	private static Date date;
	
	// Call this method when you want to save stats
	public static void save() throws FileNotFoundException {
		
		PrintWriter out = new PrintWriter("character.txt");
		
		// If statement is executed if player has never saved before (may be useful)
		if (!inputFile.exists()) System.out.println("Character file has been created.");
		
		System.out.println("Writing output to " + inputFile.getPath() + "...");
		// Each command below is writing a new line in the text file
		out.println("[Last saved on " + (new Timestamp(date.getTime())) + " for character" + Zionus.username + "]");
		out.println("<User class> " + user_class);
		out.println("<Total Level> " + total_level);
		out.println("<Health> " + currentHealth);
		out.println("<Max Health> " + maxHealth);
		out.println("<Attack> " + attack);
		out.println("<Strength> " + strength);
		out.println("<Defense> " + defense);
		out.println("<Coins> " + coins);
		out.close();
		
	}
	
	// Load this whenever you start the game.
	// Self-explanatory, loads the saved stats.
	public static void load() throws FileNotFoundException {
		Scanner in = new Scanner(inputFile);
		
		// Resetting all of these to default values to make sure they get reset by the text file
		// and not by the code itself... if that makes sense.
		currentHealth = 0;
		maxHealth = 0;
		attack = 0;
		strength = 0;
		defense = 0;
		coins = 1;
		
		String text = "";
		
		// Admit it, this for loop is pure genius
		for (int i = 0; i < 7; i++) {
			text = in.nextLine();
			
			// Setting variables to the values in the text file. Each if statement is a new line in the text file :)
			//                      if (i == 0) speed = Double.parseDouble((text.substring(8)));
			if (i == 1) currentHealth = Integer.parseInt((text.substring(9)));
			if (i == 2) maxHealth = Integer.parseInt((text.substring(13)));
			if (i == 3) attack = Integer.parseInt((text.substring(9)));
			if (i == 4) strength = Integer.parseInt((text.substring(11)));
			if (i == 5) defense = Integer.parseInt((text.substring(10)));
			if (i == 6) coins = Integer.parseInt((text.substring(8)));
			
		}
		
		in.close();
		
		// Printing the results
		System.out.println("---");
		System.out.println("Current health set as " + currentHealth + ".");
		System.out.println("Max health set as " + maxHealth + ".");
		System.out.println("Attack set as " + attack + ".");
		System.out.println("Strength set as " + strength + ".");
		System.out.println("Defense set as " + defense + ".");
		System.out.println("Coins set to " + coins + ".");
		
	}
	
}