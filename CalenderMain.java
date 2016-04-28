package ics314;

import java.util.Scanner;

public class CalenderMain {

	public static void main(String[] args) throws Exception {
	    Scanner sc = new Scanner(System.in);
	    Calender cal = new Calender();
		System.out.println("What do you want to do? (Please enter a number)" );
		System.out.println("1. Create new .ics file?");
		System.out.println("2. Import new .ics file?");
		System.out.print(">>>");
	    String next = sc.nextLine();

		if (next.equalsIgnoreCase("1")) {
			cal.addEvent(); //Start creating files
			cal.sortEvents(); //Start sorting files
			cal.printAllEvents(); //Print out all events in order by start time
			cal.getGreatCircleDistance();
		} 
		if (next.equalsIgnoreCase("2")) {
			cal.addPreCreated(); //Start importing files
			cal.sortEvents(); //Start sorting files
			cal.printAllEvents(); //Print out all events in order by start time
			//System.out.println("Nothing programmed yet.");
		} 
		System.out.println("Thanks! Exiting program!");	}
}
