package edu.handong.csee.java.hw3;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

/**
 * In this class, When get the path of the input files, read the files there. 
 * if it is a CSV file, put that file to ArrayList 'CSVFiles'. Or if it is a TXT file, put that file to ArrayList 'TXTFiles'.
 * Open files in different formats and put the string in the ArrayList 'messages' one after the other in order. 
 * About Instance variables,
 * 'messages' is ArrayList containing strings of messages.
 * 'CSVFiles' and 'TXTFiles' are ArrayList containing files of each format.
 * 'CSVData' and 'TXTData' are ArrayList containing strings from messages for each format.
 * 
 * @author sua
 *
 */


public class DataReader {
	//class that reads file. In this class, instantiate DataReaderForCSV or DataReaderForTXT by file type.

	ArrayList<File> CSVFiles;
	ArrayList<File> TXTFiles;
	ArrayList<String> CSVdata;
	ArrayList<String> TXTdata;

	/**
	 * GetData is the main method in DataReader class.
	 * By using getDirectory, obtain file directory.
	 * And use getListOfFilesFromDirectory to get file arrayList.
	 * and by reaFiles method, set the CSVData and TXT data.
	 * 
	 * @param strDir is came from ChatMessageCounter class which is main class.
	 * strDir is path of input files. 
	 * @throws Exception 
	 * 
	 */
	public void getData(String strDir) throws Exception{
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
		try {
			for(File file: dataDir.listFiles()) {
				if(file.getAbsolutePath().contains(".csv")) {
					CSVFiles.add(file);

				}
				else if(file.getAbsolutePath().contains(".txt")) {
					TXTFiles.add(file);

				}
				else if(file.getAbsolutePath().contains(".doc")) throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("Wrong File Format!");
		}
	}

	private ArrayList<String> readFiles(ArrayList<File> files) throws Exception {
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = null;
		for(File f :files) {
			try{
				br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
				String line ="";

				while ((line = br.readLine()) != null) {
					if(f.getName().contains(".csv")) {
						int count = StringUtils.countMatches(line, "\"");
						if(!line.contains("\"")) continue;
						if(count == 1) continue;
						if(!line.endsWith("\"")) line = line+ "\"";
						data.add(line);
					}
					else if(f.getName().contains(".txt")){
						data.add(line);
					}
				}
			} catch (IOException e) {
				System.out.println ("Error opening the file" + f.getName());
				System.exit(0);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return data;		
	}

}