package com.example.medico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PatientActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Patient p = (Patient) i.getSerializableExtra("patient");

        setContentView(R.layout.activity_patient);
        BottomNavigationView bottom_navigation = findViewById(R.id.bottomnavigationbar);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment current_fragment = null;

                switch(item.getItemId()){
                    case R.id.patient_home:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("patient", p);
                        current_fragment = new PatientFeedFragment(p);
                        current_fragment.setArguments(bundle);
                        break;
                    case R.id.patient_search:
                        current_fragment = new DoctorSearchFragment();
                        break;

                    //case R.id.patient_history:
                      //  current_fragment = new PatientHistoryFragment();
                       // break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,current_fragment).commit();
                return true;
            }
        });
    }
}