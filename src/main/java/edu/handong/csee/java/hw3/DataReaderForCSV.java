package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.opencsv.*;
public class DataReaderForCSV {
	ArrayList<String> CSVData;
	ArrayList<String[]> data;
	ArrayList <ArrayList<String>> messages;
	final String pattern = "(.+),\\\"(.+)\\\",\\\"(.+)\\\"";
	final Pattern p = Pattern.compile(pattern);

	DataReaderForCSV(ArrayList<String> csvData){
		this.CSVData = csvData;
	}

	public ArrayList<String[]> parseCSV() {
		this.data = new ArrayList<String[]>();
		String fileName = "outCSV.txt";
		PrintWriter outputStream = null;

		System.out.println ("Those lines were written to " + fileName);
		try {
			outputStream = new PrintWriter(fileName);
			for(String line : CSVData) {
				Matcher matcher = p.matcher(line);

				if(matcher.find()) {
					if(matcher.group(1).equals("Data")) {
						continue;
					}
					//System.out.println(line);
					String timeData = mergeDatetime(matcher.group(1));
					String []m = {matcher.group(2), timeData, matcher.group(3)};
					outputStream.println("1) name : " + m[0]);
					//System.out.println(m[0]);
					outputStream.println("2) time : " + m[1]);
					outputStream.println("3) message : " + m[2]);
					data.add(m);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Finish to output/ CSV");
		
		outputStream.close();
		return data;
	}

	private String mergeDatetime(String dateTime) {
		String pattern = "([0-9]+)-([0-9]+)-([0-9]+)\\s([0-9]+):([0-9]+):([0-9]+)";
		Pattern d = Pattern.compile(pattern);
		Matcher matchWithDate = d.matcher(dateTime);
		if(matchWithDate.find()) {
			String date = matchWithDate.group(1)+matchWithDate.group(2)+matchWithDate.group(3);
			String time = matchWithDate.group(4)+matchWithDate.group(5);
			String mergedTime = date + time;
			return mergedTime;
		}
		String errorMessage = "Error in reading date and time";
		return errorMessage;
	}
}
