package com.example.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PatientHistoryFragment extends Fragment {
    Patient current_patient;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_history_fragment,container,false);
        Bundle bundle = this.getArguments();

        current_patient = (Patient) bundle.getSerializable("patient");

        //loading history data from the database

        return view;
    }
}
