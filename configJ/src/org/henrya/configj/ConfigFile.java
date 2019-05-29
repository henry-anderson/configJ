package org.henrya.configj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class ConfigFile {
	private String path;
	private File file;
	//Comments will be implemented soon
	private static final String COMMENT_PREFIX = "#";
	private LinkedHashMap<String, String> fileMap;

	/**
	 * A class for storing human readable data
	 * @param path The path to the new, or existing file excluding the extension
	 */
	public ConfigFile(String path) {
		this(path, "txt");
	}
	
	/**
	 * A class for storing human readable data
	 * @param path The path to the new, or existing file
	 * @param extension The extension of the file excluding the period
	 */
	public ConfigFile(String path, String extension) {
		this.path = path + "." + extension;
		this.file = new File(this.path);
		fileMap = new LinkedHashMap<String, String>();
		try {
			file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.fileMap = this.toMap();
	}
	
	/**
	 * Saves the current data to the file
	 */
	public void save() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.path, true));
			this.clear();
			for(String key : this.fileMap.keySet()) {
				String value = this.fileMap.get(key);
				String line;
				line = key + ": " + value; 
				bw.append(line);
				bw.newLine();
			}
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Assigns a value to a key
	 * @param key The key
	 * @param value The value assigned to the key
	 */
	public void set(String key, Object value) {
		if(key.startsWith(COMMENT_PREFIX)) {
			try {
				throw new ConfigFormatException("Key cannot start with '" + COMMENT_PREFIX + "'");
			} catch(ConfigFormatException e) {
				e.printStackTrace();
			}
		} else {
			this.fileMap.put(key, value.toString());
		}
	}

	/**
	 * Removes the specified line from the file
	 * @param key The key to remove
	 */
	public void remove(String key) {
		this.fileMap.remove(key);
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
		this.file.delete();
	}
	
	/**
	 * Gets a list of all keys saved in the file
	 * @return Returns a List of keys
	 */
	public Set<String> keySet() {
		Set<String> keys = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			for(String line; (line = br.readLine()) != null;) {
				if(!line.startsWith(COMMENT_PREFIX) && !line.isEmpty()) {
					String lineKey = this.parseKey(line);
					keys.add(lineKey);
				}
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return keys;
	}
	
	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a String
	 */
	public String getString(String key) {
		return this.getValue(key);
	}
	
	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns an int
	 */
	public int getInt(String key) {
		return Integer.parseInt(this.getValue(key));
	}
	
	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a double
	 */
	public double getDouble(String key) {
		return Double.parseDouble(this.getValue(key));
	}

	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a float
	 */
	public float getFloat(String key) {
		return Float.parseFloat(this.getValue(key));
	}

	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a long
	 */
	public long getLong(String key) {
		return Long.parseLong(this.getValue(key));
	}
	
	/**
	 * Gets a List assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a value
	 */
	public List<String> getList(String key) {
		ArrayList<String> list = new ArrayList<String>();
		String string = this.getValue(key);
		String[] array = string.split(", ");
		for(int i = 0; i < array.length; i++) {
			String line = array[i];
			list.add(line);
		}
		return list;
	}

	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a boolean
	 */
	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(this.getValue(key));
	}
	
	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a byte
	 */
	public byte getByte(String key) {
		return Byte.parseByte(this.getValue(key));
	}

	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a short
	 */
	public short getShort(String key) {
		return Short.parseShort(this.getValue(key));
	}

	/**
	 * Returns whether the value assigned to the key is an integer
	 * @param key The key to get the value from
	 * @return Returns a boolean
	 */
	public boolean isInt(String key) {
		String value = this.getValue(key);
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns whether the value assigned to the key is a double
	 * @param key The key to get the value from
	 * @return Returns a boolean
	 */
	public boolean isDouble(String key) {
		String value = this.getValue(key);
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns whether the value assigned to the key is a float
	 * @param key The key to get the value from
	 * @return Returns a boolean
	 */
	public boolean isFloat(String key) {
		String value = this.getValue(key);
		try {
			Float.parseFloat(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Returns whether the value assigned to the key is a long
	 * @param key The key to get the value from
	 * @return Returns a boolean
	 */
	public boolean isLong(String key) {
		String value = this.getValue(key);
		try {
			Long.parseLong(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Returns whether the value assigned to the key is a boolean
	 * @param key The key to get the value from
	 * @return Returns a boolean
	 */
	public boolean isBoolean(String key) {
		String value = this.getValue(key);
		try {
			Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Returns whether the value assigned to the key is a byte
	 * @param key The key to get the value from
	 * @return Returns a boolean
	 */
	public boolean isByte(String key) {
		String value = this.getValue(key);
		try {
			Byte.parseByte(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Returns whether the value assigned to the key is a short
	 * @param key The key to get the value from
	 * @return Returns a boolean
	 */
	public boolean isShort(String key) {
		String value = this.getValue(key);
		try {
			Short.parseShort(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	} 

	/**
	 * Returns the value of a key as a String
	 * @param key The key
	 * @return The value as a String
	 */
	private String getValue(String key) {
		return this.fileMap.get(key);
	}
	
	/**
	 * Returns the instance as a Map
	 * @return A LinkedHashMap
	 */
	private LinkedHashMap<String, String> toMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			for(String line; (line = br.readLine()) != null;) {
				if(!line.isEmpty() && !line.startsWith(COMMENT_PREFIX)) {
					String lineKey = this.parseKey(line);
					String lineValue = this.parseValue(line);
					map.put(lineKey, lineValue);
				}
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * Parses the key of the line
	 * @param line The line to parse
	 * @return The key
	 */
	private String parseKey(String line) {
		return line.substring(0, line.indexOf(":"));
	}
	
	/**
	 * Parses the value of the line
	 * @param line The line to parse
	 * @return The value
	 */
	private String parseValue(String line) {
		return line.substring(line.indexOf(":") + 2);
	}
}
