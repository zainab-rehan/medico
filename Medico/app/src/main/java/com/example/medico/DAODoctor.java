package com.example.medico;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DAODoctor {
    private final FirebaseFirestore db;
    private final FirebaseAuth mAuth;

    public DAODoctor()
    {
        db= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> addDoctor(Doctor d)
    {
        return mAuth.createUserWithEmailAndPassword(d.getId(),d.getPassword());
    }

    public Task<Void> addPatientData(Doctor d)
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",d.getName());
        map.put("email",d.getEmail());
        map.put("userId",d.getId());
        map.put("specialization",d.getSpecialization());
        map.put("appointment",d.getApp_list());

        return db.collection("Doctor").document(d.getId()).set(d);
    }
}
