package com.example.medico;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class DAOSignin {
    private final FirebaseFirestore db;
    private final FirebaseAuth mAuth;

    public DAOSignin()
    {
        db= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> authenticate(String userId, String password)
    {
        return mAuth.signInWithEmailAndPassword(userId,password);
    }

    public String getCurrentUser()
    {
        return mAuth.getCurrentUser().getEmail();
    }

    public Task<QuerySnapshot> checkPatient()
    {
        return db.collection("Patient").whereEqualTo("userId",getCurrentUser()).get();
    }

    public Task<QuerySnapshot> checkDoctor()
    {
        return db.collection("Doctor").whereEqualTo("userId",getCurrentUser()).get();
    }
}