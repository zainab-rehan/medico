package com.example.medico;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DAOHistory {
    private final FirebaseFirestore db;

    public DAOHistory()
    {
        db= FirebaseFirestore.getInstance();
    }

    public Task<Void> addHistory(History h)
    {
        return db.collection("History").document(h.getId()).set(h);
    }
    public Task<QuerySnapshot> retrieveHistory(String patId)
    {
        return db.collection("History").whereEqualTo("patId",patId).get();
    }
}
