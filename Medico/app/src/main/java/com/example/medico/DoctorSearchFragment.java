package com.example.medico;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class DoctorSearchFragment extends Fragment {
    EditText search_doctor;
    ListView search_doctorlistview;
    DoctorsListAdapter adapter;
    ArrayList<Doctor> doctors;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_search_fragment,container,false);
        search_doctorlistview = (ListView) view.findViewById(R.id.search_doctorlistview);
        search_doctor = (EditText) view.findViewById(R.id.search_doctor);
        doctors = new ArrayList<Doctor>();

       //retrieve doctors' data from database

        search_doctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(search_doctor.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        adapter = new DoctorsListAdapter(getActivity(),doctors);
        search_doctorlistview.setAdapter(adapter);
        return view;
    }

}
