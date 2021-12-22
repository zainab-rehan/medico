package com.example.medico;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PatientFeed extends AppCompatActivity {

    ListView doctorListView;
    private DAODoctor daoDoctor;
    ArrayList<Doctor> doctors;
    DoctorsListAdapter adapter;
    Patient current_patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting current patient's data from login activity (main activity)
        Intent i = getIntent();
        current_patient = (Patient) i.getSerializableExtra("patient");

        setContentView(R.layout.patient_feed);
        doctorListView = (ListView) findViewById(R.id.DoctorListView);
        doctors = new ArrayList<Doctor>();

        //placing current patient's data in profile
        TextView patient_name = (TextView) findViewById(R.id.patient_profile_name);
        patient_name.setText(current_patient.getName());
        TextView patient_age = (TextView) findViewById(R.id.patient_profile_age);
        patient_age.setText(current_patient.getAge());
        TextView patient_gender = (TextView) findViewById(R.id.patient_profile_gender);
        patient_gender.setText(current_patient.getGender());


        Doctor d = new Doctor();
        d.setName("Zainab");
        d.setId("D3");
        d.setEmail("zainab@gmail.com");
        d.setLocation("Lahore");
        d.setContact("03008927162");
        d.setSpecialization("Child Specialist");
        d.setAvailability("Available");

        doctors.add(d);

        d=new Doctor();
        d.setName("Bayyana");
        d.setId("D4");
        d.setEmail("bayyana@gmail.com");
        d.setLocation("Islamabad");
        d.setContact("03007355512");
        d.setSpecialization("Dermatology");
        d.setAvailability("Available");
        doctors.add(d);

        d=new Doctor();
        d.setName("Amna");
        d.setId("D5");
        d.setEmail("amna@gmail.com");
        d.setLocation("Lahore");
        d.setContact("03009288371");
        d.setSpecialization("Cardiology");
        d.setAvailability("Not Available");
        doctors.add(d);

        d=new Doctor();
        d.setName("Hasan");
        d.setId("D6");
        d.setEmail("hasan@gmail.com");
        d.setLocation("Karachi");
        d.setContact("03002938173");
        d.setSpecialization("Child Specialist");
        d.setAvailability("Not Available");
        doctors.add(d);

        d=new Doctor();
        d.setName("Alina");
        d.setId("D7");
        d.setEmail("alina@gmail.com");
        d.setLocation("Lahore");
        d.setContact("03029163711");
        d.setSpecialization("Child Specialist");
        d.setAvailability("Available");
        doctors.add(d);

        adapter = new DoctorsListAdapter(this,doctors);
        doctorListView.setAdapter(adapter);
        //daoDoctor = new DAODoctor();
    }
}
