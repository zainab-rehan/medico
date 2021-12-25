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

    }

    //getters
    public String getEmail(){return this.email;}
    public String getId(){ return this.id;}
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
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


}
