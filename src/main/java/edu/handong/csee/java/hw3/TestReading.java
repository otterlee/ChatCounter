package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestReading {
	/*public static void main(String[] args) {
		TestReading testReading = new TestReading();
		ArrayList<String> messages = new ArrayList<String>();
		messages = testReading.getData(args[0]);
		for(String m : messages) {
			System.out.println(m);
		}
	}

	public ArrayList<String> getData(String strDir){
		File myFile = getFile(strDir);
		ArrayList<String> messages = readFile(myFile);
		for(String e :messages) System.out.println(e);
		return messages;
	}

	private File getFile(String strDir) {
		File myFile = new File(strDir);
		return myFile;
	}

	private ArrayList<String> readFile(File file){
       // System.out.println("FileName: " + file);
        Scanner inputStream = null;
		System.out.println ("1");
		System.out.println(file);
		ArrayList<String> messages = new ArrayList<String>();
		try{
			System.out.println ("2");
			inputStream = new Scanner(file);
			System.out.println ("3");
		} catch (FileNotFoundException e) {
			System.out.println ("Error opening the file ");
			System.exit(0);
		}
		while(inputStream.hasNextLine()) {
			String line = inputStream.nextLine ();
			messages.add(line);
		}
		inputStream.close();
		
		return messages;
	}*/
}
