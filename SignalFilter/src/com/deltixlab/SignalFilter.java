package com.deltixlab;


public class SignalFilter implements Filter {

	public SignalFilter(int N) {
		this.N = N ;
		this.limitTimeInterval = 60 * 1000;
		signalGenerations = new CircularBuffer(N);
	}

	private final int N;
	private final long limitTimeInterval;
	private CircularBuffer signalGenerations;

	private  long timeNow;
	private  long currentTimeInterval;

	@Override
	public synchronized boolean isSignalAllowed() {
		timeNow = System.currentTimeMillis();
		if (this.signalGenerations.size() < N) {
			this.signalGenerations.add(timeNow);
			return true;
		} else {
			currentTimeInterval = timeNow - this.signalGenerations.peek();
			if (currentTimeInterval >= this.limitTimeInterval) {
				this.signalGenerations.add(timeNow);
				return true;
			} else {
				return false;
			}
		}
	}

}
