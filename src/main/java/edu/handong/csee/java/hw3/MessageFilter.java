package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageFilter{
	ArrayList<String[]> data;
	HashMap<String, ArrayList<String[]>> filteredChatDataList;
	HashMap<String, Integer> chatCount;
	
	MessageFilter(ArrayList<String[]> dataList){
		this.data = dataList;
	}
	
	public void filterData() {
		filteredChatDataList = createHashMap(data);
		checkRedundancy(filteredChatDataList);
		chatCount = createCountList(filteredChatDataList);
	}
	
	private void checkRedundancy(HashMap<String, ArrayList<String[]>> filteredMessageSet){
		for(String name : filteredChatDataList.keySet()) {
			ArrayList<String[]> allMessages = filteredChatDataList.get(name);
			for (int i = 0; i < allMessages.size(); i++) {
                for (int j = 0; j < allMessages.size(); j++) {
                    if (i == j) {
                    } else if (allMessages.get(j).equals(allMessages.get(i))) {
                    	allMessages.remove(j);
                    }
                }
            }
		}
	}
	
	private HashMap<String, Integer> createCountList(HashMap<String, ArrayList<String[]>> filteredMessageSet) {
		HashMap<String, Integer> chatCountList = new HashMap<String, Integer>();
		for(String name : filteredChatDataList.keySet()) {
			chatCountList.put(name, filteredChatDataList.get(name).size());
		} 
		return chatCountList;
	}
	
	
	private HashMap<String, ArrayList<String[]>> createHashMap(ArrayList<String[]> dataList){
		HashMap<String, ArrayList<String[]>> messageSet = new HashMap<String, ArrayList<String[]>>();
		for(String[] s: data) {
			String name = s[0];
			String []timeAndMessage = {s[1], s[2]};
			
			if(messageSet.containsKey(name)) {
				messageSet.get(name).add(timeAndMessage);
			}
			else {
				ArrayList<String[]> messages = new ArrayList<String[]>();
				messages.add(timeAndMessage);
				messageSet.put(name,messages);
			}
			
		}
		return messageSet;
	}
	

}
