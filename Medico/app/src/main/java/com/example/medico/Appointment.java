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

    //getters
    public String getDate() {
        return date;
    }

    public String getDocId() {
        return docId;
    }

    public String getPatId() {
        return patId;
    }

    public String getTime() {
        return time;
    }

    public String getId(){return this.id;}

    //getter function for getting a doctor's details thru the docID (database)

    //setters

    public void setDate(String date) {
        this.date = date;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //setter function for setting a doctor's details thru the docID (database)
}
