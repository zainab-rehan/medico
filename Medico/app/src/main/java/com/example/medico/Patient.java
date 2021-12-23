package com.example.medico;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient implements Serializable {
    private String email;
    private String age;
    private String id;
    private String name;
    private String contact;
    private String password;
    private String gender;
    private ArrayList<String> history;
    private ArrayList<Appointment> app_list;

    private void init(){}
    public Patient(){}
    public Patient(String email,String id, String name, String password, String age, String contact,String gender){
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.contact = contact;
        this.gender = gender;
        this.history = new ArrayList<String>();
        this.app_list = new ArrayList<Appointment>(0);

    }

    //getters
    public String getEmail(){return this.email;}
    public String getId(){ return this.id;}
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
    public ArrayList<String> getHistory(){return this.history;}
    public ArrayList<Appointment> getApp_list(){return this.app_list;}
    public String getAge() {
        return age;
    }
    public String getContact() {
        return contact;
    }
    public String getGender() {
        return gender;
    }


    //setters
    public void setEmail(String email){this.email = email;}
    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password;}
    public void setAge(String age) {
        this.age = age;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

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
    public void addApp(Appointment a)
    {
        if(this.app_list == null){
            this.app_list = new ArrayList<Appointment>();
        }
        this.app_list.add(a);
    }
}
