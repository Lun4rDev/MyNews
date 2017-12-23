package com.hernandez.mickael.mynews.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.hernandez.mickael.mynews.R;

public class AlarmReceiver extends BroadcastReceiver {
 
    private static final String DEBUG_TAG = "AlarmReceiver";
 
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification_bg_normal)
                .setContentTitle("My notification")
                .setContentText("Hello World!");

        // Gets instance of NotificationManager service
        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (mNotificationManager != null) {
            mNotificationManager.notify(0, mBuilder.build());
        }
    }
 
}