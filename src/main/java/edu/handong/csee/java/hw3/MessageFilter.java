package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class MessageFilter implements Comparable<MessageFilter>{
	ArrayList<String[]> dataList;
	HashMap<String, ArrayList<String[]>> filteredChatDataList;
	HashMap<String, Integer> chatCount;

	MessageFilter(ArrayList<String[]> dataList){
		this.dataList = dataList;
	}

	public void filterData() {
		filteredChatDataList = createHashMap();
		checkRedundancy(filteredChatDataList);
		chatCount = createCountList(filteredChatDataList);
		//chatCount = sortHashMap(chatCount);
	}

	private void checkRedundancy(HashMap<String, ArrayList<String[]>> messageSets){
		int count = 0;
		System.out.println("### Detect 정승영");
		for(String[] s: messageSets.get("정승영")) {
			//System.out.println(s[0] +" || "+ s[1]);
		}

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

		System.out.println("### Detect 정승영");

		//for(String[] s: messageSets.get("정승영")) System.out.println(s[0] +" || "+ s[1]);
		//if(count > 10) break;

	}

	private boolean sameElement(ArrayList<String[]> arrayList, String[] arr) {
		for(String s[] : arrayList) {
			if(s[0].equals(arr[0]) && s[1].equals(arr[1])) return true;
		}
		return false;
	}
	
	private boolean checkPhotoRedundancy(ArrayList<String[]> arrayList, String[] arr) {
		for(String s[] : arrayList) {
			if(s[0].equals(arr[0])) {
				if((s[1].equals("Photo") && arr[1].equals("사진")) 
						|| (s[1].equals("사진") && arr[1].equals("Photo"))) return true;
			}
			
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

	private HashMap<String, Integer> sortHashMap(HashMap<String, Integer> originalHashMap){
		HashMap<String, Integer> copiedHashMap = originalHashMap;
		System.out.println("복사 잘 됐다");
		HashMap<String, Integer> sortedHashMap = new HashMap<String, Integer>();
		int max = 0;
		String maxCountUser = "";

		int count = 1;
		for(int i= 0; i< copiedHashMap.size(); i++) {
			for(String nameInnerLoop : copiedHashMap.keySet()) {
				if(originalHashMap.get(nameInnerLoop) > max) {
					max = originalHashMap.get(nameInnerLoop);
					maxCountUser = nameInnerLoop;
					System.out.println("max 찾았다.");
					System.out.println(maxCountUser);
					System.out.println(count);
					count++;
				}
			}
			sortedHashMap.put(maxCountUser, originalHashMap.get(maxCountUser));
			copiedHashMap.remove(maxCountUser);
		}

		for(String name : sortedHashMap.keySet()) {
			System.out.println("key = "+name+" value = "+sortedHashMap.get(name));
		}

		return sortedHashMap;

	}

	@Override
	public int compareTo(MessageFilter arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

