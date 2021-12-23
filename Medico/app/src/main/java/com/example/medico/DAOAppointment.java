package com.example.medico;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class DAOAppointment {
    private final FirebaseFirestore db;
    private final FirebaseAuth mAuth;

    public DAOAppointment()
    {
        db= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<Void> updateAppointmentData(Appointment a) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", a.getDate());
        map.put("date", a.getDate());
        map.put("time", a.getTime());
        map.put("docId", a.getDocId());
        map.put("patId", a.getPatId());

        return db.collection("Appointment").document(a.getId()).set(map);
    }

    public Task<DocumentReference> addAppointmentData(Appointment a) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", a.getId());
        map.put("date", a.getDate());
        map.put("time", a.getTime());
        map.put("docId", a.getDocId());
        map.put("patId", a.getPatId());

        return db.collection("Appointment").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                a.setId( documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Appointment Add Error", "Error adding appointment", e);
            }
        });
    }

    public Task<QuerySnapshot> getAppPat(String id)
    {
        return db.collection("Appointment").whereEqualTo("id", id).get();
    }

}
