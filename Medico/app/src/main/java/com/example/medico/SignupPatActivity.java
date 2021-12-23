package com.example.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;

public class SignupPatActivity extends AppCompatActivity {

    private DAOPatient daoPatient;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_patient);

        EditText email = findViewById(R.id.email_input);
        EditText userId = findViewById(R.id.user_id_input);
        EditText name = findViewById(R.id.name_input);
        EditText password = findViewById(R.id.password_input);
        EditText age = findViewById(R.id.age_input);
        EditText contact = findViewById(R.id.contact_input);
        EditText gender = findViewById(R.id.gender_input);
        Button signup = findViewById(R.id.sign_up_doc);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Patient p = new Patient(email.getText().toString(), userId.getText().toString(), name.getText().toString(),
                        password.getText().toString(), age.getText().toString(),contact.getText().toString(), gender.getText().toString());
                    daoPatient = new DAOPatient();
                    daoPatient.addPatientData(p).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(SignupPatActivity.this,"Signup Complete!",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent();
                                setResult(RESULT_OK,intent);
                                SignupPatActivity.this.onBackPressed();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(SignupPatActivity.this,"Signup Failed!",Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });

    }
}
