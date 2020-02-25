package com.putrasamawa.dicodingmade1.notification;

import android.content.Context;
import android.content.SharedPreferences;

import static com.putrasamawa.dicodingmade1.utils.Utils.KEY_REMINDER_DAILY;
import static com.putrasamawa.dicodingmade1.utils.Utils.KEY_REMINDER_MESSAGE_Daily;
import static com.putrasamawa.dicodingmade1.utils.Utils.KEY_REMINDER_MESSAGE_Release;
import static com.putrasamawa.dicodingmade1.utils.Utils.PREF_NAME;

/* Copyright Satria Junanda */

public class NotificationPreference {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public NotificationPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void setReminderReleaseTime(String time){
        editor.putString(KEY_REMINDER_DAILY,time);
        editor.commit();
    }
    public void setReminderReleaseMessage (String message){
        editor.putString(KEY_REMINDER_MESSAGE_Release,message);
    }
    public void setReminderDailyTime(String time){
        editor.putString(KEY_REMINDER_DAILY,time);
        editor.commit();
    }
    public void setReminderDailyMessage(String message){
        editor.putString(KEY_REMINDER_MESSAGE_Daily,message);
    }
}

/* Copyright Satria Junanda */
