package com.skionz.dataapi;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MapFile {
	private String path;
	private DataFile file;
	
	/**
	 * A wrapper class for quick Map saving
	 * @param path The path to the new, or existing file
	 * @param extension The extension of the file excluding the period
	 */
	public MapFile(String path, String extension) {
		this.path = path + "." + extension;
		this.file = new DataFile(path, extension);
	}
	/**
	 * Writes a Map to the file in a key: value format
	 * @param map The Map to save
	 */
	public void write(Map<String, ?> map) {
		for(String key : map.keySet()) {
			Object value = map.get(key);
			file.set(key, value);
		}
	}
	
	/**
	 * Turns a file into a Map
	 * @return Returns a Map<String, String> with every key and value inside
	 */
	public Map<String, String> read() {
		Map<String, String> map = new HashMap<String, String>();
		for(String key : file.keyList()) {
			String value = file.getString(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * Adds a line to the end of the file or updates an existing line
	 * @param key The line to add
	 * @param value The value assigned to the key
	 */
	public void put(String key, String value) {
		Map<String, String> map = this.read();
		map.put(key, value);
		this.write(map);
	}
	
	/**
	 * Clears the file of all data
	 */
	public void clear() {
		try {
			PrintWriter pw = new PrintWriter(this.path);
			pw.close();
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
}