package com.example.medico;

import android.content.BroadcastReceiver;

import android.app.*;
import android.os.*;
import android.content.*;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class ConnectivityReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent serviceIntent = new Intent(context,Services.class);
            context.startForegroundService(serviceIntent);
        }
    }

}
