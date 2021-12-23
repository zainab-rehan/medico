package com.example.medico;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class HistoryListAdapter extends ArrayAdapter<History> {
    ArrayList<History> patient_symptoms_history;
    public HistoryListAdapter(Context context, ArrayList<History> history){
        super(context,0,history);
        this.patient_symptoms_history = history;
    }
}
