package ics314;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class iCalenderTest {
	iCalender cal = new iCalender();
	
	@Test
	public void testSummary() throws IOException {
		assertEquals("", cal.getSummary());
	}
	
	@Test
	public void testDescription() {
		assertEquals("", cal.getDescription());
	}
	
	@Test
	public void testlocation() {
		assertEquals("LOCATION:Seattle\n", cal.getLocation());
	}
	
	@Test
	public void testDate() throws IOException {
		assertEquals("20160311", cal.getDate());
	}
	
	@Test
	public void testTime() throws IOException {
		assertEquals("", cal.getTime());
	}
	
	@Test
	public void testClassification() {
		assertEquals("", cal.getClassification());
	}
}
