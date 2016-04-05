package com.deltixlab;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class SignalFilter implements Filter {

	public SignalFilter(int N, long limitTimeInterval) {
		this.N = N;
		this.limitTimeInterval = limitTimeInterval * 1000;
		this.signalGenerations = new ArrayBlockingQueue<Long>(N - 1);

	}

	private int N;
	private long limitTimeInterval;
	private Queue<Long> signalGenerations;

	
	@Override
	public synchronized boolean isSignalAllowed() {

		if (this.signalGenerations.size() < this.N - 1) {
			signalGenerations.offer(System.currentTimeMillis());
			return true;
		} else {
			long timeNow = System.currentTimeMillis();
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
