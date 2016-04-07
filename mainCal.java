package ics314;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class mainCal {
    Scanner reader = new Scanner(System.in);
	ArrayList<iCalender> allEvents = new ArrayList<iCalender>();

	public Boolean addEvent() throws Exception {
		while(true) {
			System.out.println("Do you want to add an event? (Y/N)");
			System.out.print(">>>");
			if (reader.nextLine().equalsIgnoreCase("Y")) {
				iCalender newEvent = new iCalender();
				newEvent.nameFile();
				allEvents.add(newEvent);
			} else {
				return false;
			}
		}
	}
	
	//Print the contents of entire event array
	public void printAllEvents()
	{
		Iterator eventsItr = allEvents.iterator();
		System.out.print("-----------------------------------\n");
		while(eventsItr.hasNext()){
			System.out.print(eventsItr.next().toString());
			System.out.println("-----------------------------------");
		}
	}

	
	public static void main(String[] args) throws Exception {
		//iCalender cal = new iCalender();
		//cal.nameFile();
		
		mainCal cal = new mainCal();
		cal.addEvent(); //Start creating files
		cal.printAllEvents();
	}
}
