package com.example.medico;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AppointmentListAdapter extends ArrayAdapter<Appointment> {
    private ArrayList<Appointment> appointments;

    public AppointmentListAdapter(Context context, ArrayList<Appointment> appointments){
        super(context,0,appointments);
        this.appointments = appointments;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        Appointment appointment = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.appointment_list_item,parent,false);
        }
        String docId = appointment.getDocId();
       // Doctor doctor = getDoctor(docId);

        Doctor doctor = new Doctor();
        doctor.setId("d3");
        doctor.setSpecialization("Skin Specialist");
        doctor.setContact("03009876543");
        doctor.setName("ali");
        doctor.setLocation("Multan");

        TextView name = (TextView) convertView.findViewById(R.id.appt_doctor_name);
        name.setText(doctor.getName());

        TextView specialization = (TextView) convertView.findViewById(R.id.appt_doctor_specialization);
        specialization.setText(doctor.getSpecialization());

        TextView appt_date = (TextView) convertView.findViewById(R.id.appt_date);
        appt_date.setText(appointment.getDate());

        TextView appt_time = (TextView) convertView.findViewById(R.id.appt_time);
        appt_time.setText(appointment.getTime());

        String phone_number = doctor.getContact();

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

        return convertView;
    }

    public Doctor getDoctor(String id)
    {
        final Doctor[] d = new Doctor[1];
        DAODoctor daoDoctor = new DAODoctor();
        daoDoctor.getDoctor(id).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot ds : list) {
                    d[0] = ds.toObject(Doctor.class);
                }

            }
        });
        return d[0];
    }

}
