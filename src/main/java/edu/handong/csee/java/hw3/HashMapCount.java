package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapCount extends HashMap<String, Integer> {

	public void run() {
		List<Student> list = new ArrayList<Student>();
		HashMapCount 
		Collections.sort(list, new Comparator<Student>() {
			@Override
			public int compare(HashMapCount.get(name), HashMapCount h2) {
				if (h1.getCount() < 2.getCount()) {
					return -1;
				} else if (s1.getcount() > s2.getcount()) {
					return 1;
				}
				return 0;
			}
		});

		 for (Student s : list) {
			 System.out.println(s.getcount());
		 }
	}
}

class HashMapCount {
	String name;
	int count;

	public HashMapCount(String name, int count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return this.name;
	}

	public int getCount() {
		return this.count;
	}
}

