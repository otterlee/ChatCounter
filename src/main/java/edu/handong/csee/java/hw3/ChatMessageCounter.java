package edu.handong.csee.java.hw3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ChatMessageCounter {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataReader dataReader = new DataReader();
		dataReader.getData(args[0]);
		DataReaderForTXT dataReaderForTXT = new DataReaderForTXT(dataReader.TXTFiles);
		DataReaderForCSV dataReaderForCSV = new DataReaderForCSV(dataReader.CSVFiles);
		
		ArrayList<String[]> parsedDataListFromTXT = dataReaderForTXT.parseTXT(dataReader.TXTFiles);
		ArrayList<String[]> parsedDataListFromCSV = dataReaderForCSV.parseCSV(dataReader.CSVFiles);
		ArrayList<String[]> parsedDataList = new ArrayList<String[]>();
		
		parsedDataList.addAll(parsedDataListFromTXT);
		parsedDataList.addAll(parsedDataListFromCSV);
		MessageFilter messageFilter = new MessageFilter(parsedDataList);
		messageFilter.filterData();
		HashMap<String, Integer> chatCountForOutPut = messageFilter.chatCount;
		
		Iterator<String> iterator = chatCountForOutPut.keySet().iterator();
		// 반복자를 이용해서 출력
		while (iterator.hasNext()) { 
			String key = (String)iterator.next(); // 키 얻기
			System.out.print("key="+key+" / value="+chatCountForOutPut.get(key));  // 출력
		}
		
	}
}
