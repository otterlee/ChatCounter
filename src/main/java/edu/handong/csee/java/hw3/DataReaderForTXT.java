package edu.handong.csee.java.hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * DataReaderForTXT class is the class that parse the strings from txt file and put to parsedData ArrayList.
 * 
 * TXTData is raw data from DataReader class.
 * parsedData is parsed data from parseTXT method.
 * chatMessagePattern and datePattern are pattern for using regex.
 * Pattern c and d are for parsing. 
 * 
 * @author sua
 */
public class DataReaderForTXT {
	ArrayList<String> TXTData;
	ArrayList<String[]> parsedData;

	final String chatMessagePattern ="\\[(.+)\\]\\s\\[(.+)\\s([0-9]+):([0-9]+)\\]\\s(.+)";
	final String datePattern ="-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s.+\\s-+";
	final Pattern c = Pattern.compile(chatMessagePattern);
	final Pattern d = Pattern.compile(datePattern);

	DataReaderForTXT(ArrayList<String> td){
		this.TXTData= td;
	}

	/**
	 * parseTXT is the main method in DataReaderForTXT.
	 * by using chooseParseType method, distinguish the type of string.
	 * if the type is the 'data', use parseDate method.
	 * or just 'message,  use parseLine method.
	 * 
	 * @return data is the ArrayList which contains parsed string array.
	 */
	public ArrayList<String[]> parseTXT() {
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[] s;
		String date= "";
		
		for(String line : TXTData) {
			int kind = chooseParseType(line);
			if(kind == 1) {
				date = parseDate(line);
			}
			else if(kind == 2) {
				s = parseLine(line, date);
				data.add(s);
			}

		} 
		System.out.println("Finish to output/ TXT");
		parsedData = data;
		return data;
	}

	private int chooseParseType(String line) {
		Matcher matchWithChat = c.matcher(line);
		Matcher matchWithDate = d.matcher(line);

		if(matchWithDate.find()) return 1;
		else if(matchWithChat.find()) return 2;
		else return -1;
	}

	private String[] parseLine(String line, String date) {
		String s[] = new String[3];
		Matcher matchWithChat = c.matcher(line);

		if(matchWithChat.find()) {
			String name = matchWithChat.group(1);
			String amOrPm= matchWithChat.group(2);
			String hour = matchWithChat.group(3);
			String minute = matchWithChat.group(4);
			String message = matchWithChat.group(5);

			String convertedTime = convertTimeData(amOrPm, hour, minute);
			s[0] = name;
			s[1] = date + convertedTime;
			s[2] = message;
		}
		return s;
	}

	private String parseDate(String line) {
		Matcher matchWithDate = d.matcher(line);
		String date ="";
		if(matchWithDate.find()) {
			String year = matchWithDate.group(1);
			int month = Integer.parseInt(matchWithDate.group(2));
			int day = Integer.parseInt(matchWithDate.group(3));
			date = year + String.format("%02d", month) + String.format("%02d",day);
		}
		return date;
	}

	private String convertTimeData(String amOrPm, String hour, String minute) {
		int hourInt = Integer.parseInt(hour);
		if(amOrPm.equals("오후")) {
			hour = Integer.toString(hourInt+12);
		}
		else hour = String.format("%02d", hourInt);

		return hour + minute;
	}
}
