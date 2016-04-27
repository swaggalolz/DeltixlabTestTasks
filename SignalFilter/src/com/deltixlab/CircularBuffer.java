package com.deltixlab;

public final class CircularBuffer<T> {
	
	 private  T[] elements;
	 private int offset = 0;
	 
	 @SuppressWarnings("unchecked")
	 public CircularBuffer(int size) {
		 if (size <= 0)
	            throw new IllegalArgumentException("CircularBuffer capacity must be positive.");
		 elements = (T[]) new Object[size];
	 }
	 
	 public  void add(T elem)  {
	        elements[offset] = elem;
	        offset = (offset + 1) % elements.length;
	        ++unconsumedElements;
	 }
	 public  void offer(T elem)  {
	        elements[offset] = elem;
	        offset = (offset + 1) % elements.length;
	 }

	 private int unconsumedElements = 0;
	 
	 public  T peek()  {
	        return elements[(offset + (elements.length - unconsumedElements)) % elements.length];
	 }
	 
	 public  int size() {
	        return unconsumedElements;
	 }
	 
}
