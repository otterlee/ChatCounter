package edu.handong.csee.java.hw3;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * In this class, When get the path of the input files, read the files there. 
 * if it is a CSV file, create CSVThread. Or if it is a TXT file, TXTThread.
 * Open files in different formats and put the string in the ArrayList 'parsedLines'. 
 * About Instance variables,
 * 'parsedLines' is ArrayList containing strings of parsed messages.
 * 'inputNumThread' is the input number of threads
 * 'inputPath' is the path containing files.
 * @author sua
 *
 */


public class DataReader{
	String inputPath;
	int inputNumThreads;
	ArrayList<String[]> parsedLines = new ArrayList<String[]>();

	DataReader (int numThread){
		this.inputNumThreads = numThread;
	}
	/**
	 * GetData is the main method in DataReader class.
	 * By using getDirectory, obtain file directory.
	 * And use getListOfFilesFromDirectory to get file parsed data by using thread pool.
	 * 
	 * @param strDir is came from ChatMessageCounter class which is main class.
	 * strDir is path of input files. 
	 * @throws Exception 
	 * 
	 */
	public void getData(String strDir) throws Exception{
		File myDir = getDirectory(strDir);
		getListOfFilesFromDirectory(myDir);

	}

	private File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		return myDirectory;
	}

	private void getListOfFilesFromDirectory(File dataDir) {
		ExecutorService executor = Executors.newFixedThreadPool(inputNumThreads);
		ArrayList<DataReaderForCSVThread> threadsCSV = new ArrayList<DataReaderForCSVThread>();
		ArrayList<DataReaderForTXTThread> threadsTXT = new ArrayList<DataReaderForTXTThread>();

		try {
			for(File file: dataDir.listFiles()) {
				if(file.getAbsolutePath().contains(".csv")) {
					Runnable worker = new DataReaderForCSVThread(file);
					executor.execute(worker);
					threadsCSV.add((DataReaderForCSVThread)worker);

				}
				else if(file.getAbsolutePath().contains(".txt")) {
					Runnable worker = new DataReaderForTXTThread(file);
					executor.execute(worker);
					threadsTXT.add((DataReaderForTXTThread)worker);

				}
				else if(file.getAbsolutePath().contains(".doc")) throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("Wrong File Format! Reading problem");
		}

		executor.shutdown();

		while (!executor.isTerminated()) {}

		for(DataReaderForCSVThread t: threadsCSV) {
			parsedLines.addAll(t.getParsedLines());
		}
		for(DataReaderForTXTThread t: threadsTXT) {
			parsedLines.addAll(t.getParsedLines());
		}
	}
}