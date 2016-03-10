package ics314;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class iCalender implements calEssentials {
	private String version = "VERSION:1.0\n";
	private String prodid = "PRODID:Team Quinze\n";
	private String uid = "UID:blake7@hawaii.edu\n";
	private String startTime;
	private String endTime;
	private String location;
	private String summary;
	private String description;
	private Float geoValue;
	private String calBegin = "BEGIN:VCALENDAR\n";
	private String calEnd = "END:VCALENDAR\n";
	private String eventBegin = "BEGIN:VEVENT\n";
	private String eventEnd = "END:VEVENT\n";
	
	public void iCalender(){
		//Create an empty constructor.
	}
	
	public void nameFile() {
		//Create an instance of iCalender
		iCalender newCal = new iCalender();
		
		//Make a name for the .ics file
		Scanner reader = new Scanner(System.in);
		System.out.println("What do you want your file to be called?");
		String eName = reader.next();
		
		//Write a new .ics file with an event name
		newCal.writeFile(eName);
	}

	public void writeFile(String name){

		//Take the input name and buld a file from it with append .ics
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
            
            bw.write(eventEnd);
            bw.write(calEnd);
            
            bw.close();
            
            System.out.println("The .ics file has been created.");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void createFile() throws IOException {        
        Scanner reader = new Scanner(System.in);
        String date;
        String time;
        
        System.out.println("What is the event?");
        summary = "SUMMARY:" + reader.nextLine() + "\n";
        
        System.out.println("What is the description of the event?");
        description = "DESCRIPTION:" + reader.nextLine() + "\n";
        
        System.out.println("What is the location of the event?");
        location = "LOCATION:" + reader.nextLine() + "\n";
        
        System.out.println("What is the date of the event? (YYYYMMDD)");
        date = reader.nextLine();
        if (date.length() < 8) {
        	throw new IOException("Must be YYYYMMDD!");
        }
        
        System.out.println("What time does the event start? (24hr)");
        time = reader.nextLine();
        if (time.length() < 4) {
        	throw new IOException("Must be 24hr!");
        }
        startTime = "DTSTART:" + date + "T" + time + "0"+ "\n";

        System.out.println("What time does the event end? (24 hr)");
        time = reader.nextLine();
        if (time.length() < 4) {
        	throw new IOException("Must be 24 hr!");
        }
        endTime = "DTEND:" + date + "T" + time + "0" + "\n";
	}
}
