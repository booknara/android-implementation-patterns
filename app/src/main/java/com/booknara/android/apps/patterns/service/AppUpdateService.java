package com.booknara.android.apps.patterns.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class AppUpdateService extends IntentService {
    private static final String TAG = "AppUpdateService";

    public AppUpdateService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: any handling for app update");
    }
}
