package edu.handong.csee.java.hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * DataReaderForCSV class is the class that parse the strings from csv file and put to CSVData ArrayList.
 * @author suagu
 * 
 * allLines is raw data created by just file reading.
 * allParsdLines is parsed data from parseCSV method.
 * pattern is the pattern type of string from csv file.
 * Pattern p is for parsing. 
 * 
 */

public class DataReaderForCSVThread implements Runnable{
	ArrayList<String> allLines= new ArrayList<String>();
	ArrayList<String[]> allParsedLines= new ArrayList<String[]>();
	File CSVFile;
	final String pattern = "(.+),\\\"(.+)\\\",\\\"(.+)\\\"";
	final Pattern p = Pattern.compile(pattern);

	DataReaderForCSVThread(File file){
		this.CSVFile = file;
	}

	public void run() {
		readFileByLine(CSVFile);

	}

	public ArrayList<String[]> getParsedLines(){
		return allParsedLines;
	}
	/**
	 * ParseCSV method is the main method in this class.
	 * Use method mergeDatetime to make date and time easy to compare in check redundancy).
	 * put parsed data into string array and put string array to data which is arrayList which contains parsed data.
	 * 
	 * @return data is the parsed data.
	 */
	public void readFileByLine(File csvFile) {
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
			String line ="";

			while ((line = br.readLine()) != null) {
				int count = StringUtils.countMatches(line, "\"");
				if(!line.contains("\"")) continue;
				if(count == 1) continue;
				if(!line.endsWith("\"")) line = line+ "\"";
				allLines.add(line);
			}
		} catch (IOException e) {
			System.out.println ("Error opening the file" + csvFile.getName());
			System.exit(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		for(String line : allLines) {
			Matcher matcher = p.matcher(line);
			if(matcher.find()) {
				if(matcher.group(1).equals("Data")) {
					continue;
				}
				String timeData = mergeDatetime(matcher.group(1));
				String []m = {matcher.group(2), timeData, matcher.group(3)};
				allParsedLines.add(m);
			}
		} 
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
