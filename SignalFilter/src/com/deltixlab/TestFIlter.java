package com.deltixlab;


public class TestFIlter implements Filter {

    public TestFIlter(int N) {
        this.N = N ;
        this.limitTimeInterval = 60 * 1000;
        signalGenerations = new CircularBuffer(N);
    }

    private final int N;
    private final long limitTimeInterval;
    private CircularBuffer signalGenerations;


    @Override
    public synchronized boolean isSignalAllowed() {
        long timeNow = System.currentTimeMillis();
        if (this.signalGenerations.size() < N) {
            this.signalGenerations.add(timeNow);
            return true;
        } else {
            long currentTimeInterval = timeNow - this.signalGenerations.peek();
            if (currentTimeInterval >= this.limitTimeInterval) {
                this.signalGenerations.add(timeNow);
                return true;
            } else {
                return false;
            }
        }
    }

}
