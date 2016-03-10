package ics314;

public abstract class calEssentials {
	protected String version = "VERSION:1.0\n";
	protected String prodid = "PRODID:Team Quinze\n";
	protected String uid = "UID:blake7@hawaii.edu\n";
	protected String startTime;
	protected String endTime;
	protected String location;
	protected String summary;
	protected String description;
	protected String classification;
	protected String geo;
	protected String calBegin = "BEGIN:VCALENDAR\n";
	protected String calEnd = "END:VCALENDAR\n";
	protected String eventBegin = "BEGIN:VEVENT\n";
	protected String eventEnd = "END:VEVENT\n";
}
