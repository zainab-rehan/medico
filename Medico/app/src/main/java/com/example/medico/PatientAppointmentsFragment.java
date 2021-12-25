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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PatientAppointmentsFragment extends Fragment {
    ListView appointmentListView;
    ArrayList<Appointment> appointments;
    ArrayList<Doctor> doctors;
    AppointmentListAdapter adapter;
    Patient current_patient;
    DAOAppointment daoAppointment;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appointments_fragment,container,false);
        Bundle bundle = this.getArguments();
        appointments =  new ArrayList<Appointment>();
        doctors = new ArrayList<Doctor>();
        current_patient = (Patient) bundle.getSerializable("patient");
        appointmentListView = (ListView) view.findViewById(R.id.AppointmentsListView) ;
        adapter = new AppointmentListAdapter(getActivity(), appointments,doctors,current_patient);
        appointmentListView.setAdapter(adapter);

        //loading appointments data from the database of this patient
        daoAppointment = new DAOAppointment();
        daoAppointment.getAppPat(current_patient.getId()).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot ds : list) {
                    Appointment a = ds.toObject(Appointment.class);
                    appointments.add(a);
                }
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
