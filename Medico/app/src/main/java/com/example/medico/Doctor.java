package com.example.medico;

import java.util.ArrayList;

public class Doctor {
    private String id;
    private String name;
    private String password;
    private String specialization;
    private ArrayList<Appointment> app_list;

    private void init(){}
    public Doctor(){}
    public Doctor(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //getters
    public String getId(){ return this.id;}
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
    public String getSpecialization(){return this.specialization;}

    //setters
    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password;}
    public void setSpecialization(String specialization){this.specialization = specialization;}
    public void setApp_list(ArrayList<Appointment> app_list)
    {
        if(this.app_list == null){
            this.app_list = new ArrayList<Appointment>();
        }
        this.app_list = app_list;
    }

}
