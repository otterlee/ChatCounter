package edu.handong.csee.java.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.*;;

public class DataReaderForCSV {
	ArrayList<File> CSVFiles;
	ArrayList<String[]> data;
	ArrayList <ArrayList<String>> messages;

	DataReaderForCSV(ArrayList<File> cf){
		this.CSVFiles = cf;
	}
	
	public ArrayList<String[]> parseCSV(ArrayList<File> files) {
		this.data = new ArrayList<String[]>();
		String[] s;
		for(File f : files) {
			try {
				CSVReader reader = new CSVReader(new FileReader(f));
				while ((s = reader.readNext()) != null) {
					s[0] = parseDatetime(s[0]);
					String []m = {s[1], s[0], s[2]};
					System.out.println(m[0] +" @@ "+m[1]+" @@ "+m[2]);
					data.add(m);
				}
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	private String parseDatetime(String dateTime) {
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
