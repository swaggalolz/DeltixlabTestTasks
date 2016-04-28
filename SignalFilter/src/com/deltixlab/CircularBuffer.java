package com.deltixlab;

public final class CircularBuffer {
	
	 private long[] elements;
	 private int offset = 0;
	 
	 @SuppressWarnings("unchecked")
	 public CircularBuffer(int size) {
		 if (size <= 0)
	            throw new IllegalArgumentException("CircularBuffer capacity must be positive.");
		 elements = new long[size];
	 }
	 
	 public  void add(long elem)  {
	        elements[offset] = elem;
	        offset = (offset + 1) % elements.length;
	        if(unconsumedElements < elements.length)
	        	++unconsumedElements;
	 }


	 private int unconsumedElements = 0;
	 
	 public  long peek()  {
	        return elements[(offset + (elements.length - unconsumedElements)) % elements.length];
	 }
	 
	 public  int size() {
	        return unconsumedElements;
	 }
	 
}
