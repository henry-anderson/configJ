package com.skionz.dataapi;

import java.util.UUID;

public class Main {
	public static void main(String[] args) {
		DataFile file = new DataFile("dataaa", "txt");
		file.addComment("This is a csomment!...");
		file.set("key", true);
		file.save();
		System.out.println(UUID.randomUUID());
	}
}
