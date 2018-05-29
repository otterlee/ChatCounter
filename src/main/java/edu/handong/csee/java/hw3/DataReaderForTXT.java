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

public class DataReaderForTXT {
	ArrayList<String> TXTData;
	//HashMap<String, String[]> data;
	final String chatMessagePattern ="\\[(.+)\\]\\s\\[(.+)\\s([0-9]+):([0-9]+)\\]\\s(.+)";
	final String datePattern ="-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s.+\\s-+";
	final Pattern c = Pattern.compile(chatMessagePattern);
	final Pattern d = Pattern.compile(datePattern);

	DataReaderForTXT(ArrayList<String> td){
		this.TXTData= td;
	}

	/*public void readTXT() throws IOException {
		data = parseTXT(TXTFiles);
		//parseDatetime()
	}*/

	public ArrayList<String[]> parseTXT() {
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[] s;
		String date= "";
		String fileName = "outTXT.txt";
		PrintWriter outputStream = null;
		System.out.println("Im in parseTXT");
		System.out.println("TXTData size is "+TXTData.size());
		
		try{
			outputStream = new PrintWriter(fileName);
			for(String line : TXTData) {
				//br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
				
				//System.out.println(line);
				int kind = chooseParseType(line);
				//System.out.println("kind : "+kind);
				if(kind == 1) {
					date = parseDate(line);
					//System.out.println(date);
				}
				//날짜 라인이면 날짜 미리 parse해놓아야 한다.
				else if(kind == 2) {
					s = parseLine(line, date);
					//time에 date 합치고서 return 하였음.
					data.add(s);
					outputStream.println("1) name : " + s[0]);
					outputStream.println("2) time : " + s[1]);
					outputStream.println("3) message : " + s[2]);
				}
				//else System.out.println("Fail to parse");
			}

		} catch (FileNotFoundException e) {
			System.out.println ("Error writing the file");
			e.printStackTrace();
			System.exit(0);
		} 
		System.out.println("Finish to output/ TXT");
		outputStream.close();

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
