package com.example.medico;

import java.io.Serializable;
import java.util.ArrayList;

public class Doctor implements Serializable {
    private String email;
    private String id;
    private String name;
    private String contact;
    private String location;
    private String password;
    private String specialization;
    private String availability;

    private void init(){}
    public Doctor(){}
    public Doctor(String email, String id, String name, String password, String specialization, String location,String contact){
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.specialization = specialization;
        this.location = location;
        this.contact = contact;
        this.availability = "Available";
    }

    //getters
    public String getEmail(){return this.email;}
    public String getId(){ return this.id;}
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
    public String getSpecialization(){return this.specialization;}
    public String getContact() { return contact; }
    public String getLocation() { return location; }
    public String getAvailability() { return availability; }


    //setters
    public void setEmail(String email){this.email = email;}
    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password;}
    public void setLocation(String location) {
        this.location = location;
    }
    public void setUnavailable() {
        this.availability = "Not Available";
    }
    public void setAvailable() {
        this.availability = "Available";
    }
    public void setSpecialization(String specialization){this.specialization = specialization;}
    public void setContact(String contact) {
        this.contact = contact;
    }


}
