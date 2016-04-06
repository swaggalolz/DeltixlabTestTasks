package com.deltixlab;

import java.awt.SecondaryLoop;

import com.deltixlab.SortedLimitedList.Entry;

/**
 * Created by VavilauA on 6/19/2015.
 */
public class Processor {
    public Processor(long limit) {
        this.limit = limit;
    }

    private long limit;
        
    public void doProcess(SortedLimitedList<Double> mustBeEqualTo, SortedLimitedList<Double> expectedOutput) {
    	mustBeEqualTo.clear();
    	Entry<Double> elementFromExpectedOutput  = expectedOutput.getFirst();
    	mustBeEqualTo.addFirst(elementFromExpectedOutput.getValue());
    	Entry<Double> elementFromMustBeEqualTo  = mustBeEqualTo.getFirst();
    	elementFromExpectedOutput = elementFromExpectedOutput.getNext();
    	while(null != elementFromExpectedOutput){
    		mustBeEqualTo.addAfter(elementFromMustBeEqualTo, elementFromExpectedOutput.getValue());
    		elementFromMustBeEqualTo = elementFromMustBeEqualTo.getNext();
    		elementFromExpectedOutput = elementFromExpectedOutput.getNext();
    	}
    }
	
}