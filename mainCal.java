package ics314;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import ics314.Geographic;
import ics314.iCalender;
import ics314.GreatCircle;

public class mainCal {
    Scanner reader = new Scanner(System.in);
	ArrayList<iCalender> allEvents = new ArrayList<iCalender>();
	iCalenderComparator eventComp = new iCalenderComparator();
	iCalender q = new iCalender();

	
	public void addEvent() throws Exception {
		boolean done = false;
		while(!done) {
			System.out.println("Do you want to add an event? (Y/N)");
			System.out.print(">>>");
			if (reader.nextLine().equalsIgnoreCase("Y")) {
				iCalender newEvent = new iCalender();
				newEvent.nameFile();
				allEvents.add(newEvent);
			} else {
				done = true;
			}
		}
	}
	
	//Sort the contents of the entire event array by start time
	public void sortEvents() {
		if (allEvents.size() > 1) {
			Collections.sort(allEvents, eventComp);
		}
	}
	
	//Print the contents of entire event array
	public void printAllEvents()
	{
		Iterator eventsItr = allEvents.iterator();
		System.out.println();
		System.out.print("<<<<<LIST OF EVENTS>>>>>\n");
		while(eventsItr.hasNext()){
			System.out.print(eventsItr.next().toString());
		}
	}
	
	public String getGreatCircleDistance() {
		if (allEvents.size() > 1) {
			for (int i = 0; i < allEvents.size()-1; i++) {
				GreatCircle newGC = new GreatCircle(allEvents.get(i).getLat(), allEvents.get(i).getLong(),
						allEvents.get(i+1).getLat(), allEvents.get(i+1).getLong());
				allEvents.get(i).setComment(newGC.getDistance());
				System.out.print(allEvents.get(i).getComment());
				System.out.print(allEvents.get(i+1).getComment() + "\n");
			}
			
		} else {
			System.out.println("No Great Circle Distance to Calculate.");
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
	    Scanner reader = new Scanner(System.in);
		mainCal cal = new mainCal();
		System.out.println("What do you want to do? (Please enter a number)" );
		System.out.println("1. Create new .ics file?");
		System.out.println("2. Import new .ics file?");
		System.out.print(">>>");
	    String next = reader.nextLine();

		if (next.equalsIgnoreCase("1")) {
			cal.addEvent(); //Start creating files
			cal.sortEvents(); //Start sorting files
			cal.printAllEvents(); //Print out all events in order by start time
			cal.getGreatCircleDistance();
		} 
		if (next.equalsIgnoreCase("2")) {
			System.out.println("Nothing programmed yet.");
		} 
		System.out.println("Thanks! Exiting program!");
	}
}
