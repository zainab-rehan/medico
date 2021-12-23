package com.example.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class PatientFeedFragment extends Fragment {

    ListView doctorListView;
    ArrayList<Doctor> doctors;
    DoctorsListAdapter adapter;
    Patient current_patient;
    FirebaseFirestore db;

    public PatientFeedFragment(Patient p)
    {
        current_patient = p;
    }

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_feed_fragment, container, false);
        doctorListView = (ListView) view.findViewById(R.id.DoctorListView);
        doctors = new ArrayList<Doctor>();
        Bundle bundle = this.getArguments();

        current_patient = (Patient) bundle.getSerializable("patient");

        //placing current patient's data in profile
        TextView patient_name = (TextView) view.findViewById(R.id.patient_profile_name);
        patient_name.setText(current_patient.getName());
        TextView patient_age = (TextView) view.findViewById(R.id.patient_profile_age);
        patient_age.setText(current_patient.getAge());
        TextView patient_gender = (TextView) view.findViewById(R.id.patient_profile_gender);
        patient_gender.setText(current_patient.getGender());


        adapter = new DoctorsListAdapter(getActivity(), doctors);
        doctorListView.setAdapter(adapter);

        //loading doctor data from the database
        db = FirebaseFirestore.getInstance();
        db.collection("Doctor").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot ds : list) {
                    Doctor d = ds.toObject(Doctor.class);
                    doctors.add(d);
                }
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}

