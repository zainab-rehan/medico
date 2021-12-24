package com.example.medico;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class DAOPatient {
    private final FirebaseFirestore db;
    private final FirebaseAuth mAuth;

    public DAOPatient()
    {
        db= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> addPatient(Patient p)
    {
        return mAuth.createUserWithEmailAndPassword(p.getEmail(),p.getPassword());
    }


    public Task<Void> addPatientData(Patient p)
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",p.getName());
        map.put("email",p.getEmail());
        map.put("userId",p.getId());
        map.put("history",p.getHistory());
        map.put("appointment",p.getApp_list());

        return db.collection("Patient").document(p.getId()).set(p);
    }
    public Task<Void> addPatientApp(Patient p)
    {
        return db.collection("Patient").document(p.getId()).set(p);
    }
    public Task<QuerySnapshot> getHistory(String id)
    {
        return db.collection("Doctor").whereEqualTo("id", id).get();
    }
}
