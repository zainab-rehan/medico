package com.example.medico;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.common.internal.Objects;

import java.util.ArrayList;

public class DoctorsListAdapter extends ArrayAdapter<Doctor> implements Filterable {

    private ArrayList<Doctor> doctors;
    private ArrayList<Doctor> filteredDoctors;
    private Filter filter;

    public DoctorsListAdapter(Context context, ArrayList<Doctor> doctors){
        super(context,0,doctors);
        this.doctors = doctors;
        this.filteredDoctors = doctors;

    }

    @Override
    public Filter getFilter(){
        if(filter == null)
        {
            filter = new DoctorsFilter();
        }
        return filter;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Doctor doctor = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.doctor_list_item,parent,false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.doctor_name);
        name.setText(doctor.getName());

        TextView location = (TextView) convertView.findViewById(R.id.doctor_location);
        location.setText(doctor.getLocation());

        TextView spec = (TextView) convertView.findViewById(R.id.doctor_specialization);
        spec.setText(doctor.getSpecialization());

        TextView availability = (TextView) convertView.findViewById(R.id.doctor_availability);
        availability.setText(doctor.getAvailability());

        String phone_number = doctor.getContact();

        ImageView call_doctor = (ImageView) convertView.findViewById(R.id.call_doctor);
        call_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(availability.getText().equals("Available") == true)
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+ phone_number));
                    callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(callIntent);
                }
                else if(availability.getText().equals("Not Available") == true)
                {
                    Toast toast = new Toast(getContext());
                    toast.setText("Doctor is not available");
                    toast.show();
                }
            }
        });

        ImageView msg_doctor = (ImageView) convertView.findViewById(R.id.msg_doctor);
        msg_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(availability.getText().equals("Available") == true)
                {
                    Uri uri = Uri.parse("sms:" + phone_number);
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body","Contacting Doctor");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(intent);
                }
                else if(availability.getText().equals("Not Available") == true)
                {
                    Toast toast = new Toast(getContext());
                    toast.setText("Doctor is not available!");
                    toast.show();
                }
            }
        });



        return convertView;
    }
    public Doctor getItem(int position){
        return filteredDoctors.get(position);
    }
    public int getCount() {
        return filteredDoctors.size();
    }

    private class DoctorsFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                ArrayList<Doctor> filteredList = new ArrayList<Doctor>();
                for(int i=0; i < doctors.size(); i++){

                    if(doctors.get(i).getSpecialization().contains(constraint) || doctors.get(i).getLocation().contains(constraint)){
                        filteredList.add(doctors.get(i));

                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;

            }
            else{
                results.count = doctors.size();
                results.values = doctors;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredDoctors =  ((ArrayList<Doctor>) results.values);
            notifyDataSetChanged();
        }
    }
}
