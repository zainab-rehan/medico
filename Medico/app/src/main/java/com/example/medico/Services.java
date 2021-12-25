package com.example.medico;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Services extends Service{

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            Log.e("Service","Location Service is running..");
                            try{
                                Thread.sleep(2000);
                            }
                            catch(InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }

        ).start();
        final String CHANNELID = "Foreground Service ID";
        NotificationChannel channel = new NotificationChannel(CHANNELID, CHANNELID, NotificationManager.IMPORTANCE_LOW
        );
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this,CHANNELID).setContentText("Location Services is running").setContentTitle("Service Enabled").setSmallIcon(R.drawable.ic_launcher_background);
        startForeground(1001, notification.build());
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
