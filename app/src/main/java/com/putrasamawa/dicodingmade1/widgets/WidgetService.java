package com.putrasamawa.dicodingmade1.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/* Copyright Satria Junanda */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewFactory(this.getApplicationContext(), intent);
    }
}

/* Copyright Satria Junanda */