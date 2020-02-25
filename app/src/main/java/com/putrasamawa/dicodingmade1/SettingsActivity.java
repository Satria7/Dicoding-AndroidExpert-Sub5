package com.putrasamawa.dicodingmade1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.putrasamawa.dicodingmade1.notification.DailyReceiver;
import com.putrasamawa.dicodingmade1.notification.MovieReleaseReceiver;
import com.putrasamawa.dicodingmade1.notification.NotificationPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import static com.putrasamawa.dicodingmade1.utils.Utils.KEY_FIELD_DAILY_REMINDER;
import static com.putrasamawa.dicodingmade1.utils.Utils.KEY_FIELD_UPCOMING_REMINDER;
import static com.putrasamawa.dicodingmade1.utils.Utils.KEY_HEADER_DAILY_REMINDER;
import static com.putrasamawa.dicodingmade1.utils.Utils.KEY_HEADER_UPCOMING_REMINDER;
import static com.putrasamawa.dicodingmade1.utils.Utils.TYPE_REMINDER_PREF;
import static com.putrasamawa.dicodingmade1.utils.Utils.TYPE_REMINDER_RECIEVE;

/* Copyright Satria Junanda */

public class SettingsActivity extends AppCompatActivity {

    public DailyReceiver dailyReceiver;
    public MovieReleaseReceiver movieReleaseReceiver;
    public NotificationPreference notificationPreference;
    public SharedPreferences sReleaseReminder, sDailyReminder;
    public SharedPreferences.Editor editorReleaseReminder, editorDailyReminder;
    private TextView lang;
    @BindView(R.id.daily_reminder)
    Switch dailyReminder;
    @BindView(R.id.release_Reminder)
    Switch releaseReminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        lang = (TextView)findViewById(R.id.local_setting);

        movieReleaseReceiver = new MovieReleaseReceiver();
        dailyReceiver = new DailyReceiver();
        notificationPreference = new NotificationPreference(this);
        setPreference();


        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
            }
        });
    }

    private void releaseReminderOn() {
        String time = "08:00";
        String message = getResources().getString(R.string.release_movie_message);
        notificationPreference.setReminderReleaseTime(time);
        notificationPreference.setReminderReleaseMessage(message);
        movieReleaseReceiver.setAlarm(SettingsActivity.this, TYPE_REMINDER_PREF, time, message);
    }

    private void releaseReminderOff() {
        movieReleaseReceiver.cancelAlarm(SettingsActivity.this);
    }

    private void dailyReminderOn() {
        String time = "07:00";
        String message = getResources().getString(R.string.daily_reminder);
        notificationPreference.setReminderDailyTime(time);
        notificationPreference.setReminderDailyMessage(message);
        dailyReceiver.setAlarm(SettingsActivity.this, TYPE_REMINDER_RECIEVE, time, message);
    }

    private void dailyReminderOff() {
        dailyReceiver.cancelAlarm(SettingsActivity.this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @OnCheckedChanged(R.id.daily_reminder)
    public void setDailyReminder(boolean isChecked) {
        editorDailyReminder = sDailyReminder.edit();
        if (isChecked) {
            editorDailyReminder.putBoolean(KEY_FIELD_DAILY_REMINDER, true);
            editorDailyReminder.commit();
            dailyReminderOn();
        } else {
            editorDailyReminder.putBoolean(KEY_FIELD_DAILY_REMINDER, false);
            editorDailyReminder.commit();
            dailyReminderOff();
        }
    }

    @OnClick(R.id.local_setting)
    public void onViewClicked(View view) {
        Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(mIntent);
    }

    @OnCheckedChanged(R.id.release_Reminder)
    public void setReleaseReminder(boolean isChecked) {
        editorReleaseReminder = sReleaseReminder.edit();
        if (isChecked) {
            editorReleaseReminder.putBoolean(KEY_FIELD_UPCOMING_REMINDER, true);
            editorReleaseReminder.commit();
            releaseReminderOn();
        } else {
            editorReleaseReminder.putBoolean(KEY_FIELD_UPCOMING_REMINDER, false);
            editorReleaseReminder.commit();
            releaseReminderOff();
        }
    }
    private void setPreference() {
        sReleaseReminder = getSharedPreferences(KEY_HEADER_UPCOMING_REMINDER, MODE_PRIVATE);
        sDailyReminder = getSharedPreferences(KEY_HEADER_DAILY_REMINDER, MODE_PRIVATE);
        boolean checkUpcomingReminder = sReleaseReminder.getBoolean(KEY_FIELD_UPCOMING_REMINDER, false);
        releaseReminder.setChecked(checkUpcomingReminder);
        boolean checkDailyReminder = sDailyReminder.getBoolean(KEY_FIELD_DAILY_REMINDER, false);
        dailyReminder.setChecked(checkDailyReminder);
    }


    public void exit(View view) {
        finish();
    }
}

/* Copyright Satria Junanda */