package edu.handong.csee.java.hw3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class DataReader {
	//class that reads file. In this class, instantiate DataReaderForCSV or DataReaderForTXT by file type.
	ArrayList<String> messages;
	ArrayList<File> CSVFiles;
	ArrayList<File> TXTFiles;
	ArrayList<String> CSVdata;
	ArrayList<String> TXTdata;


	public void getData(String strDir){
		File myDir = getDirectory(strDir);
		getListOfFilesFromDirectory(myDir);
		CSVdata = readFiles(CSVFiles);
		TXTdata = readFiles(TXTFiles);
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
			//System.out.println(file.getAbsolutePath());
			if(file.getAbsolutePath().contains(".csv")) {
				CSVFiles.add(file);

			}
			else if(file.getAbsolutePath().contains(".txt")) {
				TXTFiles.add(file);

			}
		}
		System.out.println("csv file 개수: "+CSVFiles.size());
		System.out.println("txt file 개수: "+TXTFiles.size());

	}

	private ArrayList<String> readFiles(ArrayList<File> files) {
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = null;
		for(File f :files) {
			try{
				br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
				String line ="";

				while ((line = br.readLine()) != null) {
					if(f.getName().contains(".csv")) {
						//if(!line.endsWith("\"")) continue;
						//if(line.contains("김석진")) System.out.println(line);
						int count = StringUtils.countMatches(line, "\"");
						if(!line.contains("\"")) continue;
						if(count == 1) continue;
						if(!line.endsWith("\"")) line = line+ "\"";
						data.add(line);
					}
					else if(f.getName().contains(".txt")){
						//if(line.contains("김석진")) System.out.println(line);
						data.add(line);
					}
				}
			} catch (IOException e) {
				System.out.println ("Error opening the file" + f.getName());
				System.exit(0);
			}

		}
		return data;		
	}

}