package com.booknara.android.apps.patterns.activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.booknara.android.apps.patterns.R
import java.util.concurrent.TimeUnit


class HandlerActivity : AppCompatActivity() {
  private val uiHandler = Handler()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_empty)
    val toolbar = findViewById<Toolbar>(R.id.toolbar_view)
    setSupportActionBar(toolbar)

    Thread {
      for (i in 0..3) {
        try {
          TimeUnit.SECONDS.sleep(2)
        } catch (e: InterruptedException) {
          e.printStackTrace()
        }
        if (i == 2) {
          uiHandler.post(Runnable {
            Log.i(TAG, "running")
            Toast.makeText(this@HandlerActivity,
                "I am at the middle of background task",
                Toast.LENGTH_LONG)
                .show()
          })
        }
      }

      uiHandler.post(Runnable {
        Log.i(TAG, "completed")
        Toast.makeText(this@HandlerActivity,
            "Background task is completed",
            Toast.LENGTH_LONG)
            .show()
      })
    }.start()
  }

  companion object {
    const val TAG = "HandlerActivity"
  }
}
