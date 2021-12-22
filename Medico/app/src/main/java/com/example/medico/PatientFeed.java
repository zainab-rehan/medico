package com.example.medico;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PatientFeed extends AppCompatActivity {

    ListView doctorListView;
    private DAODoctor daoDoctor;
    ArrayList<Doctor> doctors;
    DoctorsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_feed);
        doctorListView = (ListView) findViewById(R.id.DoctorListView);
        doctors = new ArrayList<Doctor>();

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


        adapter = new DoctorsListAdapter(this,doctors);
        doctorListView.setAdapter(adapter);
        //daoDoctor = new DAODoctor();
    }
}
