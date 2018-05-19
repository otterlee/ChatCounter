package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataReaderForCSV extends DataReader{
	HashMap<String, Integer> dataList = new HashMap<String, Integer>();
	
	public static void main(String[] args){
		DataReaderForCSV dataReaderForCSV = new DataReaderForCSV();
		dataReaderForCSV.getData(args[0]);
	}
	
	public ArrayList<String> getData(String strDir){
		return super.getData(strDir);
	}

	public File getDirectory(String strDir) {
		return super.getDirectory(strDir);
	}

	public ArrayList<File> getListOfFilesFromDirectory(File dataDir) {
		ArrayList<File> fileListCSV = new ArrayList<File>();
		for(File file: dataDir.listFiles()) {
			if(file.getAbsolutePath().contains(".csv")) {
				fileListCSV.add(file);
				System.out.println("->"+file.getAbsolutePath());
			}
		}
		return fileListCSV;
	}

	public ArrayList<String> readFiles(ArrayList<File> files) {
		ArrayList<String> messages = new ArrayList<String>();
		Scanner inputStream = null;
		
		for(File f :files) {
			try{
				System.out.println("File name >> "+f.getName());
				inputStream = new Scanner(f,"UTF-8");
			} catch (FileNotFoundException e) {
				System.out.println ("Error opening the file ");
				System.exit(0);
			}
			while (inputStream.hasNext()) {
				System.out.println("Start method readFiles");
				String line = inputStream.nextLine();
				messages.add(line);
				System.out.println (line);
			}
		}
		inputStream.close();
		return messages;
	}
}
