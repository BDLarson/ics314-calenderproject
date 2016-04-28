package ics314;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestingProgram {

	@Test
	public void testEventName() {
		Event e = new Event();
		assertNull(e.getEventName());
		e.setEventName("event1");
		assertEquals("event1",e.getEventName());
		e.setEventName("event1 + event2");
		assertEquals("event1 + event2", e.getEventName());
		e.setEventName("");
		assertEquals("", e.getEventName());
		e.setEventName(" ");
		assertEquals(" ", e.getEventName());
	}
	
	@Test
	public void testDescription() {
		Event e = new Event();
		assertNull(e.getDescription());
		e.setDescription("Party");
		assertEquals("Party",e.getDescription());
		e.setDescription("Party in New York City");
		assertEquals("Party in New York City", e.getDescription());
		e.setDescription("");
		assertEquals("", e.getDescription());
		e.setDescription(" ");
		assertEquals(" ", e.getDescription());
	}

	@Test
	public void testDate() {
		Event e = new Event();
		assertNull(e.getDate());
		e.setDate("20160417");
		assertEquals("20160417",e.getDate());
	}
	
	@Test
	public void testStartTime() {
		Event e = new Event();
		assertEquals(0, e.getStartTime());
		e.setStartTime(1230);
		assertEquals(1230, e.getStartTime());
	}
	
	@Test
	public void testEndTime() {
		Event e = new Event();
		assertEquals(0, e.getEndTime());
		e.setEndTime(1230);
		assertEquals(1230, e.getEndTime());
	}
	
	@Test
	public void testClassification() {
		Event e = new Event();
		assertEquals(null, e.getClassification());
		e.setClassification("PUBLIC");
		assertEquals("PUBLIC", e.getClassification());
		e.setClassification("PRIVATE");
		assertEquals("PRIVATE", e.getClassification());
		e.setClassification("CONFIDENTIAL");
		assertEquals("CONFIDENTIAL", e.getClassification());
	}
	
	@Test
	public void testLatitude() {
		Event e = new Event();
		assertNull(e.getLat());
		e.setLat("40.7127837");
		assertEquals("40.7127837", e.getLat());
	}
	
	@Test
	public void testLongitude() {
		Event e = new Event();
		assertNull(e.getLong());
		e.setLong("-74.0059413");
		assertEquals("-74.0059413", e.getLong());
	}
	
	@Test
	public void testGeographic() {
		Event e = new Event();
		assertNull(e.getGeo());
		e.setGeo("40.7127837", "-74.0059413");
		assertEquals("40.7127837;-74.0059413", e.getGeo());
	}
}
