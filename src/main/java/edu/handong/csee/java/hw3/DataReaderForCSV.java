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

/**
 * DataReaderForCSV class is the class that parse the strings from csv file and put to CSVData ArrayList.
 * @author suagu
 * 
 * CSVData is the raw data from DataReader class.
 * parsedData is parsed data from parseCSV method.
 * pattern is the pattern type of string from csv file.
 * Pattern p is for parsing. 
 * 
 */

public class DataReaderForCSV {
	ArrayList<String> CSVData;
	ArrayList<String[]> data;
	ArrayList <ArrayList<String>> messages;
	final String pattern = "(.+),\\\"(.+)\\\",\\\"(.+)\\\"";
	final Pattern p = Pattern.compile(pattern);

	DataReaderForCSV(ArrayList<String> csvData){
		this.CSVData = csvData;
	}

	/**
	 * ParseCSV method is the main method in this class.
	 * Use method mergeDatetime to make date and time easy to compare in check redundancy).
	 * put parsed data into string array and put string array to data which is arrayList which contains parsed data.
	 * 
	 * @return data is the parsed data.
	 */

	public ArrayList<String[]> parseCSV() {
		this.data = new ArrayList<String[]>();

		for(String line : CSVData) {
			Matcher matcher = p.matcher(line);

			if(matcher.find()) {
				if(matcher.group(1).equals("Data")) {
					continue;
				}
				String timeData = mergeDatetime(matcher.group(1));
				String []m = {matcher.group(2), timeData, matcher.group(3)};
				data.add(m);
			}

		} 

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
