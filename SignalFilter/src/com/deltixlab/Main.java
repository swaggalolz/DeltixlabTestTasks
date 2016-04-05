package com.deltixlab;

public class Main {

	public static void main(String[] args) {
	
		Filter frequencyFilter = new SignalFilter(5, 100);
		while (true) {

			Signal signal = waitForSignal();

			if (frequencyFilter.isSignalAllowed()) {

				transmit(signal);
			}
		}

	}
	
	public static Signal waitForSignal(){
		// TODO something
		return null;
	}
	
	public static void transmit(Signal signal){
		// TODO something
	}
	

}
