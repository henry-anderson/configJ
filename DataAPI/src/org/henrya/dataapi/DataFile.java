package org.henrya.dataapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DataFile {
	private String path;
	private File file;
	//Comments will be implemented soon
	private String COMMENT_PREFIX = "#";
	private LinkedHashMap<String, String> fileMap;

	/**
	 * A class for storing human readable data
	 * @param path The path to the new, or existing file excluding the extension
	 */
	public DataFile(String path) {
		this(path, "txt");
	}
	
	/**
	 * A class for storing human readable data
	 * @param path The path to the new, or existing file
	 * @param extension The extension of the file excluding the period
	 */
	public DataFile(String path, String extension) {
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
		if(key.startsWith(this.COMMENT_PREFIX)) {
			try {
				throw new DataFormatException("Key cannot start with '" + this.COMMENT_PREFIX + "'");
			} catch(DataFormatException e) {
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
	public List<String> keyList() {
		ArrayList<String> keys = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			for(String line; (line = br.readLine()) != null;) {
				if(!line.startsWith(this.COMMENT_PREFIX) && !line.isEmpty()) {
					String lineKey = this.parseKey(line);
					keys.add(lineKey);
				}
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return keys;
	}
	
	/**
	 * Gets a list of all values saved in the file
	 * @return Returns an List of values
	 */
	public List<String> valueList() {
		ArrayList<String> values = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			for(String line; (line = br.readLine()) != null;) {
				if(!line.startsWith(this.COMMENT_PREFIX) && !line.isEmpty()) {
					String lineValue = this.parseValue(line);
					values.add(lineValue);
				}
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return values;
	}
	
	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a String value
	 */
	public String getString(String key) {
		return this.getValue(key);
	}
	
	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns an Integer value
	 */
	public Integer getInt(String key) {
		try {
			return Integer.parseInt(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a Double value
	 */
	public Double getDouble(String key) {
		try {
			return Double.parseDouble(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a Float value
	 */
	public Float getFloat(String key) {
		try {
			return Float.parseFloat(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a Long value
	 */
	public Long getLong(String key) {
		try {
			return Long.parseLong(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	 * @return Returns a Boolean value
	 */
	public Boolean getBoolean(String key) {
		try {
			return Boolean.parseBoolean(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a Byte value
	 */
	public Byte getByte(String key) {
		try {
			return Byte.parseByte(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the value assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns a Short value
	 */
	public short getShort(String key) {
		try {
			return Short.parseShort(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
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

	private String getValue(String key) {
		return this.fileMap.get(key);
	}
	private LinkedHashMap<String, String> toMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.path));
			for(String line; (line = br.readLine()) != null;) {
				if(!line.isEmpty() && !line.startsWith("#")) {
					String lineKey = this.parseKey(line);
					String lineValue = this.parseValue(line);
					map.put(lineKey, lineValue);
				}
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	private String parseKey(String line) {
		String lineKey = line.substring(0, line.indexOf(":"));
		return lineKey;
	}
	private String parseValue(String line) {
		String lineValue = line.substring(line.indexOf(":") + 2);
		return lineValue;
	}
}
