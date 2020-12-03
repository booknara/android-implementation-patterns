package com.booknara.android.apps.patterns.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.patterns.R
import com.booknara.android.apps.patterns.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {
  private lateinit var binding: ActivitySplashBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

    binding.motionlayout.setTransitionListener(object : MotionLayout.TransitionListener {
      override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) { }

      override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) { }

      override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
        Log.i(TAG, "splash logo animation finished")
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
      }

      override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) { }
    })
  }

  override fun onResume() {
    super.onResume()
    binding.motionlayout.startLayoutAnimation()
  }

  companion object {
    const val TAG = "SplashActivity"
  }
}
