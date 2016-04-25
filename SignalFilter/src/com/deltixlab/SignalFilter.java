package com.deltixlab;

import java.util.LinkedList;
import java.util.Queue;

public /*final*/ class SignalFilter implements Filter {

	public SignalFilter(int N) {
		this.N = N - 1 ;
		this.limitTimeInterval = 60 * 1000;
		signalGenerations = new LinkedList<Long>();
	}

	private final int N;
	private final long limitTimeInterval;
	private Queue<Long> signalGenerations;
	
	//private long timeNow ;
	//private long currentTimeInterval;
	
	@Override
	public /*final*/ synchronized  boolean isSignalAllowed() {	
		long timeNow = System.currentTimeMillis();
		if ( this.signalGenerations.size() < N) {
			this.signalGenerations.offer(timeNow);
			return true;
		} else {
			long currentTimeInterval =  timeNow - this.signalGenerations.peek();
			if (currentTimeInterval > this.limitTimeInterval) {
				this.signalGenerations.poll();
				this.signalGenerations.offer(timeNow);
				return true;
			} else {
				return false;
			}
		}
	}
	

}
