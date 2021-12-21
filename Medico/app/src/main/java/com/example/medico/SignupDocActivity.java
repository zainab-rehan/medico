package com.example.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;

public class SignupDocActivity extends AppCompatActivity {
    private DAODoctor daoDoctor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_doctor);

        EditText email = findViewById(R.id.email_input);
        EditText userId = findViewById(R.id.user_id_input);
        EditText name = findViewById(R.id.name_input);
        EditText password = findViewById(R.id.password_input);
        EditText spec = findViewById(R.id.specialization_input);
        Button signup = findViewById(R.id.sign_up_doc);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Doctor d = new Doctor(email.getText().toString(), userId.getText().toString(), name.getText().toString(),password.getText().toString(), spec.getText().toString());
                daoDoctor = new DAODoctor();
                daoDoctor.addPatientData(d).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignupDocActivity.this,"Signup Complete!",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent();
                            setResult(RESULT_OK,intent);
                            SignupDocActivity.this.onBackPressed();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(SignupDocActivity.this,"Signup Failed!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
