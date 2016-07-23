package com.ethansprojects.features.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import com.ethansprojects.engine.Zionus;

/**
 * Player configurations.
 * @author ethansprojects
 */

public class Config {
	
	Properties properties = new Properties();
	
	public static String configFile = System.getProperty("user.home") + "/Zionus/config.xml";
	
	public void saveConfiguration(String key, int value) {
		
		try {
			
			File file = new File(configFile);
			boolean fileExist = file.exists();
			
			File folder = new File(System.getProperty("user.home") + "/Zionus/");
			
			if (!fileExist) {
				folder.mkdir();
				file.createNewFile();
			}
			OutputStream write = new FileOutputStream(configFile);
			properties.setProperty(key, Integer.toString(value));
			properties.storeToXML(write, "Settings for Zionus - please don't mess with this file");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot write new configurations file.");
		}
		
	}
	
	public void loadConfiguration(String path) {
		try {
			InputStream read = new FileInputStream(path);
			properties.loadFromXML(read);
			String selection = properties.getProperty("selection");
			Zionus.selection = Integer.parseInt(selection);
			
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("General I/O exception: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("General error: " + e.getMessage());
		}
	}
	
}
