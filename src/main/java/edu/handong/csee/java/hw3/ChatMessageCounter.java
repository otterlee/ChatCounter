package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ChatMessageCounter is the main class of this package.
 * @author sua
 *
 */
public class ChatMessageCounter {
	/**
	 * This is the main method of this class.
	 * Use instance of DataReader to Read the file and get the strings.
	 * And instantiate CLIOption class and call run method to get the args from CLI.
	 * To parse string, instantiate class DataReaderForTXT and DataReaderForCSV.
	 * And by calling parse method from each class, get parsed data.
	 * Merge two ArrayList and create instance of Message filter to remove redundancy.
	 * To create output file, instantiate the DataWriter class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataReader dataReader = new DataReader();
		//CLIOption cliOption = new CLIOption();
		//cliOption.run(args);
		
		//dataReader.getData(cliOption.inputPath);
		dataReader.getData(args[0]);
		
		//System.out.println(cliOption.inputPath);
		DataReaderForTXT dataReaderForTXT = new DataReaderForTXT(dataReader.TXTdata);
		DataReaderForCSV dataReaderForCSV = new DataReaderForCSV(dataReader.CSVdata);

		ArrayList<String[]> parsedDataListFromTXT = dataReaderForTXT.parseTXT();
		ArrayList<String[]> parsedDataListFromCSV = dataReaderForCSV.parseCSV();

		ArrayList<String[]> parsedDataList = new ArrayList<String[]>();

		parsedDataList.addAll(parsedDataListFromTXT);
		parsedDataList.addAll(parsedDataListFromCSV);

		System.out.println("  "+parsedDataListFromTXT.size());
		System.out.println("  "+parsedDataListFromCSV.size());
		System.out.println("  "+parsedDataList.size());


		MessageFilter messageFilter = new MessageFilter(parsedDataList);

		messageFilter.filterData();
		HashMap<String, Integer> chatCountForOutPut = messageFilter.chatCount;

		for (String name : chatCountForOutPut.keySet()) {
			// System.out.println(s.getScore());
		}
		//System.out.println("cliOption.outputPath : "+cliOption.outputPath);
		//DataWriter dataWriter = new DataWriter(chatCountForOutPut, cliOption.outputPath);
		//dataWriter.run();


	}



}
