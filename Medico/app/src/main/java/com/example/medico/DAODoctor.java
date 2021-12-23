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

    public Task<Void> addDoctorData(Doctor d)
    {

        return db.collection("Doctor").document(d.getId()).set(d);
    }
}
