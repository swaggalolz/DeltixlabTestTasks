package com.deltixlab;

import com.deltixlab.Processor;
import com.deltixlab.SortedLimitedList;

public class Main {

	public static void main(String[] args) {
	
		
		Processor processor = new Processor(5);
		SortedLimitedList<Double> sortedLimitedList = new SortedLimitedList<Double>(6);
		sortedLimitedList.addFirst(1.0);
		sortedLimitedList.addAfter(sortedLimitedList.getFirst(), 1.5);
		sortedLimitedList.addAfter(sortedLimitedList.getFirst().getNext(), 2.0);
		sortedLimitedList.addAfter(sortedLimitedList.getFirst().getNext().getNext(), 2.5);
		sortedLimitedList.addAfter(sortedLimitedList.getFirst().getNext().getNext().getNext(), 3.0);
		sortedLimitedList.addAfter(sortedLimitedList.getFirst().getNext().getNext().getNext().getNext(), 3.5);
		
		SortedLimitedList<Double> sortedLimitedList2 = new SortedLimitedList<Double>(5);
		sortedLimitedList2.addFirst(0.5);
		sortedLimitedList2.addAfter(sortedLimitedList2.getFirst(), 0.6);
		sortedLimitedList2.addAfter(sortedLimitedList2.getFirst().getNext(), 0.7);
		sortedLimitedList2.addAfter(sortedLimitedList2.getFirst().getNext().getNext(), 2.6);
		sortedLimitedList2.addAfter(sortedLimitedList2.getFirst().getNext().getNext().getNext(), 8.0);
		
		processor.doProcess(sortedLimitedList, sortedLimitedList2);

		System.out.print(sortedLimitedList2.equals(sortedLimitedList));
		

	}
	
	

}