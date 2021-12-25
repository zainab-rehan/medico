package com.example.medico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.wifi.aware.PeerHandle;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class PatientActivity extends AppCompatActivity {
    private AdView mAdView;
    Patient p;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //admob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
       // mAdView.loadAd(adRequest);

        //analytics
       // mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

       /* Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, );
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if(extras != null)
        {
            p = (Patient) i.getSerializableExtra("patient");
        }

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
                        bundle = new Bundle();
                        bundle.putSerializable("patient", p);
                        current_fragment = new DoctorSearchFragment();
                        current_fragment.setArguments(bundle);
                        break;
                    case R.id.patient_appointments:
                        bundle = new Bundle();
                        bundle.putSerializable("patient", p);
                        current_fragment = new PatientAppointmentsFragment();
                        current_fragment.setArguments(bundle);
                        break;
                    case R.id.patient_history:
                        bundle = new Bundle();
                        bundle.putSerializable("patient", p);
                        current_fragment = new PatientHistoryFragment();
                        current_fragment.setArguments(bundle);
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,current_fragment).commit();
                return true;
            }
        });

    }
}