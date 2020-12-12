package com.booknara.android.apps.patterns.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.patterns.databinding.data.WeatherViewModel
import com.booknara.patterns.databinding.R
import com.booknara.patterns.databinding.databinding.ActivityViewModExampleBinding


class ViewModExampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel: WeatherViewModel by viewModels()
    val binding: ActivityViewModExampleBinding =
      DataBindingUtil.setContentView(this, R.layout.activity_view_mod_example)

    binding.weather = viewModel
    // set the lifecycle owner to the Activity
    binding.lifecycleOwner = this
  }
}
