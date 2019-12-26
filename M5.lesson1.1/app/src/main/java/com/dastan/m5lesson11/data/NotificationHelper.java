package com.dastan.m5lesson11.data;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.dastan.m5lesson11.R;
import com.dastan.m5lesson11.ui.main.MainActivity;
import com.dastan.m5lesson11.ui.main.MapActivity;

public class NotificationHelper {
    private static final String CHANNEL_ID = "CHANNEL_ID";

    public static Notification createNotification(Context context, String title, String body) {
        createNotificationChannel(context);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent  = PendingIntent.getActivity(context, 1, intent, 0);

        Intent intent2 = new Intent(context, MapActivity.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 1, intent2, 0);

        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.mipmap.ic_launcher_round, "more", pendingIntent2)
                .build();
    }

    public static void showNotification(Context context, Notification notification){
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(1, notification);
    }

    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
