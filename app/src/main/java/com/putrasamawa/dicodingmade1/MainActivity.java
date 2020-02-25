package com.putrasamawa.dicodingmade1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.putrasamawa.dicodingmade1.model.Item;
import com.putrasamawa.dicodingmade1.notification.DailyReceiver;
import com.putrasamawa.dicodingmade1.notification.MovieReleaseReceiver;
import com.putrasamawa.dicodingmade1.notification.NotificationPreference;

import java.util.ArrayList;

import static com.putrasamawa.dicodingmade1.utils.Utils.TYPE_REMINDER_PREF;
import static com.putrasamawa.dicodingmade1.utils.Utils.TYPE_REMINDER_RECIEVE;

/* Copyright Satria Junanda */

public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView settings;

    public DailyReceiver dailyReceiver;
    public MovieReleaseReceiver movieReleaseReceiver;
    public NotificationPreference notificationPreference;
    public SharedPreferences sReleaseReminder, sDailyReminder;
    public SharedPreferences.Editor editorReleaseReminder, editorDailyReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        settings=(ImageView)findViewById(R.id.pengaturan);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent p = new Intent(MainActivity.this, SettingsActivity.class);
               startActivity(p);
            }
        });

        movieReleaseReceiver = new MovieReleaseReceiver();
        dailyReceiver = new DailyReceiver();
        notificationPreference = new NotificationPreference(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_movie, R.id.navigation_tv,R.id.navigation_fav)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    private void releaseReminderOn() {
        String time = "08:00";
        String message = getResources().getString(R.string.release_movie_message);
        notificationPreference.setReminderReleaseTime(time);
        notificationPreference.setReminderReleaseMessage(message);
        movieReleaseReceiver.setAlarm(MainActivity.this, TYPE_REMINDER_PREF, time, message);
    }

    private void releaseReminderOff() {
        movieReleaseReceiver.cancelAlarm(MainActivity.this);
    }

    private void dailyReminderOn() {
        String time = "07:00";
        String message = getResources().getString(R.string.daily_reminder);
        notificationPreference.setReminderDailyTime(time);
        notificationPreference.setReminderDailyMessage(message);
        dailyReceiver.setAlarm(MainActivity.this, TYPE_REMINDER_RECIEVE, time, message);
    }

    private void dailyReminderOff() {
        dailyReceiver.cancelAlarm(MainActivity.this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}

/* Copyright Satria Junanda */