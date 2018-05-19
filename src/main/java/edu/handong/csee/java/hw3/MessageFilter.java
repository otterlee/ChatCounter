package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageFilter{
	ArrayList<String> name;
	ArrayList<String> dateTime;
	ArrayList<String> chatMessage;
	//check redundancy using above ArrayLists name, dateTime, chatMessage
	HashMap<String, Integer> filteredChatDataList;
	//In this class, HashMap filteredChatDataList will be finished.
	
	
	MessageFilter(ArrayList<String> name, ArrayList<String> dateTime, ArrayList<String> chatMessage){
		this.name = name;
		this.dateTime = dateTime;
		this.chatMessage = chatMessage;
	}
	

}
