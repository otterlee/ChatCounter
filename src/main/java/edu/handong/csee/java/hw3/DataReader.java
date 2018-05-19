package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
	//class that reads file. In this class, instantiate DataReaderForCSV or DataReaderForTXT by file type.
	ArrayList<String> messages;
	public static void main(String[] args){
		DataReader dataReader = new DataReader();
		dataReader.messages = dataReader.getData(args[0]);
	}

	public ArrayList<String> getData(String strDir){
		File myDir = getDirectory(strDir);
		File[] files = getListOfFilesFromDirectory(myDir);
		ArrayList<String> messages = readFiles(files);
		return messages;
	}

	private File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		System.out.println("Directory : " +myDirectory);
		return myDirectory;

	}

	private File[] getListOfFilesFromDirectory(File dataDir) {
		for(File file: dataDir.listFiles()) {
			System.out.println("->"+file.getAbsolutePath());
		}
		return dataDir.listFiles();
	}

	private ArrayList<String> readFiles(File[] files) {
		ArrayList<String> messages = new ArrayList<String>();
		Scanner inputStream = null;
		for(File f :files) {
			try{
				inputStream = new Scanner(f,"UTF-8");

			} catch (FileNotFoundException e) {
				System.out.println ("Error opening the file" + f.getName());
				System.exit(0);
			}
			while (inputStream.hasNextLine ()) {
				String line = inputStream.nextLine();
				messages.add(line);
				System.out.println (line);
			}
		}
		inputStream.close();
		return messages;
	}
}

