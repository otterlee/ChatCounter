package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ChatMessageCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataReader dataReader = new DataReader();
		CLIOption cliOption = new CLIOption();
		cliOption.run(args);
		dataReader.getData(cliOption.inputPath);
		System.out.println(cliOption.inputPath);
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
		System.out.println("cliOption.outputPath : "+cliOption.outputPath);
		DataWriter dataWriter = new DataWriter(chatCountForOutPut, cliOption.outputPath);
		dataWriter.run();
		
		
	}
	
	public static List sortByValue(final Map map) {
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





	/*private int compareTo(HashMapCount person) {
        if (this. < person.getScore()) {
            return -1;
        } else if (this.score > s.getScore()) {
            return 1;
        }
        return 0;
    }*/



}
