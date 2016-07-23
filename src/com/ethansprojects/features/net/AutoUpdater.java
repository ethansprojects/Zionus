package com.ethansprojects.features.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Auto-updates the game at system launch.
 * @author ethansprojects
 */

public class AutoUpdater {
	
	private final static String versionURL = "http://ethansprojects.com/downloads/games/version.txt";
	
	public AutoUpdater() throws IOException {
		download();
	}
	
	private static void download() throws IOException {
		URL updateServer = new URL("http://ethansprojects.com/downloads/games/Zionus.jar");
		ReadableByteChannel rbc = Channels.newChannel(updateServer.openStream());
		File file = new File("Zionus.jar");
		FileOutputStream fos = new FileOutputStream(file);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
	}
	
	public static String getLatestVersion() throws IOException {
		String data = getData(versionURL);
		return data.substring(data.indexOf("[version]") + 9, data.indexOf("[/version]"));
	}
	
	private static String getData(String address) throws IOException {
		
		URL url = new URL(address);
		InputStream html = null;
		html = url.openStream();
		
		int c = 0;
		StringBuffer buffer = new StringBuffer("");
		
		while (c != -1) {
			c = html.read();
			buffer.append((char) c);
		}
		return buffer.toString();
	}
	
}
