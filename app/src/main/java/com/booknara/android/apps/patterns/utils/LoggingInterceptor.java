package com.booknara.android.apps.patterns.utils;

import android.util.Log;

public class LoggingInterceptor {
    private static final String TAG = "LoggingInterceptor";

    public void intercept(int counter) {
        Log.d(TAG, "counter : " + counter);
    }
}
