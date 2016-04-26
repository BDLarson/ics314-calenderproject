package ics314;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends eventEssentials{


	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return (this.name);
	}
	
	public void setEventName(String n) {
		this.eName = n;
	}
	
	public String getEventName() {
		return (this.eName);
	}
	
	public void setDescription(String n) {
		this.description = n;
	}
	
	public String getDescription() {
		return (this.description);
	}
	
	public void setDate(String d) {
		this.date = d;
	}
	
	public String getDate() {
		return (this.date);
	}
	
	public void setStartTime(int st1) {
		this.startTime = st1;
	}
	
	public int getStartTime() {
		return (this.startTime);
	}
	
	public void setEndTime(int eT) {
		this.endTime = eT;
	}
	
	public int getEndTime() {
		return (this.endTime);
	}
	
	public void setClassification(String c) {
		this.classification = c;
	}
	
	public String getClassification() {
		return (this.classification);
	}
	
	public void setLat(String lat) {
		this.latitude = lat;
	}
	
	public String getLat() {
		return (this.latitude);
	}
	
	public void setLong(String l) {
		this.longitude = l;
	}
	
	public String getLong() {
		return (this.longitude);
	}
	
	public void setGeo(String lat, String lon) {
		this.geo = lat + ";" + lon;
	}
	
	public String getGeo() {
		return (this.geo);
	}
	
	public void setLocation(String l) {
		this.location = l;
	}
	
	public String getLocation() {
		return (this.location);
	}
	
	public void setComment(double c) {
		this.comment = c;
	}
	
	public Double getComment() {
		return (this.comment);
	}
	
	public String toString() {
		String eString = (getEventName() + " starting at: " + getStartTime() + "\n");
		return eString;
	}

	public void writeFile() {
		//Take the input name and build a file from it with append .ics
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
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
            bw.write("DTSTART:" + date + "T" + startTime + "0"+ "\n");
            bw.write("DTEND:" + date + "T" + endTime + "0" + "\n");
            bw.write("SUMMARY:" + eName + "\n");
            bw.write("DESCRIPTION:" + description + "\n");
            bw.write("CLASS:" + classification + "\n");
           
            if (comment != 0) {
            	bw.write("COMMENT:" + comment + "\n");
            }
            
            if (location != null) {
            	bw.write("LOCATION:" + location + "\n");
        	}
            
            if (latitude != null && longitude != null) {
            	bw.write("GEO:" + geo + "\n");
            }
            
            bw.write(eventEnd);
            bw.write(calEnd);
            
            bw.close();
            
            System.out.println("The .ics file has been created.");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
