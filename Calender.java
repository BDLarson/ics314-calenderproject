package ics314;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import ics314.Event;

public class Calender {
	Scanner sc = new Scanner(System.in);
	
	//Create the array to store events
	ArrayList<Event> allEvents = new ArrayList<Event>();
	
	//Set up the comparator class
	eventComparator eventComp = new eventComparator();
		
	public void addEvent() throws Exception {
		boolean done = false;
		while(!done) {
			System.out.println("Do you want to add an event? (Y/N)");
			System.out.print(">>>");
			if (sc.nextLine().equalsIgnoreCase("Y")) {
				
				////////////////////////
				//Create the new event//
				////////////////////////
				Event newEvent = new Event();
				
				////////////////////////
				//Create the geo class//
				////////////////////////
				Geographic newGeo = new Geographic();

				////////////////////////////
				//Set the name of the file//
				////////////////////////////
				System.out.println("What do you want your file to be called?");
		    	System.out.print(">>>");
				newEvent.setName(sc.next());
				
				/////////////////////////////
				//Set the name of the event//
				/////////////////////////////
				System.out.println("Do you want to add an event name? (Y/N)");
		    	System.out.print(">>>");
		    	sc.nextLine();
		    	if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
		    		System.out.println("What is the event?");
		    		System.out.print(">>>");
		    		newEvent.setEventName(sc.nextLine());
		    	} else {
		    		newEvent.setEventName("N/A");
		    	}
		    	
		    	////////////////////////////////////
		    	//Set the description of the event//
		    	////////////////////////////////////
				System.out.println("Do you want to add a description? (Y/N)");
		    	System.out.print(">>>");
		    	if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
		        	System.out.println("What is the description of the event?");
		        	System.out.print(">>>");
		        	newEvent.setDescription(sc.nextLine());
		    	} else {
		    		newEvent.setDescription("N/A");
		    	} 
		    	
		    	boolean verifyD = false;
		    	while(!verifyD){
		    		int valid = 0;
		    		
		    		/////////////////////////////
		    		//Set the date of the event//
		    		/////////////////////////////
		    		System.out.println("What is the date of the event? (YYYYMMDD)");
		    		System.out.print(">>>");
		        	String date = sc.nextLine();
		        	if (date.length() < 8) {
		        		System.out.println("Too little! Must 8 digits and YYYYMMDD!");
		        		valid = 1;
		        	}
		        	if (date.length() > 8) {
		        		System.out.println("Too much! Must be 8 digits and YYYYMMDD!");
		        		valid = 1;
		        	} 
		        	if(valid == 0) {
		        		verifyD = true;
		        		newEvent.setDate(date);
		        	}
		    	}
		        
		        boolean complete = false;
		        while(!complete) {
		        	int valid = 0;
		        	
		        	///////////////////////////////////
		        	//Set the start time of the event//
		        	///////////////////////////////////
		        	System.out.println("What time does the event start? (24hr)");
		        	System.out.print(">>>");
		        	String time1 = sc.nextLine();
		        	if (time1.length() < 4) {
		        		System.out.println("Too little! Must be 4 digits and 24hr!");
		        		valid = 1;
		        	}
		        	if (time1.length() > 4 && valid == 0) {
		        		System.out.println("Too much! Must be 4 digits and 24hr!");
		        		valid = 1;
		        	}
		        	int st1 = Integer.parseInt(time1);
		        	newEvent.setStartTime(st1);
		        
		        	/////////////////////////////////
		        	//Set the end time of the event//
		        	/////////////////////////////////
		        	if (valid == 0) {
		        		System.out.println("What time does the event end? (24 hr)");
		        		System.out.print(">>>");
		        		String time2 = sc.nextLine();
		        		if (time2.length() < 4 && valid == 0) {
		        			System.out.println("Too little! Must be 4 digits and 24hr!");
		        			valid = 1;
		        		}
		        		if (time2.length() > 4 && valid == 0) {
		        			System.out.println("Too Much! Must be 4 digits and 24hr!");
		        			valid = 1;
		        		}
		        		int st2 = Integer.parseInt(time2);
		        		newEvent.setEndTime(st2);
		        		}
		        	
		        	////////////////////////////
		        	//Check overall time frame//
		        	////////////////////////////
		        	if(newEvent.getEndTime() >= newEvent.getStartTime() && valid == 0) {
		        		complete = true;
		        	} else {
		        		System.out.println("End time cannot be earlier than start time!");
		        	}
		        }
		    	
		        ///////////////////////////////////////
		        //Set the classification of the event//
		        ///////////////////////////////////////
				System.out.println("Do you want to specify the classification of the event? (Y/N)");
		    	System.out.print(">>>");
		    	if (sc.nextLine().trim().equalsIgnoreCase("Y")) {
		        	System.out.println("Private, Public, or Confidential?");
		        	System.out.print(">>>");
		        	String choice = sc.nextLine().trim();
		        	if (choice.equalsIgnoreCase("Private")) {
		        		newEvent.setClassification("PRIVATE");
		        	} else if (choice.equalsIgnoreCase("Confidential")) {
		        		newEvent.setClassification("CONFIDENTIAL");
		        	} else {
		        		newEvent.setClassification("PUBLIC");
		        	}
		    	} else {
		    		newEvent.setClassification("PUBLIC");
		    	}
		    	
		    	////////////////////
		    	//Set the location//
		    	////////////////////
				System.out.println("Do you have an address of the event? (Y/N)");
				System.out.print(">>>");
				if (sc.nextLine().equalsIgnoreCase("Y")) {
					System.out.println("What is the address?");
					System.out.print(">>>");
			        String subLocation = sc.nextLine();
					String subGeo = Geographic.getLatLongPositions(subLocation);
					subGeo = subGeo.trim();
					newEvent.setLocation(subLocation);
					newEvent.setLat(Geographic.lat);
					newEvent.setLong(Geographic.lon);
					newEvent.setGeo(newEvent.getLat(), newEvent.getLong());
				} else {
					System.out.println("Do you have the coordinates of the event? (Y/N)");
					System.out.print(">>>");
					if (sc.nextLine().equalsIgnoreCase("Y")) {
						System.out.println("What are the Latitude coordinates?");
						System.out.print(">>>");
						newEvent.setLat(sc.nextLine());
						System.out.println("What are the Longitude coordinates?");
						System.out.print(">>>");
						newEvent.setLong(sc.nextLine());
						newEvent.setGeo(newEvent.getLat(), newEvent.getLong());
					} else {
						System.out.println("Do you know the location name? (Y/N)");
						System.out.print(">>>");
						if (sc.nextLine().equalsIgnoreCase("Y")) {
							System.out.println("What is the name?");
							System.out.print(">>>");
							newEvent.setLocation(sc.nextLine());
						} 
					}
				}
		    	
		    	///////////////////////////////////////
		        //Write all the contents to the file.//
		    	///////////////////////////////////////
		        newEvent.writeFile();
		        
		        ////////////////////////////////////
				//Add event to the array of events//
		        ////////////////////////////////////
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
	public void printAllEvents() {
		Iterator eventsItr = allEvents.iterator();
		System.out.println();
		System.out.print("<<<<<LIST OF EVENTS>>>>>\n");
		while(eventsItr.hasNext()){
			System.out.print(eventsItr.next().toString());
		}
	}
	
	//Get the greater circle distance among each event
	public String getGreatCircleDistance() {
		if (allEvents.size() > 1) {
			for (int i = 0; i < allEvents.size()-1; i++) {
				String eventI = allEvents.get(i).latitude;
				String eventII = allEvents.get(i+1).latitude;
				if(eventI != null || eventII != null) {
					GreatCircle newGC = new GreatCircle(allEvents.get(i).getLat(), allEvents.get(i).getLong(),
							allEvents.get(i+1).getLat(), allEvents.get(i+1).getLong());
					allEvents.get(i).setComment(newGC.getDistance());
					System.out.print(allEvents.get(i).getComment());
					System.out.print(allEvents.get(i+1).getComment() + "\n");
				} else {
					System.out.println("No Great Circle Distance to Calculate.");
				}
			}	
		} else {
			System.out.println("No Great Circle Distance to Calculate.");
		}
		return null;
	}	
	
	public void addPreCreated() throws FileNotFoundException {
		boolean done = false;
		while(!done) {
			System.out.println("Do you want to add a precreated event? (Y/N)");
			System.out.print(">>>");
			if (sc.nextLine().equalsIgnoreCase("Y")) {
				System.out.println("What is the name of the file?");
				System.out.print(">>>");
				List<String> newFile = new ArrayList<String>();
				Scanner readFile = new Scanner(new File(sc.nextLine() + ".ics"));
				while (readFile.hasNext()) {
					newFile.add(readFile.nextLine());
				}
				Event newEvent = new Event();
				for (int i = 0; i<newFile.size(); i++) {
					if (newFile.get(i).startsWith("DTSTART:")) {
						String newDate = (String) newFile.get(i).subSequence(8, 16);
						String newStartTime = (String) newFile.get(i).subSequence(17,21);
						int newStartTime2 = Integer.parseInt(newStartTime);
						newEvent.setDate(newDate);
						newEvent.setStartTime(newStartTime2);
						//System.out.println(newEvent.getDate());
						//System.out.println(newEvent.getStartTime());
					}
					if (newFile.get(i).startsWith("DTEND:")) {
						String newEndTime = (String) newFile.get(i).subSequence(15, 19);
						int newEndTime2 = Integer.parseInt(newEndTime);
						newEvent.setEndTime(newEndTime2);
						//System.out.println(newEvent.getEndTime());
					}
					if (newFile.get(i).startsWith("SUMMARY:")) {
						String newName = (String) newFile.get(i).subSequence(8, newFile.get(i).length());
						newEvent.setEventName(newName);
						//System.out.println(newEvent.getEventName());
					}
					if (newFile.get(i).startsWith("DESCRIPTION:")) {
						String newDescription = (String) newFile.get(i).subSequence(12, newFile.get(i).length());
						newEvent.setDescription(newDescription);
						//System.out.println(newEvent.getDescription());
					}
					if (newFile.get(i).startsWith("CLASS")) {
						String newClass = (String) newFile.get(i).subSequence(6, newFile.get(i).length());
						newEvent.setClassification(newClass);
						//System.out.println(newEvent.getClassification());
					}
					if (newFile.get(i).startsWith("LOCATION")) {
						String newLocation = (String) newFile.get(i).subSequence(9, newFile.get(i).length());
						newEvent.setLocation(newLocation);
						//System.out.println(newEvent.getLocation());
					}
					if (newFile.get(i).startsWith("GEO")) {
						String newSplitter = (String) newFile.get(i).subSequence(4, newFile.get(i).length());
						String[] delimiter = newSplitter.split(";");
						String newLatitude = delimiter[0];
						String newLongitude = delimiter[1];
						newEvent.setLat(newLatitude);
						newEvent.setLong(newLongitude);
						//System.out.println(newEvent.getLat());
						//System.out.println(newEvent.getLong());
					}
					//System.out.println(newFile.get(i));
				}
				allEvents.add(newEvent);
			} else {
				done = true;
			}
		}
	}
}
