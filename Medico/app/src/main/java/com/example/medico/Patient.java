package com.example.medico;

import java.util.ArrayList;

public class Patient {
    private String id;
    private String name;
    private String password;
    private ArrayList<String> history;
    private ArrayList<Appointment> app_list;

    private void init(){}
    public Patient(){}
    public Patient(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //getters
    public String getId(){ return this.id;}
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
    public ArrayList<String> getHistory(){return this.history;}

    //setters
    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password;}
    public void setHistory(ArrayList<String> history){
        if(this.history == null){
            this.history = new ArrayList<String>();
        }
        this.history = history;
    }

    //additional functions
    public void addHistory(String history){
        this.history = new ArrayList<>();
        this.history.add(history);
    }
    public void setApp_list(ArrayList<Appointment> app_list)
    {
        if(this.app_list == null){
            this.app_list = new ArrayList<Appointment>();
        }
        this.app_list = app_list;
    }
}
