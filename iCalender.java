package ics314;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import ics314.Geographic;
import ics314.GreatCircle;

public class iCalender extends calEssentials implements calMethods {
    Scanner reader = new Scanner(System.in);
    private String date;
    public int setTime;
    public int setETime;
    public String setName;
    public String lat;
    public String lon;
    
	public iCalender() {
	}
	
	public String setSummary() {
        System.out.println("Do you want to add an event name? (Y/N)");
    	System.out.print(">>>");
    	reader.nextLine();
    	if (reader.nextLine().trim().equalsIgnoreCase("Y")) {
    		System.out.println("What is the event?");
    		System.out.print(">>>");
    		String newName = reader.nextLine().trim();
    		summary = "SUMMARY:" + newName + "\n";
    		setName = newName;
    		return summary;
    	} else {
    		summary = "SUMMARY:none\n";
    		setName = "Unknown event";
    		return summary;
    	}
	}
	
	public String getSummary() {
		return setName;
	}
	
	public String getDescription() {
		System.out.println("Do you want to add a description? (Y/N)");
    	System.out.print(">>>");
		if (reader.nextLine().trim().equalsIgnoreCase("Y")) {
        	System.out.println("What is the description of the event?");
        	System.out.print(">>>");
            description = "DESCRIPTION:" + reader.nextLine().trim() + "\n";
            return description;
        } else {
        	description = "DESCRIPTION:none\n";
    		return "none";
        }
	}
	
	public String getDate() throws IOException {
		
        System.out.println("What is the date of the event? (YYYYMMDD)");
    	System.out.print(">>>");
        date = reader.nextLine();
        if (date.length() < 8) {
        	System.out.println("Too much! Must 8 digits and YYYYMMDD!");
        	getDate();
        }
        if (date.length() > 8) {
        	System.out.println("Too little! Must be 8 digits and YYYYMMDD!");
        	getDate();
        }
		return date;
	}
	
	public int setStartTime() {
        System.out.println("What time does the event start? (24hr)");
    	System.out.print(">>>");
        String time1 = reader.nextLine();
        if (time1.length() < 4) {
        	System.out.println("Too little! Must be 4 digits and 24hr!");
        	getStartTime();
        }
        if (time1.length() > 4) {
        	System.out.println("Too much! Must be 4 digits and 24hr!");
        	getStartTime();
        }
        int sTime = Integer.parseInt(time1);
        startTime = "DTSTART:" + date + "T" + sTime + "0"+ "\n";
        setTime = sTime;
        System.out.println();
		return sTime;
	}
	
	public int getStartTime() {
		return setTime;
	}
	
	
	public int setEndTime() {
        System.out.println("What time does the event end? (24 hr)");
    	System.out.print(">>>");
    	
        String time2 = reader.nextLine();
        if (time2.length() < 4) {
        	System.out.println("Too little! Must be 4 digits and 24hr!");
        	getEndTime();
        }
        if (time2.length() > 4) {
        	System.out.println("Too Much! Must be 4 digits and 24hr!");
        	getEndTime();
        }
        int eTime = Integer.parseInt(time2);
        endTime = "DTEND:" + date + "T" + eTime + "0" + "\n";
		return eTime;
	}
	
	public int getEndTime() {
		return setETime;
	}
	
	
	public String getTime() throws IOException {
		int sTime = setStartTime();
		int eTime = setEndTime();
        if(eTime < sTime) {
        	System.out.println("End time cannot be earlier than start time!");
        	getTime();
        }
        return (sTime + "-" + eTime);
	}
	
	public String getClassification() {
		String choice;
		System.out.println("Do you want to specify the classification of the event? (Y/N)");
    	System.out.print(">>>");
        if (reader.nextLine().trim().equalsIgnoreCase("Y")) {
        	System.out.println("Private, Public, or Confidential?");
        	System.out.print(">>>");
        	choice = reader.nextLine().trim();
        	if (choice.equalsIgnoreCase("Private")) {
        		classification = "CLASS:PRIVATE\n";
        	} else if (choice.equalsIgnoreCase("Confidential")) {
        		classification = "CLASS:CONFIDENTIAL\n";
        	} else {
        		classification = "CLASS:PUBLIC\n";
        	}
        } else {
        	classification = "CLASS:PUBLIC\n";
        }
		return classification;
	}
	
	public String getGeo() throws Exception {
		Geographic newGeo = new Geographic();
		String subGeo;
		System.out.println("Do you have an address of the event? (Y/N)");
		System.out.print(">>>");
		if (reader.nextLine().equalsIgnoreCase("Y")) {
			System.out.println("What is the address?");
			System.out.print(">>>");
	        String subLocation = reader.nextLine();
			subGeo = Geographic.getLatLongPositions(subLocation);
			subGeo = subGeo.trim();
	        location = "LOCATION:" + subLocation + "\n";
			geo = "GEO:" + subGeo +"\n";
			lat = Geographic.lat;
			lon = Geographic.lon;
			return geo;
		} else {
			System.out.println("Do you have the coordinates of the event? (Y/N)");
			System.out.print(">>>");
			if (reader.nextLine().equalsIgnoreCase("Y")) {
				System.out.println("What are the coordinates?");
				System.out.print(">>>");
				location = null;
				geo = "GEO:" + reader.nextLine().trim() + "\n";
				
				return geo;
			} else {
				System.out.println("Do you know the location name? (Y/N)");
				System.out.print(">>>");
				if (reader.nextLine().equalsIgnoreCase("Y")) {
					System.out.println("What is the name?");
					System.out.print(">>>");
					location = "LOCATION:" + reader.nextLine().trim() + "\n";
					return location;
				} else {
					location = null;
					geo = null;	
					return (geo + ", " + location);
				}
			}
		}
	}
	
	public double getLat() {
		double latitude = Double.parseDouble(lat);
		return latitude;
	}
	
	public double getLong() {
		double longitude = Double.parseDouble(lon);
		return longitude;
	}
	
	
	public String toString() {
		String eventEssentials = (getSummary() + " starting at: " + getStartTime() + "\n");
		return eventEssentials;
	}
	

	public void setComment(double distance) {
		this.comment = ("COMMENT: " + distance + "\n");
	}
	
	public String getComment() {
		return comment;
	}
	
	public void nameFile() throws Exception {		
		//Make a name for the .ics file
		System.out.println("What do you want your file to be called?");
    	System.out.print(">>>");
		String eName = reader.next();
		
		//Write a new .ics file with an event name
		writeFile(eName);
	}
	
	public void writeFile(String name) throws Exception{

		//Take the input name and build a file from it with append .ics
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(".ics");
                
        try {

            File file = new File(builder.toString());

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            
            createFile();
          
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(calBegin);
            bw.write(version);
            bw.write(prodid);
            bw.write(eventBegin);
            bw.write(uid);
            bw.write(startTime);
            bw.write(endTime);
            bw.write(summary);
            bw.write(description);
            bw.write(classification);
           
            if (comment != null) {
            	bw.write(comment);
            }
            
            if (location != null) {
            	bw.write(location);
        	}
            
            if (geo != null) {
            	bw.write(geo);
            }
            
            bw.write(eventEnd);
            bw.write(calEnd);
            
            bw.close();
            
            System.out.println("The .ics file has been created.");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void createFile() throws Exception {        
     
        //////////////////////USER INPUT: EVENT NAME///////////////////////
        setSummary();
        
        //////////////////////USER INPUT: DESCRIPTION///////////////////////
        getDescription();
        
        //////////////////////USER INPUT: DATE///////////////////////
        getDate();

        //////////////////////USER INPUT: START/END TIME///////////////////////
		getTime();

        //////////////////////USER INPUT: CLASSIFICATION///////////////////////
        getClassification();
        
        //////////////////////USER Input: GEO//////////////////////////////////
        getGeo();
        
	}

}
