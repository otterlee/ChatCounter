package edu.handong.csee.java.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HashMapCount extends HashMap<String, Integer> {
	String name;
	String count;

	public List sortByCount(HashMap map) {
		List<String> list = new ArrayList();
		list.addAll(map.keySet());

		Collections.sort(list,new Comparator() {

			public int compare(Object o1,Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable) v2).compareTo(v1);
			}

		});
		return list;
	}
}


