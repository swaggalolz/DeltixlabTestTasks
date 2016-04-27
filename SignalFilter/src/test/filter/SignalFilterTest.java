package test.filter;

import com.deltixlab.SignalFilter;

import junit.framework.TestCase;
	
public class SignalFilterTest extends TestCase {

	
	public void testExceptionMessage() {
		try {
			new SignalFilter(0);
	    } catch (IllegalArgumentException anIllegalArgumentException) {
	    	   assertEquals("CircularBuffer capacity must be positive.", anIllegalArgumentException.getMessage());
	    }
	}
	
	public void testFilterWorkWihoutDelay() {
		SignalFilter filter = new SignalFilter(3);
		assertTrue(filter.isSignalAllowed());
		assertTrue(filter.isSignalAllowed());
		assertTrue(filter.isSignalAllowed());
		assertEquals(false,filter.isSignalAllowed());
	}
	
	public void testFilterWorkWithDelay() throws InterruptedException {
		SignalFilter filter = new SignalFilter(2);
		assertTrue(filter.isSignalAllowed());
		Thread.sleep(30000);
		assertTrue(filter.isSignalAllowed());
		assertEquals(false,filter.isSignalAllowed());
		Thread.sleep(30000);
		assertTrue(filter.isSignalAllowed());		
	}

	

}
