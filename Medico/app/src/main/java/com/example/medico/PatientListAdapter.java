package com.example.medico;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PatientListAdapter extends ArrayAdapter<Appointment> {

    ArrayList<Appointment> appointments;
    FirebaseFirestore db;
    Doctor curr_doctor;


    public PatientListAdapter(Context context, ArrayList<Appointment> appointments, Doctor current_doc){
        super(context,0,appointments);
        this.appointments = appointments;
        this.curr_doctor = current_doc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Appointment appointment = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.patient_list_item,parent,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.appt_patient_name);
        name.setText(appointment.getPatName());

        TextView app_date = (TextView) convertView.findViewById(R.id.appt_patient_date);
        app_date.setText(appointment.getDate());

        TextView app_time = (TextView) convertView.findViewById(R.id.appt_patient_time);
        app_time.setText(appointment.getTime());


        String phone_number = appointment.getPatContact();

        ImageView call_doctor = (ImageView) convertView.findViewById(R.id.call_patient);
        call_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+ phone_number));
                    callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(callIntent);
            }
        });

        ImageView msg_doctor = (ImageView) convertView.findViewById(R.id.msg_patient);
        msg_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Uri uri = Uri.parse("sms:" + phone_number);
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body","Contacting Doctor");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(intent);
            }
        });
        ImageView cancel = (ImageView) convertView.findViewById(R.id.cancel_appointment_doctor);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove appointment from doctor from patient and from appointments
                db = FirebaseFirestore.getInstance();
                CollectionReference itemsRef = db.collection("Appointment");
                db.collection("Appointment").whereEqualTo("id",appointment.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot doc: task.getResult()){
                                itemsRef.document(doc.getId()).delete();
                            }
                        }
                        notifyDataSetChanged();
                    }
                });
            }
        });

        return convertView;
    }

}
