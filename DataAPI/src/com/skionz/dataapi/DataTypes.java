package com.skionz.dataapi;

import java.io.BufferedReader;
import java.io.FileReader;

public class DataTypes {
	protected String COMMENT_PREFIX = "#";
	private String path;

	public DataTypes(String path) {
		this.path = path;
	}

	public String getString(String key) {
		return this.getValue(key);
	}

	public Integer getInt(String key) {
		try {
			return Integer.parseInt(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Double getDouble(String key) {
		try {
			return Double.parseDouble(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Float getFloat(String key) {
		try {
			return Float.parseFloat(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getLong(String key) {
		try {
			return Long.parseLong(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean getBoolean(String key) {
		try {
			return Boolean.parseBoolean(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte getByte(String key) {
		try {
			return Byte.parseByte(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public short getShort(String key) {
		try {
			return Short.parseShort(this.getValue(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean isInt(String key) {
		String value = this.getValue(key);
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean isDouble(String key) {
		String value = this.getValue(key);
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean isFloat(String key) {
		String value = this.getValue(key);
		try {
			Float.parseFloat(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean isLong(String key) {
		String value = this.getValue(key);
		try {
			Long.parseLong(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean isBoolean(String key) {
		String value = this.getValue(key);
		try {
			Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean isByte(String key) {
		String value = this.getValue(key);
		try {
			Byte.parseByte(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

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
