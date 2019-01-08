package com.booknara.android.apps.patterns.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.booknara.android.apps.patterns.BuildConfig;

public class ToastFactory {
    private static final String TAG = "ToastFactory";

    public static void showShort(@NonNull Context context, int resId) {
        try {
            Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.e(TAG, "exception : ", e);
        }
    }

    public static void showShort(@NonNull Context context, String message) {
        if (TextUtils.isEmpty(message)) {
            Log.w(TAG, "message is empty");
            return;
        }

        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.e(TAG, "exception : ", e);
        }
    }

    public static void showLong(@NonNull Context context, int resId) {
        try {
            Toast.makeText(context, context.getString(resId), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.e(TAG, "exception : ", e);
        }
    }

    public static void showLong(@NonNull Context context, String message) {
        if (TextUtils.isEmpty(message)) {
            Log.w(TAG, "message is empty");
            return;
        }

        try {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.e(TAG, "exception : ", e);
        }
    }
}