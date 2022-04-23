package edu.ewubd.myclassnotes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.Date;

public class NoteCLass {
    public String noteId,courseTitle,notesTitle,description,dateAndTime;
    public NoteCLass(String courseTitle, String notesTitle, String description){
        this.noteId= UUID.randomUUID().toString();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("hh:mmaa,dd/MM/yy");
        this.dateAndTime=dateFormat.format(date);
        this.courseTitle=courseTitle;
        this.description=description;
        this.notesTitle=notesTitle;
    }
}
