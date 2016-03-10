package ics314;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class iCalender extends calEssentials implements calMethods {
    Scanner reader = new Scanner(System.in);
    String date;

	public void iCalender() {
		//Create an empty constructor.
	}
	
	public String getSummary() {
        System.out.println("What is the event?");
    	System.out.print(">>>");
    	String subSum = reader.nextLine();
        summary = "SUMMARY:" + subSum + "\n";
		return subSum;
	}
	
	public String getDescription() {
		System.out.println("Do you want to add a description? (Y/N)");
    	System.out.print(">>>");
        String subDesc;
		if (reader.nextLine().trim().equalsIgnoreCase("Y")) {
        	System.out.println("What is the description of the event?");
        	System.out.print(">>>");
        	subDesc = reader.nextLine();
            description = "DESCRIPTION:" + subDesc + "\n";
            return subDesc;
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
        	throw new IOException("Must be YYYYMMDD!");
        }
		return date;
	}
	
	public String getTime() throws IOException {
		String time1;
		String time2;
		 //////////////////////USER INPUT: START-TIME///////////////////////
        System.out.println("What time does the event start? (24hr)");
    	System.out.print(">>>");
        time1 = reader.nextLine();
        if (time1.length() < 4) {
        	throw new IOException("Must be 24hr!");
        }
        int sTime = Integer.parseInt(time1);
        startTime = "DTSTART:" + date + "T" + sTime + "0"+ "\n";

        //////////////////////USER INPUT: END-TIME///////////////////////
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
        	choice = reader.nextLine();
        	if (choice.equalsIgnoreCase("Private")) {
        		classification = "CLASS:PRIVATE\n";
        	}
        	else if (choice.equalsIgnoreCase("Confidential")) {
        		classification = "CLASS:CONFIDENTIAL\n";
        	}
        	else {
        		classification = "CLASS:PUBLIC\n";
        	}
        } else {
        	choice = "Public";
        	classification = "CLASS:PUBLIC\n";
        }
		return choice;
	}
	
	
	public void nameFile() {
		//Create an instance of iCalender
		iCalender newCal = new iCalender();
		
		//Make a name for the .ics file
		System.out.println("What do you want your file to be called?");
    	System.out.print(">>>");
		String eName = reader.next();
		
		//Write a new .ics file with an event name
		newCal.writeFile(eName);
	}

	public void writeFile(String name){

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
            bw.write(location);
            bw.write(classification);
            
            bw.write(eventEnd);
            bw.write(calEnd);
            
            bw.close();
            
            System.out.println("The .ics file has been created.");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void createFile() throws IOException {        
        //Scanner reader = new Scanner(System.in);
     
        //////////////////////USER INPUT: EVENT NAME///////////////////////
        getSummary();
        
        //////////////////////USER INPUT: DESCRIPTION///////////////////////
        getDescription();
        
        //////////////////////USER INPUT: LOCATION///////////////////////
        getLocation();
        
        //////////////////////USER INPUT: DATE///////////////////////
        getDate();

        //////////////////////USER INPUT: START/END TIME///////////////////////
		getTime();

        //////////////////////USER INPUT: CLASSIFICATION///////////////////////
        getClassification();
	}

}
