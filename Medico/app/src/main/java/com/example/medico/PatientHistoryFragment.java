package com.example.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PatientHistoryFragment extends Fragment {
    Patient current_patient;
    DAOPatient daoPatient;
    ArrayList<String> history;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_history_fragment,container,false);
        Bundle bundle = this.getArguments();

        current_patient = (Patient) bundle.getSerializable("patient");

        //loading history data from the database
        daoPatient.getHistory(current_patient.getId()).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot ds : list) {
                    Patient p = ds.toObject(Patient.class);
                    assert p != null;
                    history = p.getHistory();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //toast to show  history could not be loaded
            }
        });

        //here the code to simply display the history is to be added
        //....
        // here the code to add history on button click is to be added
        

        return view;
    }
}
