package com.deltixlab;

import java.util.LinkedList;
import java.util.Queue;

public class SignalFilter implements Filter {

	public SignalFilter(int N, long limitTimeInterval) {
		this.N = N;
		this.limitTimeInterval = limitTimeInterval * 1000;
		this.signalGenerations = new LinkedList<Long>();

	}

	private int N;
	private long limitTimeInterval;
	private Queue<Long> signalGenerations;

	// It uses the synchronized method because at bytecode it is less than a
	// synchronized block at the expense of set ACC_SYNCHRONIZED flag at
	// method_info
	@Override
	public synchronized boolean isSignalAllowed() {
		long timeNow = System.currentTimeMillis();
		if (this.signalGenerations.size() < this.N - 1) {
			signalGenerations.offer(timeNow);
			return true;
		} else {
			long currentTimeInterval = timeNow - this.signalGenerations.peek();
			if (currentTimeInterval > this.limitTimeInterval) {
				this.signalGenerations.poll();
				this.signalGenerations.offer(timeNow);
				return true;
			} else {
				return false;
			}
		}

	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public long getLimitTimeInterval() {
		return limitTimeInterval;
	}

	public void setLimitTimeInterval(long limitTimeInterval) {
		this.limitTimeInterval = limitTimeInterval;
	}

}
