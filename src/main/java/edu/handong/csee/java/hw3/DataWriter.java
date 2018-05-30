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

public class DataWriter {

	HashMap<String, Integer> chatDataList;
	String outputPath;

	DataWriter(HashMap<String, Integer> chatDataList, String outputPath){
		this.chatDataList = chatDataList;
		this.outputPath = outputPath;	
	}

	//write this HashMap which contains name and chat count on output file.
	public void run() {
		System.out.println("DEBUG1");
		String fileName = outputPath;
		PrintWriter outputStream = null;

		try {
			outputStream = new PrintWriter(fileName);
			System.out.println("DEBUG2");
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}


		Iterator iterator = chatDataList.keySet().iterator();

		Iterator it = sortByValue(chatDataList).iterator();
		while(it.hasNext()) {
			String temp = (String)it.next();
			outputStream.println(temp + " = " + chatDataList.get(temp));
		}

		outputStream.close();
		System.out.println ("Those lines were written to " + fileName);
	}


	public static List sortByValue(final HashMap map) {
		List<String> list = new ArrayList();
		list.addAll(map.keySet());

		Collections.sort(list,new Comparator() {

			public int compare(Object o1,Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable) v2).compareTo(v1);
			}

		});
		//Collections.reverse(list); // 주석시 오름차순
		return list;
	}
}
