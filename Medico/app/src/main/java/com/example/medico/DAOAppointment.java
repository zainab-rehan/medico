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
        map.put("docName",a.getDocName());
        map.put("docSpec",a.getDocSpec());
        map.put("docContact",a.getDocContact());
        map.put("patName",a.getPatName());
        map.put("patContact",a.getPatContact());


        return db.collection("Appointment").add(a).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //a.setId( documentReference.getId());
                a.setId(a.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Appointment Add Error", "Error adding appointment", e);
            }
        });
        //return db.collection("Appointment").document(a.getId()).set(a);
    }
    public Task<Void> addAppData(Appointment a)
    {
        return db.collection("Appointment").document(a.getId()).set(a);
    }

    public Task<QuerySnapshot> getAppPat(String id)
    {
        return db.collection("Appointment").whereEqualTo("patId", id).get();
    }
    public Task<QuerySnapshot> getAppDoc(String id)
    {
        return db.collection("Appointment").whereEqualTo("docId", id).get();
    }
    public Task<QuerySnapshot> delApp(String id)
    {
        return db.collection("Appointment").whereEqualTo("id", id).get();
    }



}