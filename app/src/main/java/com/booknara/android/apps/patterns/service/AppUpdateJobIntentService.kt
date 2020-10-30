package com.booknara.android.apps.patterns.service

import android.content.Intent
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService

/**
 * References
 * https://developer.android.com/reference/androidx/core/app/JobIntentService
 * https://medium.com/@sambhaji2134/jobintentservice-android-example-7f58bd2720bf
 */
class AppUpdateJobIntentService: JobIntentService() {
    private val mHandler: Handler = Handler()

    override fun onHandleWork(intent: Intent) {
        if (intent.action == null) return

        // We have received work to do.  The system or framework is already
        // holding a wake lock for us at this point, so we can just go.
        Log.i(TAG, "App update executing work: $intent")
        var label = intent.getStringExtra("label")
        if (label == null) {
            label = intent.toString()
        }

        toast("Executing: $label")
        for (i in 0..4) {
            Log.i(TAG, "Running service " + (i + 1) + "/5 @ " + SystemClock.elapsedRealtime())
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
            }
        }
        Log.i(TAG, "Completed service @ " + SystemClock.elapsedRealtime())
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("All work complete")
    }

    // Helper for showing tests
    fun toast(text: CharSequence) {
        mHandler.post { Toast.makeText(this@AppUpdateJobIntentService, text, Toast.LENGTH_SHORT).show() }
    }

    companion object {
        const val TAG = "AppUpdateJobIntentService"
        const val APP_UPDATE_JOB_ID = 1000
    }
}