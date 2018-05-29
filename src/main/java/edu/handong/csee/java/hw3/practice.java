package edu.handong.csee.java.hw3;


public class practice<N, C extends Number> {

	NameCountPair<N, C> pairList[] = new NameCountPair[100];

	public C get(N name) {
		int index = getIndex(name);
		NameCountPair<N,C> list = pairList[index];
		return getMatchCount(list, name);
	}

	public void put(N name, C count) {
		int index = getIndex(name);
		storeCount(index, name, count);
	}

	public void remove(N name) {
		int index = getIndex(name);
		NameCountPair<N,C> list = pairList[index];
		if (list == null)
			return;
		// if only one element is present in the list ,set the index to null
		if(list.getName().equals(name)){
			if (list.next == null){
				pairList[index] = null;
				return;
			}
		}
		NameCountPair<N,C> prev = null;
		do{
			if(list.name.equals(name)){
				if (prev == null){
					list = list.getNext();
				}else{
					prev.next = list.getNext();
				}
				break;
			}
			list = list.next;
		}while(list != null);

		pairList[index] = list;
	}

	/*
	 * find the match value and return , if not found either throw exception or return null.
	 */
	private C getMatchCount(NameCountPair<N,C> list, N name) {
		while (list != null) {
			if (list.getName().equals(name))
				return list.getCount();
			list = list.next;
		}
		return null;
	}

	private void storeCount(int index, N name, C count) {
		NameCountPair<N, C> list = pairList[index];

		// if list is empty , enter as first element
		if (list == null) {
			pairList[index] = new NameCountPair<N, C>(name, count);
		} else {
			boolean done = false;
			// traverse through list , if a key is found ,replace the value or add it at the end of the list
			while(list.next != null) {
				if (list.getName().equals(name)) {
					list.setCount(count);
					done = true;
					break;
				} 
				list = list.next;
			}
			// add at the end of the list
			if (!done)
				list.next = new NameCountPair<N, C>(name, count);
		}

	}

	private int getIndex(N name) {
		int hash = name.hashCode();
		return hash % 100;
	}

	private int size() {
		int i;
		for( i = 0; i < pairList.length; i++) {
			if(pairList[i].getCount()== null) break;
		}
		return i+1;
	}

}

class NameCountPair<N, C> {
	N name;
	C count;
	NameCountPair<N, C> next = null;

	public NameCountPair<N,C> getNext() {
		return next;
	}

	public void setNext(NameCountPair<N,C> next) {
		this.next = next;
	}

	public NameCountPair(N name, C count) {
		super();
		this.name= name;
		this.count = count;
	}

	public N getName() {
		return name;
	}

	public void setName(N name) {
		this.name = name;
	}

	public C getCount() {
		return count;
	}

	public void setCount(C count) {
		this.count = count;
	}

}

