package ics314;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class iCalenderTest {
	//iCalender cal = new iCalender();
	
	@Test
	public void testName() throws IOException {
		iCalender alpha = new iCalender();
	}
	
	@Test
	public void testlocation() {
		iCalender alpha = new iCalender();
		assertEquals("Seattle", alpha.getLocation());
	}
}
