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
	 * instantiate CLIOption class and call run method to get the args from CLI.
	 * Use instance of DataReader to Read the file and get the parsed data.
	 * And use thread created in class DataReader. 
	 * Create instance of Message filter to remove redundancy with 'parsedLines'.
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

		MessageFilter messageFilter = new MessageFilter(dataReader.parsedLines);
		messageFilter.filterData();
		HashMap<String, Integer> chatCountForOutPut = messageFilter.chatCount;
		
		DataWriter dataWriter = new DataWriter(chatCountForOutPut, cliOption.outputPath);
		
		dataWriter.run();
	}

}
