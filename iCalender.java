import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class iCalender {
	private String version = "VERSION:1.0\n";
	private String prodid = "PRODID:Team Quinze\n";
	private String uid = "UID:blake7@hawaii.edu\n";
	private String startTime = "DTSTART:20160225T16300\n";
	private String endTime = "DTEND:20160225T16450\n";
	private String location = "LOCATION:ICSpace 318B\n";
	private String summary = "SUMMARY:ICS314 TA Meeting\n";
	private String description = "DESCRIPTION:Deliverable #1 presentation to Amy\n";
	private String calBegin = "BEGIN:VCALENDAR\n";
	private String calEnd = "END:VCALENDAR\n";
	private String eventBegin = "BEGIN:VEVENT\n";
	private String eventEnd = "END:VEVENT\n";
	
	public void iCalender(){
		//Create an empty constructor.
	}

	public void write(String name){

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
	
	public static void main(String[] args) {
		//Create an instance of iCalender
		iCalender newCal = new iCalender();
		
		//Write a new .ics file with a name
		newCal.write("New-Event");
	}
}
