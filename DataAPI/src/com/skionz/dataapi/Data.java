package com.skionz.dataapi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Data {
	
	/**
	 * Serializes an object
	 * @author Skionz
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
}
