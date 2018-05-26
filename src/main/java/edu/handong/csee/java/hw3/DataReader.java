package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
	//class that reads file. In this class, instantiate DataReaderForCSV or DataReaderForTXT by file type.
	ArrayList<String> messages;
	ArrayList<File> CSVFiles;
	ArrayList<File> TXTFiles;

	public void getData(String strDir){
		File myDir = getDirectory(strDir);
		getListOfFilesFromDirectory(myDir);
	}

	private File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		System.out.println("Directory : " +myDirectory);
		return myDirectory;

	}

	private void getListOfFilesFromDirectory(File dataDir) {
		CSVFiles = new ArrayList<File>();
		TXTFiles = new ArrayList<File>();
		for(File file: dataDir.listFiles()) {
			System.out.println(file.getName());
			if(file.getAbsolutePath().contains(".csv")) CSVFiles.add(file);
			else if(file.getAbsolutePath().contains(".txt")) TXTFiles.add(file);
			}
		}

	/*private ArrayList<String> readFiles(File[] files) {
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
	}*/
}

