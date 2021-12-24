package com.example.medico;

import java.util.Date;

public class Appointment {
    private String id;
    private String date;
    private String time;
    private String docId;
    private String patId;
    private String docName;
    private String docSpec;
    private String docContact;

    public Appointment(){}
    public Appointment(String id, String date, String time, String docId, String patId,String docName,String docSpec, String docContact)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.docId = docId;
        this.patId = patId;
        this.docName = docName;
        this.docSpec = docSpec;
        this.docContact = docContact;
    }
    public void setAppointment(String id, String date, String time, String docId, String patId,String docName,String docSpec, String docContact)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.docId = docId;
        this.patId = patId;
        this.docName = docName;
        this.docSpec = docSpec;
        this.docContact = docContact;
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocSpec() {
        return docSpec;
    }

    public void setDocSpec(String docSpec) {
        this.docSpec = docSpec;
    }

    public String getDocContact() {
        return docContact;
    }

    public void setDocContact(String docContact) {
        this.docContact = docContact;
    }

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
