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
 * DataWriter class is the class that write the output file.
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

		Iterator iterator = chatDataList.keySet().iterator();
		Iterator it = sortByCount(chatDataList).iterator();
		while(it.hasNext()) {
			String temp = (String)it.next();
			System.out.println(temp + " = " + chatDataList.get(temp));
				
			outputStream.println(temp + " = " + chatDataList.get(temp));
		}

		outputStream.close();
		System.out.println ("Those lines were written to " + fileName);
	}



	public static List sortByCount(HashMap<String, Integer> map) {
		List<String> list = new ArrayList();
		list.addAll(map.keySet());

		Collections.sort(list,new Comparator() {

			public int compare(Object o1,Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable) v2).compareTo(v1);
			}

		});
		return list;
	}
}
