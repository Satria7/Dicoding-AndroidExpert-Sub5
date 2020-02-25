package com.putrasamawa.dicodingmade1.notification;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.putrasamawa.dicodingmade1.DetailActivity;
import com.putrasamawa.dicodingmade1.MainActivity;
import com.putrasamawa.dicodingmade1.R;
import com.putrasamawa.dicodingmade1.model.Item;
import com.putrasamawa.dicodingmade1.model.ItemMovie;
import com.putrasamawa.dicodingmade1.model.WrapperMovie;
import com.putrasamawa.dicodingmade1.service.RestApiService;
import com.putrasamawa.dicodingmade1.service.RetrofitInstance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.putrasamawa.dicodingmade1.utils.Utils.EXTRA_MESSAGE_RECIEVE;
import static com.putrasamawa.dicodingmade1.utils.Utils.EXTRA_TYPE_RECIEVE;
import static com.putrasamawa.dicodingmade1.utils.Utils.NOTIFICATION_ID_;

/* Copyright Satria Junanda */

public class MovieReleaseReceiver extends BroadcastReceiver {
    public List<ItemMovie> listMovie = new ArrayList<>();
    String title, des, img, mid;
    int notifId;
    public MovieReleaseReceiver() {

    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        Date localDate = Calendar.getInstance().getTime();
        String str = new SimpleDateFormat("yyyy-MM-dd").format(localDate);
        RestApiService movieInterface = RetrofitInstance.getApiService();
        Call<WrapperMovie> call = movieInterface.getUpcomingMovie("cf72efb6e9eb91453d9aabf8a9d16ae8",str,str);
        call.enqueue(new Callback<WrapperMovie>() {
            @Override
            public void onResponse(Call<WrapperMovie> call, Response<WrapperMovie> response) {
                listMovie = response.body().getResults();
                List<ItemMovie> items = response.body().getResults();

                for (int i = 0; i < items.size(); i++) {
                    Log.e("Fill", "yot : " + items.get(i).getOriginal_title());
                    notifId = i;
                    Log.e("Fill", "yot2 : " + i);
                    title = items.get(i).getOriginal_title();
                    des = items.get(i).getOverview();
                    mid = items.get(i).getId();
                    img = items.get(i).getPoster_path();
//                    sendNotification(context, title, des, i);
                    sendNotification(context, title, des, notifId);
                }
            }

            @Override
            public void onFailure(Call<WrapperMovie> call, Throwable t) {
                Log.d("getUpCommingMovie", "onFailure: " + t.toString());
            }
        });
    }

    private void sendNotification(Context context, String titles, String desc, int id) {
        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "karcis";

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

        Item model = new Item(title, des, img, mid, "movie");

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("Model", model);
        intent.putExtra("key", "movie");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri uriTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titles)
                .setContentText(desc)
                .setStyle(new NotificationCompat.InboxStyle())
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setAutoCancel(true)
                .setSound(uriTone);

        if (notificationManager != null) {

            notificationManager.notify(notifId, builder.build());
        }
    }

    public void setAlarm(Context context, String type, String time, String message) {
        cancelAlarm(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MovieReleaseReceiver.class);
        intent.putExtra(EXTRA_MESSAGE_RECIEVE, message);
        intent.putExtra(EXTRA_TYPE_RECIEVE, type);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID_, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

        Toast.makeText(context, R.string.on_movie_release_reminder, Toast.LENGTH_SHORT).show();
    }


    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MovieReleaseReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID_, intent, 0);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
        Toast.makeText(context, R.string.off_movie_release_reminder, Toast.LENGTH_SHORT).show();
    }

}

/* Copyright Satria Junanda */