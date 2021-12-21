package com.example.medico;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class signInDOA {
    private DatabaseReference databaseReference;
    public void MedicoDAO(){
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Doctor.class.getSimpleName());
    }
    public Task<Void> add(Doctor doctor)
    {
        return databaseReference.child(doctor.getId()).setValue(doctor);
    }
    public Task<Void> add(Patient patient)
    {
        return databaseReference.child(patient.getId()).setValue(patient);
    }
    public Task<Void> add(Appointment appointment)
    {
        return databaseReference.child(appointment.getId()).setValue(appointment);
    }

    public Task<Void> update(String key, HashMap<String ,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }
    public Query get()
    {
        return databaseReference.orderByKey();
    }
}
