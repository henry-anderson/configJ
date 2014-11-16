package com.skionz.dataapi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataUtils {
	
	/**
	 * Serializes an object
	 * @param object The object to serialize
	 * @param path The path to the new file
	 * @param extension The extension of the new file excluding the period
	 */
	public static void serialize(Object object, String path, String extension) {
		try {
			FileOutputStream fileStream = new FileOutputStream(path + "." + extension);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(object);
			objectStream.close();
			fileStream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deserializes an object from a file
	 * @param path The path to the file
	 * @param extension The extension of the file excluding the period
	 */
	public static Object deserialize(String path, String extension) {
		try {
			FileInputStream fileStream = new FileInputStream(path + "." + extension);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			Object object = objectStream.readObject();
			objectStream.close();
			fileStream.close();
			return object;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Creates an instance of the DataFile class
	 * @param path The path to the new, or existing file
	 * @param extension The extension of the file excluding the period
	 */
	public static DataFile createDataFile(String path, String extension) {
		DataFile file = new DataFile(path, extension);
		return file;
	}
	
	/**
	 * Creates an instance of the ListFile class
	 * @param path The path to the new, or existing file
	 * @param extension The extension of the file excluding the period
	 */
	public static ListFile createListFile(String path, String extension) {
		ListFile file = new ListFile(path, extension);
		return file;
	}
	
	/**
	 * Creates an instance of the MapFile class
	 * @param path The path to the new, or existing file
	 * @param extension The extension of the file excluding the period
	 */
	public static MapFile createMapFile(String path, String extension) {
		MapFile file = new MapFile(path, extension);
		return file;
	}
}
