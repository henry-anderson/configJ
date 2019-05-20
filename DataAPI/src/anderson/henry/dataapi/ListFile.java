package anderson.henry.dataapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ListFile {
	File file;
	String path;	
		
	/**
	 * @param path The path of the new, or existing file
	 * @param extension The extension of the file excluding the period
	 */
	public ListFile(String path, String extension) {
		this.path = path + "." + extension;
		this.file = new File(this.path);
		try {
			this.file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return Returns a List with every line of the file inside.
	 */
	public List<String> read() {
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			 
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			} 
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Writes a List to the file line by line
	 * @param list The List to save
	 */
	public void write(List<String> list) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.path, true));
			this.clear();
			for(String line : list) {
				bw.append(line);
				bw.newLine();
			}
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a line to the end of the file
	 * @param line The line to add
	 */
	public void addLine(String line) {
		List<String> list = this.read();
		list.add(line);
		this.write(list);
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
