package com.booknara.android.apps.kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.kotlin.R
import com.booknara.android.apps.kotlin.databinding.ActivityGreetBinding

class GreetActivity: AppCompatActivity() {
  private lateinit var binding: ActivityGreetBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_greet)

    // Elvis operator
    val name = intent.getStringExtra(KEY_NAME) ?: "World"
    binding.greeting.text = "Hello $name"
  }

  companion object {
    const val KEY_NAME = "name"
  }
}
