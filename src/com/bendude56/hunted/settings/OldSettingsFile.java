package com.bendude56.hunted.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import com.bendude56.hunted.ManhuntPlugin;

public class OldSettingsFile extends Properties{

	private static final long serialVersionUID = -1911970219353417406L;
	private final String title;
	private final String directory;
	private final String filename;
	
	public OldSettingsFile(String title, String directory, String name)
	{
		this.title = title;
		this.directory = directory;
		this.filename = name + ".properties";
		
		loadFile();
	}
	
	public void loadFile() {
		File file = new File(directory + "/" + filename);
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				ManhuntPlugin.getInstance().log(Level.SEVERE,
						"Problem loading the Manhunt file \"" + title + "\"!");
				return;
			}
		}
		try {
			FileInputStream stream = new FileInputStream(file);
			load(stream);
			stream.close();
		} catch (IOException e) {
			ManhuntPlugin.getInstance().log(Level.SEVERE,
					"Problem loading the Manhunt file \"" + title + "!\"");
			ManhuntPlugin.getInstance().log(Level.SEVERE, e.getMessage());
			return;
		}
		saveFile();
	}
	
	public void saveFile() {
		File file = new File(directory + "/" + filename);
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				ManhuntPlugin.getInstance().log(Level.SEVERE,
						"Problem loading the Manhunt file \"" + title + "!\"");
				ManhuntPlugin.getInstance().log(Level.SEVERE, e.getMessage());
				return;
			}
		}
		try {
			FileOutputStream stream = new FileOutputStream(file);
			store(stream, "- Manhunt " + title + " File -");
			stream.close();
		} catch (IOException e) {
			ManhuntPlugin.getInstance().log(Level.SEVERE,
					"Problem loading the Manhunt file \"" + title + "!\"");
			ManhuntPlugin.getInstance().log(Level.SEVERE, e.getMessage());
			return;
		}
	}

}
