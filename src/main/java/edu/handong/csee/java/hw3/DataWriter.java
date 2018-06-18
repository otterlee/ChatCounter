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
 * Write the file from Hashmap chatDataList.
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
	 * by using Iterator, write the HashMap chatDataList.
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
		chatDataList = sortChatDataList(chatDataList);
		Iterator iterator = chatDataList.keySet().iterator();

		while(iterator.hasNext()) {
			String temp = (String)iterator.next();
			System.out.println(temp + "," + chatDataList.get(temp));
			outputStream.println(temp + "," + chatDataList.get(temp));
		}

		outputStream.close();
		System.out.println ("Those lines were written to " + fileName);
	}

	private HashMap<String, Integer> sortChatDataList(HashMap<String, Integer> beforeData){
		int i=0, max = 0;
		String maxName="";
		ArrayList<String> orderOfName = new ArrayList<String>();
		HashMap<String, Integer> sortedChatDataList = new HashMap<String, Integer>();
		for(i=0; i< beforeData.keySet().size();i++) {
			for(String name : beforeData.keySet()) {
				if(beforeData.get(name) > max) {
					max = beforeData.get(name);
					maxName = name;
				}
				beforeData.remove(name);
			}
			sortedChatDataList.put(maxName, max);
		}
		return sortedChatDataList;
	}

}
