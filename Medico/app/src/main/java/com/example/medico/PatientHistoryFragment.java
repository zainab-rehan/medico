package com.example.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PatientHistoryFragment extends Fragment {
    Patient current_patient;
    DAOHistory daoHistory;
    ArrayList<String> history;
    Button add_history;
    EditText new_history_item;
    TextView patient_history;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_history_fragment,container,false);
        Bundle bundle = this.getArguments();
        history = new ArrayList<String>();
        current_patient = (Patient) bundle.getSerializable("patient");

        add_history = (Button) view.findViewById(R.id.add_history);
        new_history_item = (EditText) view.findViewById(R.id.new_history_item);
        patient_history = (TextView) view.findViewById(R.id.history_input);

        //loading already saved history from the database
        daoHistory = new DAOHistory();
        daoHistory.retrieveHistory(current_patient.getId()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot d : task.getResult())
                    {
                        History h = d.toObject(History.class);
                        history.add(h.getHistory());
                    }
                }
            }
        });

        //adding new history to the database

        //saving history item after add history button click (to database)
        //history = current_patient.getHistory();

        add_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_h_item = new_history_item.getText().toString();
                //adding new history item in current patient's history list
                history.add(new_h_item);
                String entire_history_list = "";

                for (String s : history) {
                    entire_history_list += s + "\n";
                }
                //dispalying history in text view
                patient_history.setText(entire_history_list);
                History newHis = new History();
                newHis.setId(current_patient.getId()+"H");
                newHis.setPatId(current_patient.getId());
                newHis.setHistory(entire_history_list);
                daoHistory.addHistory(newHis).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });
        String entire_history_list = "";

        for (String s : history) {
            entire_history_list += s + "\n";
        }

        patient_history.setText(entire_history_list);

        //loading history data from the database
     /*   daoPatient.getHistory(current_patient.getId()).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
        });*/

        //here the code to simply display the history is to be added
        //....
        // here the code to add history on button click is to be added


        return view;
    }
}
