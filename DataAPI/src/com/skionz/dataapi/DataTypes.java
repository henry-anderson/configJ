package com.skionz.dataapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataTypes {
	protected String COMMENT_PREFIX = "#";
	private String path;

	/**
	 * @author Skionz
	 * @param path The path of the file
	 */
	public DataTypes(String path) {
		this.path = path;
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
	 * Gets an ArrayList<String> assigned to the key
	 * @param key The key in which to get the value from
	 * @return Returns an ArrayList value
	 */
	public ArrayList<String> getList(String key) {
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
	public byte getByte(String key) {
		try {
			return Byte.parseByte(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
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

	protected String getValue(String key) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.path));
			for (String line; (line = br.readLine()) != null;) {
				if(!line.startsWith(this.COMMENT_PREFIX)) {
					String lineKey = line.substring(0, line.indexOf(":"));
					String lineValue = line.substring(line.indexOf(":") + 2);
					if (lineKey.equals(key)) {
						return lineValue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
