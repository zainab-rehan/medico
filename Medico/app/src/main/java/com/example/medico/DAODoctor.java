package com.example.medico;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
        return mAuth.createUserWithEmailAndPassword(d.getEmail(),d.getPassword());
    }
    public Task<Void> addDoctorData(Doctor d)
    {
        return db.collection("Doctor").document(d.getId()).set(d);
    }
    public Task<DocumentReference> addDoc(Doctor d)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", d.getId());
        map.put("email", d.getEmail());
        map.put("name", d.getName());
        map.put("password", d.getPassword());
        map.put("docContact",d.getContact());
        map.put("location",d.getLocation());
        map.put("specialization", d.getSpecialization());
        map.put("availability",d.getAvailability());


        return db.collection("Doctor").add(d).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //a.setId( documentReference.getId());
                d.setId(d.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Doctor Add Error", "Error adding doctor", e);
            }
        });
    }
}
