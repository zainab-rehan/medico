package com.example.medico;

import java.util.Date;

public class Appointment {
    private String id;
    private String date;
    private String time;
    private String docId;
    private String patId;


    public Appointment(){}
    public Appointment(String id,String date, String time, String docId, String patId)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.docId = docId;
        this.patId = patId;
    }
    public void setAppointment(String id, String date, String time, String docId, String patId)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.docId = docId;
        this.patId = patId;
    }

    public String getId(){return this.id;}

}
