import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class iCalender {
	private String version = "VERSION:1.0\r\n";
	private String calBegin = "BEGIN:VCALENDAR\r\n";
	private String calEnd = "END:VCALENDAR\r\n";
	private String eventBegin = "BEGIN:VEVENT\r\n";
	private String eventEnd = "END:VEVENT\r\n";
	
	public void iCalender(){
		//Create an empty constructor.
	}

	public void write(String name){

		//Take the input name and buld a file from it with append .ics
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(".ics");
        
        String newFile = "UID:blake7@hawaii.edu\nDTSTART:20160225T04300\nDTEND:20160225T04450\nSUMMARY:TA MEETING\n";
        
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
            //bw.write(prodid);
            bw.write(eventBegin);
            bw.write(newFile);
            bw.write(eventEnd);
            bw.write(calEnd);
            
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		//Create an instance of iCalender
		iCalender newCal = new iCalender();
		
		//Write a new .ics file with a name
		newCal.write("314-TA-Meeting");
	}
}
