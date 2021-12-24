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

public class AppointmentListAdapter extends ArrayAdapter<Appointment> {
    private ArrayList<Appointment> appointments;
    private ArrayList<Doctor> doctors;
    private Doctor mainDoc = new Doctor();
    FirebaseFirestore db;
    Patient current_patient;

    public AppointmentListAdapter(Context context, ArrayList<Appointment> appointments,ArrayList<Doctor> doctors,Patient current_patient){
        super(context,0,appointments);
        this.appointments = appointments;
        this.doctors = doctors;
        this.current_patient = current_patient;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        Appointment appointment = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.appointment_list_item,parent,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.appt_doctor_name);
        name.setText(appointment.getDocName());

        TextView specialization = (TextView) convertView.findViewById(R.id.appt_doctor_specialization);
        specialization.setText(appointment.getDocSpec());

        TextView appt_date = (TextView) convertView.findViewById(R.id.appt_date);
        appt_date.setText(appointment.getDate());

        TextView appt_time = (TextView) convertView.findViewById(R.id.appt_time);
        appt_time.setText(appointment.getTime());

        String phone_number = appointment.getDocContact();

        ImageView call_doctor = (ImageView) convertView.findViewById(R.id.call_doctor);
        call_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+ phone_number));
                    callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(callIntent);
            }
        });

        ImageView msg_doctor = (ImageView) convertView.findViewById(R.id.msg_doctor);
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
        ImageView cancel = (ImageView) convertView.findViewById(R.id.cancel_appointment_patient);
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

    public void removeApp(String appId)
    {


    }

}
