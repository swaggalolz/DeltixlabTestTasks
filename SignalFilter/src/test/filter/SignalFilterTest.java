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
	
	public void testFilterWork() {
		SignalFilter filter = new SignalFilter(1);
		assertTrue(filter.isSignalAllowed());
		assertEquals(false,filter.isSignalAllowed());
	}

	

}
