package com.example.medico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    ListView patientListView;
    ArrayList<Appointment> appointments;
    Doctor current_doctor;
    PatientListAdapter adapter;
    RadioButton doctor_available; //for doctor's availaibity
    RadioButton doctor_unavailable; //for doctor's unavailaibity
    FirebaseFirestore db;
    DAOAppointment daoAppointment;
    RadioGroup availability;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting current doctor's data
        Intent i = getIntent();
        current_doctor = (Doctor) i.getSerializableExtra("doctor");

        setContentView(R.layout.activity_doctor);

        //placing current doctor's data in profile
        TextView doc_name = (TextView) findViewById(R.id.doctor_profile_name);
        doc_name.setText(current_doctor.getName());
        TextView doc_spec = (TextView) findViewById(R.id.doctor_profile_spec);
        doc_spec.setText(current_doctor.getSpecialization());
        availability = (RadioGroup) findViewById(R.id.doctor_availability);
        doctor_available =(RadioButton) findViewById(R.id.available_doctor);
        doctor_unavailable = (RadioButton) findViewById(R.id.unavailable_doctor);
        if(current_doctor.getAvailability().equals("Available"))
            doctor_available.setChecked(true);
        else if(current_doctor.getAvailability().equals("Not Available"))
            doctor_unavailable.setChecked(true);

        //initializing
        appointments =  new ArrayList<Appointment>();
        patientListView = (ListView) findViewById(R.id.PatientListView);

        //calling the adapter with the appointment list
        adapter = new PatientListAdapter(this, appointments, current_doctor);
        patientListView.setAdapter(adapter);

        //getting the appointments of the current doctor
        daoAppointment = new DAOAppointment();
        daoAppointment.getAppDoc(current_doctor.getId()).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot ds : list) {
                    Appointment a = ds.toObject(Appointment.class);
                    appointments.add(a);
                }
                //notifying GUI
                adapter.notifyDataSetChanged();
            }
        });

        //availability issue------------------------------------
//        availability.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId)
//            {
//                switch(checkedId){
//                    case R.id.available_doctor:
//                        current_doctor.setAvailable();
//                        //update availabiilty status in database
//                        break;
//                    case R.id.unavailable_doctor:
//                        current_doctor.setUnavailable();
//                        //update availabiilty status in database
//                        break;
//                }
//            }
//        });

    }



}