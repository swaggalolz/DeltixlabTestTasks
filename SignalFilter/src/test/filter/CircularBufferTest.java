package test.filter;



import com.deltixlab.CircularBuffer;

import junit.framework.TestCase;

public class CircularBufferTest extends TestCase {

	public void testAdd() {
		CircularBuffer circularBuffer = new CircularBuffer(3);
		circularBuffer.add(1);
		circularBuffer.add(2);
		circularBuffer.add(3);
		assertEquals(1,circularBuffer.peek());
		circularBuffer.add(4);
		assertEquals(2,circularBuffer.peek());
	}
	
}
