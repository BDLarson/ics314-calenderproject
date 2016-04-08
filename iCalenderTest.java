package ics314;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class iCalenderTest {
	iCalender cal = new iCalender();
    
	@Test
	public void testSummary() throws IOException {
	//////Each String to check, is what the output is in the .ics file/////////
		assertEquals("SUMMARY:School\n", cal.setSummary());
		assertEquals("SUMMARY:Homework\n", cal.setSummary());
		
		System.out.println("");
		System.err.println("NOW ADD SPACES");
		System.out.println("");
		
		assertEquals("SUMMARY:Work\n", cal.setSummary());
		assertEquals("SUMMARY:Sleep\n", cal.setSummary());
		
		System.out.println("");
		System.err.println("NOW SAY NO");
		System.out.println("");
		
		assertEquals("SUMMARY:none\n", cal.setSummary());
	}
	
	@Test
	public void testDescription() {
		//////Each String to check, is what the output is in the .ics file/////////
		assertEquals("SUMMARY:Going to school\n", cal.getSummary());
		assertEquals("SUMMARY:Doing homework\n", cal.getSummary());
		
		System.out.println("");
		System.err.println("NOW ADD SPACES");
		System.out.println("");
		
		assertEquals("SUMMARY:Going to work\n", cal.getSummary());
		assertEquals("SUMMARY:Sleeping\n", cal.getSummary());
		
		System.out.println("");
		System.err.println("NOW SAY NO");
		System.out.println("");
		
		assertEquals("SUMMARY:none\n", cal.getSummary());
	}
	
	@Test
	public void testDate() throws IOException {
		assertEquals("20160311", cal.getDate());
		
		System.out.println("");
		System.err.println("NOW ADD MORE THAN 8");
		System.out.println("");
		
		assertNotSame("201603111", cal.getDate());
		
		System.out.println("");
		System.err.println("NOW ADD LESS THAN 8");
		System.out.println("");
		
		assertNotSame("2016031", cal.getDate());
	}
	
	@Test
	public void testTime() throws IOException {
		assertEquals(1700, cal.getStartTime());
		assertEquals(1715, cal.getEndTime());
		assertEquals("1700-1715", cal.getTime());
		assertEquals("300-1200", cal.getTime());
	}
	
	@Test
	public void testClassification() {
		assertEquals("CLASS:PRIVATE\n", cal.getClassification());
		assertEquals("CLASS:CONFIDENTIAL\n", cal.getClassification());
		assertEquals("CLASS:PUBLIC\n", cal.getClassification());
		
		System.out.println("");
		System.err.println("NOW SAY YES BUT ENTER NOTHING");
		System.out.println("");
		
		assertEquals("CLASS:PUBLIC\n", cal.getClassification());
		
		System.out.println("");
		System.err.println("NOW SAY NO");
		System.out.println("");
		
		assertEquals("CLASS:PUBLIC\n", cal.getClassification());
	}
	
	@Test
	public void testGeo() throws Exception {
		assertEquals("GEO:21.3072003;-157.8123004\n", cal.getGeo()); //University of Hawaii Manoa
		assertEquals("GEO:33.6518297;-117.8389422\n", cal.getGeo()); //Stanford University
		
		System.out.println("");
		System.err.println("NOW SAY NO BUT ENTER COORDINATES");
		System.out.println("");
		
		assertEquals("GEO:21.3072003;-157.8123004\n", cal.getGeo()); //University of Hawaii Manoa
		assertEquals("GEO:33.6518297;-117.8389422\n", cal.getGeo()); //Stanford University
		
		System.out.println("");
		System.err.println("NOW SAY NO TWICE BUT ENTER A LOCATAION NAME");
		System.out.println("");
		
		assertEquals("LOCATION:University of Hawaii Manoa\n", cal.getGeo()); //University of Hawaii Manoa
		assertEquals("LOCATION:Stanford University\n", cal.getGeo()); //Stanford University
		
		System.out.println("");
		System.err.println("NOW SAY NO ALL THE TIME");
		System.out.println("");
		
		assertEquals("null, null", cal.getGeo());
	}
}
