package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * DataWriter class is the class that writes the output file.
 * Sort the HashMap chatDataList and write the file from Hashmap chatDataList.
 * By constructor, set the output file's name.
 * @author sua
 *
 */
public class DataWriter {

	HashMap<String, Integer> chatDataList;
	String outputPath;

	DataWriter(HashMap<String, Integer> chatDataList, String outputPath){
		this.chatDataList = chatDataList;
		this.outputPath = outputPath;	
	}

	DataWriter(HashMap<String, Integer> chatDataList){
		this.chatDataList = chatDataList;
	}

	/**
	 * run method is the main method in DataWriter.
	 * Call method 'sortChatDataList' to sort and by using Iterator, write the HashMap chatDataList.
	 * 
	 */
	public void run() {
		String fileName = outputPath;
		PrintWriter outputStream = null;

		try {
			outputStream = new PrintWriter(fileName);
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}
		HashMap<String, Integer> chatDataListToSort = new HashMap<String, Integer>();
		chatDataListToSort.putAll(chatDataList);
		ArrayList<String> orderOfName = sortChatDataList(chatDataListToSort);
		for(String name: orderOfName) {
			System.out.println(name + "," + chatDataList.get(name));
			outputStream.println(name + "," + chatDataList.get(name));
		}

		outputStream.close();
		System.out.println ("Those lines were written to " + fileName);
	}

	private ArrayList<String> sortChatDataList(HashMap<String, Integer> beforeData){
		int i=0, max = 0;
		String maxName="";
		ArrayList<String> orderOfName = new ArrayList<String>();
		for(i=0; i< beforeData.keySet().size();i++) {
			max=0;
			for(String name : beforeData.keySet()) {
				if(beforeData.get(name) > max) {
					max = beforeData.get(name);
					maxName = name;
				}
			}
			beforeData.put(maxName, 0);
			orderOfName.add(maxName);	
		}
		return orderOfName;
	}
}
