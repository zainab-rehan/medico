package com.example.medico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    private EditText userId;
    private EditText password;
    private DAOSignin dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signIn = findViewById(R.id.sign_in);
        Button signUpPat = findViewById(R.id.sign_up_pat);
        Button signUpDoc = findViewById(R.id.sign_up_doc);
        userId=findViewById(R.id.user_id_input);
        password=findViewById(R.id.password_input);

        signUpPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupPatActivity.class);
                startActivity(intent);
            }
        });
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

        dao = new DAOSignin();
    }

    public void signInClicked(){
        Patient p;
        p=new Patient();
        p.setName("zainab");
        p.setAge("21");
        p.setId("P1");
        p.setEmail("zainab@gmail.com");
        p.setGender("Female");
        if(p!=null)
        {
            Intent i=new Intent(MainActivity.this,PatientActivity.class);
            i.putExtra("patient",  p);
            startActivity(i);
        }

        dao.authenticate(userId.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    dao.checkPatient().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            Patient p = null;

                            for(DocumentSnapshot d:task.getResult())
                            {
                                p=d.toObject(Patient.class);
                                p.setId(d.getId());
                            }

                            if(p!=null)
                            {
                                Intent i=new Intent(MainActivity.this,PatientActivity.class);
                                i.putExtra("patient",  p);
                                startActivity(i);
                            }
                        }
                    });

                    dao.checkDoctor().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            Doctor doctor = null;

                            for(DocumentSnapshot d:task.getResult())
                            {
                                doctor = d.toObject(Doctor.class);
                                doctor.setId(d.getId());
                            }

                            if(doctor!=null)
                            {
                                Intent i=new Intent(MainActivity.this, DoctorFeed.class);
                                i.putExtra("doctor", (Parcelable) doctor);
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

}