package com.putrasamawa.dicodingmade1.notification;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;


import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.putrasamawa.dicodingmade1.MainActivity;
import com.putrasamawa.dicodingmade1.R;

import java.util.Calendar;

import static com.putrasamawa.dicodingmade1.utils.Utils.EXTRA_MESSAGE_PREF;
import static com.putrasamawa.dicodingmade1.utils.Utils.EXTRA_TYPE_PREF;
import static com.putrasamawa.dicodingmade1.utils.Utils.NOTIFICATION_ID;

/* Copyright Satria Junanda */

public class DailyReceiver extends BroadcastReceiver {
    public DailyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        sendNotification(context, context.getResources().getString(R.string.message_daily), intent.getStringExtra(EXTRA_MESSAGE_PREF), NOTIFICATION_ID);
    }

    private void sendNotification(Context context, String title, String desc, int id) {
        String CHANNEL_ID = "my_channel_02";
        CharSequence name = "my_channel2";
        String Description = "karcis";

        int NOTIFICATION_ID = 235;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(true);

            if (notificationManager != null) {

                notificationManager.createNotificationChannel(mChannel);
            }

        }
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = TaskStackBuilder.create(context)
                .addNextIntent(intent)
                .getPendingIntent(NOTIFICATION_ID, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, desc)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(desc)
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(uriTone);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }

    }

    public void setAlarm(Context context, String type, String time, String message) {
        cancelAlarm(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReceiver.class);
        intent.putExtra(EXTRA_MESSAGE_PREF, message);
        intent.putExtra(EXTRA_TYPE_PREF, type);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0);
        alarmManager.cancel(pendingIntent);

    }
}

/* Copyright Satria Junanda */
