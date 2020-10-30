package com.booknara.android.apps.patterns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.booknara.android.apps.patterns.service.AppUpdateJobIntentService;
import com.booknara.android.apps.patterns.service.AppUpdateService;

import static com.booknara.android.apps.patterns.service.AppUpdateJobIntentService.APP_UPDATE_JOB_ID;

public class PatternsBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "PatternsBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        if (intent == null) {
            Log.w(TAG, "onReceive: intent is null");
            return;
        }

        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            Log.w(TAG, "onReceive: action is empty");
            return;
        }

        Log.d(TAG, "onReceive: action " + action);
        switch (action) {
            case Intent.ACTION_MY_PACKAGE_REPLACED:
                // 1. IntentService
                context.startService(new Intent(context, AppUpdateService.class));

                // 2. JobIntentService
                AppUpdateJobIntentService.enqueueWork(context,
                        AppUpdateJobIntentService.class, APP_UPDATE_JOB_ID,
                        new Intent(context, AppUpdateJobIntentService.class));
                break;
            case Intent.ACTION_BOOT_COMPLETED:
                Log.d(TAG, "onReceive: boot completed");
                break;
            default:
                break;
        }
    }
}
