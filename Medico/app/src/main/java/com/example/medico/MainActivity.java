package com.example.medico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText password;
    private DAOSignin dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //services
        if (!foregroundServiceRunning()){
            Intent serviceIntent = new Intent(this, Services.class);
            ContextCompat.startForegroundService(getApplicationContext(),serviceIntent);
        }

        Button signIn = findViewById(R.id.sign_in);
        Button signUpPat = findViewById(R.id.sign_up_pat);
        Button signUpDoc = findViewById(R.id.sign_up_doc);
        userEmail = findViewById(R.id.user_email_input);
        password = findViewById(R.id.password_input);


        //sign up patient
        signUpPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupPatActivity.class);
                startActivity(intent);
            }
        });
        //sign up doctor
        signUpDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupDocActivity.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInClicked();
            }
        });
    }

    public void signInClicked(){
        dao = new DAOSignin();
        dao.authenticate(userEmail.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    //checking if a patient signed in
                    dao.checkPatient().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                            Patient p = null;
                            for(DocumentSnapshot d:task.getResult())
                            {
                                p = d.toObject(Patient.class);
                            }
                            if(p!=null)
                            {
                                Intent i=new Intent(MainActivity.this,PatientActivity.class);
                                i.putExtra("patient",  p);
                                startActivity(i);
                            }
                        }
                    });
                    //checking if a doctor signed in
                    dao.checkDoctor().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                            Doctor doctor = null;
                            for(DocumentSnapshot d:task.getResult())
                            {
                                doctor = d.toObject(Doctor.class);
                            }
                            if(doctor!=null)
                            {
                                Intent i=new Intent(MainActivity.this, DoctorActivity.class);
                                i.putExtra("doctor",  doctor);
                                startActivity(i);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Sign In Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean foregroundServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: activityManager.getRunningServices(Integer.MAX_VALUE)){
            if (Services.class.getName().equals(service.service.getClassName())){
                return true;
            }
        }
        return false;
    }
}