package edu.handong.csee.java.hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReaderForTXT {
	ArrayList<File> TXTFiles;
	ArrayList<String[]> data;
	//HashMap<String, String[]> data;
	final String chatMessagePattern ="\\[(.+)\\]\\s\\[(.+)\\s([0-9]+):([0-9]+)\\]\\s(.+)";
	final String datePattern ="-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s.+\\s-+";
	final Pattern c = Pattern.compile(chatMessagePattern);
	final Pattern d = Pattern.compile(datePattern);

	DataReaderForTXT(ArrayList<File> tf){
		this.TXTFiles = tf;
	}

	/*public void readTXT() throws IOException {
		data = parseTXT(TXTFiles);
		//parseDatetime()
	}*/

	public ArrayList<String[]> parseTXT(ArrayList<File> files) {
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[] s;
		String date= "";
		String line= "";
		BufferedReader br = null;
		for(File f :files) {
			System.out.println("****"+f.getName());
			try{
				br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
				while((line = br.readLine()) != null) {
					line = br.readLine();
					System.out.println(line);
					//line이 메세지인지, 날짜 문장인지 구분
					if(chooseParseType(line) == 1) {
						date = parseDate(line);
						System.out.println(date);
					}
					
					//날짜 라인이면 날짜 미리 parse해놓아야 한다.
					else if(chooseParseType(line) == 2) {
						s = parseLine(line, date);
						//time에 date 합치고서 return 하였음.
						data.add(s);
					}
				}

			} catch (IOException e) {
				System.out.println ("Error opening the file" + f.getName());
				e.printStackTrace();
				System.exit(0);
			} 
		}
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

			System.out.println("name=" + s[0]);
			System.out.println("time=" + s[1]);
			System.out.println("strMessage=" + s[2]);
		}
		return s;
	}

	private String parseDate(String line) {
		Matcher matchWithDate = d.matcher(line);
		String date ="";
		if(matchWithDate.find()) {
			String year = matchWithDate.group(1);
			int month = Integer.parseInt(matchWithDate.group(2));
			String day = matchWithDate.group(3);
			date = year + String.format("%02d", month) + day;
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
