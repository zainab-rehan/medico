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

import java.util.ArrayList;

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

   /*     Doctor doctor = appointment.getDoctor();

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
        });*/

        return convertView;
    }

}
