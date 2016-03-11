package ics314;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class iCalender extends calEssentials implements calMethods {
    Scanner reader = new Scanner(System.in);
    private String date;

	public iCalender() {
		//Create an empty constructor.
	}
	
	public String getSummary() {
        System.out.println("Do you want to add an event name? (Y/N)");
    	System.out.print(">>>");
    	if (reader.nextLine().equalsIgnoreCase("Y")) {
    		System.out.println("What is the event?");
    		System.out.print(">>>");
    		summary = "SUMMARY:" + reader.nextLine().trim() + "\n";
    		return summary;
    	} else {
    		summary = "SUMMARY:none\n";
    		return summary;
    	}
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
	
	public String getLocation() {
        System.out.println("What is the location of the event?");
    	System.out.print(">>>");
    	String subLocation = reader.nextLine();
        location = "LOCATION:" + subLocation + "\n";
		return subLocation;
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
	
	public String getTime() throws IOException {
		String time1;
		String time2;
        System.out.println("What time does the event start? (24hr)");
    	System.out.print(">>>");
        time1 = reader.nextLine();
        if (time1.length() < 4) {
        	throw new IOException("Must be 24hr!");
        }
        int sTime = Integer.parseInt(time1);
        startTime = "DTSTART:" + date + "T" + sTime + "0"+ "\n";

        System.out.println("What time does the event end? (24 hr)");
    	System.out.print(">>>");
        time2 = reader.nextLine();
        if (time2.length() < 4) {
        	throw new IOException("Must be 24 hr!");
        }
        int eTime = Integer.parseInt(time2);
        if(eTime < sTime) {
        	throw new IOException("End time cannot be earlier than start time!");
        }
        endTime = "DTEND:" + date + "T" + eTime + "0" + "\n";
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
			subGeo = newGeo.getLatLongPositions(subLocation);
			subGeo = subGeo.trim();
	        location = "LOCATION:" + subLocation + "\n";
			geo = "GEO:" + subGeo +"\n";
			return subGeo;
		}
		else {
			//TODO add getLocation if answer is NO
			location = null;
			geo = null;	
			return (geo + location);
		}
	}
	
	public void nameFile() throws Exception {
		//Create an instance of iCalender
		iCalender newCal = new iCalender();
		
		//Make a name for the .ics file
		System.out.println("What do you want your file to be called?");
    	System.out.print(">>>");
		String eName = reader.next();
		
		//Write a new .ics file with an event name
		newCal.writeFile(eName);
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
           // bw.write(startTime);
           // bw.write(endTime);
            //bw.write(summary);
           // bw.write(description);
           // bw.write(location);
           bw.write(classification);
            
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
        //getSummary();
        
        //////////////////////USER INPUT: DESCRIPTION///////////////////////
       // getDescription();
        
        //////////////////////USER INPUT: LOCATION///////////////////////
        //getLocation();
        
        //////////////////////USER INPUT: DATE///////////////////////
        //getDate();

        //////////////////////USER INPUT: START/END TIME///////////////////////
		//getTime();

        //////////////////////USER INPUT: CLASSIFICATION///////////////////////
        getClassification();
        
        //////////////////////USER Input: GEO//////////////////////////////////
        //getGeo();
        
	}

}
