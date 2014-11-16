package com.skionz.dataapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DataFile extends DataTypes {
	private String path;
	private File file;
	
	/**
	 * A class for storing human readable data
	 * @author Skionz
	 * @param path The path to the new, or existing file
	 * @param extension The extension of the file excluding the period
	 */
	public DataFile(String path, String extension) {
		super(path + "." + extension);
		try {
			this.path = path + "." + extension;
			this.file = new File(this.path);
			file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Assigns a value to a key
	 * @param key The key
	 * @param value The value assigned to the key
	 */
	public void set(String key, Object value) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			if(this.getValue(key) != null) {
				BufferedReader br = new BufferedReader(new FileReader(this.path));
				ArrayList<String> file = new ArrayList<String>();
				for(String line; (line = br.readLine()) != null;) {
					if(line.startsWith(this.COMMENT_PREFIX)) {
						file.add(line);
					}
					else {
						String lineKey = line.substring(0, line.indexOf(":"));
						String lineValue = line.substring(line.indexOf(":") + 2);
						if(lineKey.equals(key)) {
							file.add(lineKey + ": " + value);
						}
						else {
							file.add(lineKey + ": " + lineValue);
						}
					}
				}
				this.clear();
				for(String line : file) {
					bw.append(line);
					bw.newLine();
				}
				br.close();
			}
			else {
				bw.append(key + ": " + value);
				bw.newLine();
			}
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes the specified line from the file
	 * @param key The key to remove
	 */
	public void remove(String key) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			ArrayList<String> file = new ArrayList<String>();
			for(String line; (line = br.readLine()) != null;) {
				String lineKey = line.substring(0, line.indexOf(":"));
				String lineValue = line.substring(line.indexOf(":") + 2);
				if(!lineKey.equals(key)) {
					file.add(lineKey + ": " + lineValue);
				}
			}
			this.clear();
			for(String line : file) {
				bw.append(line);
				bw.newLine();
			}
			br.close();
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a comment to the file
	 * @param comment The comment to add
	 */
	public void addComment(String comment) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			ArrayList<String> file = new ArrayList<String>();
			for(String line; (line = br.readLine()) != null;) {
				file.add(line);
			}
			file.add(this.COMMENT_PREFIX + comment);
			this.clear();
			for(String line : file) {
				bw.append(line);
				bw.newLine();
			}
			br.close();
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clears all of the data saved in the file
	 */
	public void clear() {
		try {
		PrintWriter writer = new PrintWriter(this.path);
		writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes the file
	 */
	public void delete() {
		file.delete();
	}
	
	/**
	 * Gets a list of all keys saved in the file
	 * @return Returns an ArrayList of keys
	 */
	public ArrayList<String> keyList() {
		ArrayList<String> keys = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			for(String line; (line = br.readLine()) != null;) {
				String lineKey = line.substring(0, line.indexOf(":"));
				keys.add(lineKey);
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return keys;
	}
	
	/**
	 * Gets a list of all values saved in the file
	 * @return Returns an ArrayList of values
	 */
	public ArrayList<String> valueList() {
		ArrayList<String> values = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			for(String line; (line = br.readLine()) != null;) {
				String lineValue = line.substring(line.indexOf(":") + 2);
				values.add(lineValue);
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return values;
	}
}
