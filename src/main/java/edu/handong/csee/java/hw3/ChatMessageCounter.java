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
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CLIOption cliOption = new CLIOption();
		cliOption.run(args);
		DataReader dataReader = new DataReader(cliOption.inputNumThread);
		
		dataReader.getData(cliOption.inputPath);
		//dataReader.inputPath = args[0];
		//dataReader.getData(args[0]);
		
		//System.out.println(cliOption.inputPath);
		
		MessageFilter messageFilter = new MessageFilter(dataReader.parsedLines);
		messageFilter.filterData();
		HashMap<String, Integer> chatCountForOutPut = messageFilter.chatCount;
		for(String name: chatCountForOutPut.keySet()) {
			System.out.println(name + " "+chatCountForOutPut.get(name));
		}
		System.out.println("cliOption.outputPath : "+cliOption.outputPath);
		//DataWriter dataWriter = new DataWriter(chatCountForOutPut, cliOption.outputPath);
		//dataWriter.run();*/


	}



}
