package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
	
	/*public static void main(String[] args){
		DataReader dataReader = new DataReader();
		dataReader.getData(args[0]);
	}*/

	public ArrayList<String> getData(String strDir){
		//(1) getDirectory
		File myDir = getDirectory(strDir);
		//(2) getListOfFilesFromDirectory
		ArrayList<File> files = getListOfFilesFromDirectory(myDir);
		//(3)
		ArrayList<String> messages = readFiles(files);

		return messages;
	}

	public File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		System.out.println("myDirectory : " +myDirectory);
		return myDirectory;

	}

	public ArrayList<File> getListOfFilesFromDirectory(File dataDir) {
		ArrayList<File> fileList = new ArrayList<File>();
		for(File file: dataDir.listFiles()) {
			System.out.println("->"+file.getAbsolutePath());
			fileList.add(file);
		}
		return fileList;
	}

	public ArrayList<String> readFiles(ArrayList<File> files) {
		ArrayList<String> messages = new ArrayList<String>();
		Scanner inputStream = null;
		for(File f :files) {
				try{
					inputStream = new Scanner(f);
				} catch (FileNotFoundException e) {
					System.out.println ("Error opening the file ");
					System.exit(0);
				}
				while (inputStream.hasNextLine ()) {
					String line = inputStream.nextLine ();
					messages.add(line);
					System.out.println (line);
				}
			}
		inputStream.close();
		return messages;
	}
}

