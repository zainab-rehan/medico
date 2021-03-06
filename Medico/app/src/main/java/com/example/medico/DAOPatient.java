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

        return db.collection("Patient").document(p.getId()).set(p);
    }
}
