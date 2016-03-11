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
		assertEquals("SUMMARY:School\n", cal.getSummary());
		assertEquals("SUMMARY:Homework\n", cal.getSummary());
		
		System.out.println("");
		System.err.println("NOW ADD SPACES");
		System.out.println("");
		
		assertEquals("SUMMARY:Work\n", cal.getSummary());
		assertEquals("SUMMARY:Sleep\n", cal.getSummary());
		
		System.out.println("");
		System.err.println("NOW SAY NO");
		System.out.println("");
		
		assertEquals("SUMMARY:none\n", cal.getSummary());
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
		//TODO
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
	public void testGeo() {
		
	}
}
