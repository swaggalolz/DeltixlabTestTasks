package com.deltixlab;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SignalFilter implements Filter {

	public SignalFilter(int N, long limitTimeInterval) {
		this.N = N;
		this.limitTimeInterval = limitTimeInterval * 1000;
		this.signalGenerations = new ArrayBlockingQueue<Long>(N-1);
		this.lock = new ReentrantLock();
	}

	private int N;
	private long limitTimeInterval;
	private BlockingQueue<Long> signalGenerations;
	private Lock lock;

	@Override
	public boolean isSignalAllowed() {

		if (this.signalGenerations.size() < this.N-1) {
			signalGenerations.offer(System.currentTimeMillis());
			return true;
		} else {
			synchronized (lock) {
				long timeNow = System.currentTimeMillis();
				long currentTimeInterval = timeNow- this.signalGenerations.peek();
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
