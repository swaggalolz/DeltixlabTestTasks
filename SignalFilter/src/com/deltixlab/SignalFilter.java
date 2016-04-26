package com.deltixlab;

public class SignalFilter implements Filter {

	public SignalFilter(int N) {
		this.N = N - 1;
		this.limitTimeInterval = 60 * 1000;
		signalGenerations = new CircularBuffer<Long>(N - 1);
	}

	private final int N;
	private final long limitTimeInterval;
	private CircularBuffer<Long> signalGenerations;

	@Override
	public synchronized boolean isSignalAllowed() {
		final long timeNow = System.currentTimeMillis();
		if (this.signalGenerations.size() < N) {
			this.signalGenerations.add(timeNow);
			return true;
		} else {
			final long currentTimeInterval = timeNow - this.signalGenerations.peek();
			if (currentTimeInterval > this.limitTimeInterval) {
				this.signalGenerations.add(timeNow);
				return true;
			} else {
				return false;
			}
		}
	}

}
