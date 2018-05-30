package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * MessageFilet is the class that check redundancy and count the chat count for each users. 
 * 
 * @author suagu
 *
 */

public class MessageFilter {
	ArrayList<String[]> dataList;
	HashMap<String, ArrayList<String[]>> filteredChatDataList;
	HashMap<String, Integer> chatCount;

	MessageFilter(ArrayList<String[]> dataList){
		this.dataList = dataList;
	}
	/**
	 * filterData is the main method.
	 * In this method, use createHashMap method to create HashMap which value is message list from dataList..
	 * and call checkRedundancy method to check redundancy and remove that message.
	 * and call createCountList to create hashmap which value is chat count.
	 */

	public void filterData() {
		filteredChatDataList = createHashMap();
		checkRedundancy(filteredChatDataList);
		chatCount = createCountList(filteredChatDataList);
		//chatCount = sortHashMap(chatCount);

		Iterator iterator = chatCount.keySet().iterator();

		//Iterator it = sortByValue(catDataList).iterator();
		while(iterator.hasNext()) {
			String temp = (String)iterator.next();
			System.out.println(temp + " = " + chatCount.get(temp));
		}

	}

	private void checkRedundancy(HashMap<String, ArrayList<String[]>> messageSets){

		for(String name : messageSets.keySet()) {
			ArrayList<String[]> allMessages = messageSets.get(name);
			ArrayList<String[]> resultList = new ArrayList<String[]>();

			for (int i = 0; i < allMessages.size(); i++) {
				if(checkPhotoRedundancy(resultList,allMessages.get(i))) allMessages.remove(i);
				if (!sameElement(resultList,allMessages.get(i))) {
					resultList.add(allMessages.get(i));
				}
			}
			messageSets.put(name,resultList);
		}



	}

	private boolean sameElement(ArrayList<String[]> arrayList, String[] arr) {
		for(String s[] : arrayList) {
			if(s[0].equals(arr[0]) && s[1].equals(arr[1])) return true;
		}
		return false;
	}

	private boolean checkPhotoRedundancy(ArrayList<String[]> arrayList, String[] arr) {
		for(String s[] : arrayList) {
			if(s[0].equals(arr[0])&&(s[1].equals("Photo") && arr[1].equals("사진")) 
					|| (s[1].equals("사진") && arr[1].equals("Photo"))) return true;


		}
		return false;
	}



	private HashMap<String, Integer> createCountList(HashMap<String, ArrayList<String[]>> filteredMessageSet) {
		HashMap<String, Integer> chatCountList = new HashMap<String, Integer>();
		for(String name : filteredChatDataList.keySet()) {
			chatCountList.put(name, filteredChatDataList.get(name).size());
		} 
		return chatCountList;
	}


	private HashMap<String, ArrayList<String[]>> createHashMap(){
		HashMap<String, ArrayList<String[]>> messageSet = new HashMap<String, ArrayList<String[]>>();
		for(String[] s: dataList) {
			String name = s[0];
			String []timeAndMessage = {s[1], s[2]};

			if(messageSet.containsKey(name)) {
				messageSet.get(name).add(timeAndMessage);
			}
			else {
				ArrayList<String[]> timeAndMessages = new ArrayList<String[]>();
				timeAndMessages.add(timeAndMessage);
				messageSet.put(name,timeAndMessages);
			}

		}
		return messageSet;
	}


}

