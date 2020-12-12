package com.booknara.android.apps.patterns.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.patterns.databinding.data.WeatherViewModel
import com.booknara.patterns.databinding.R
import com.booknara.patterns.databinding.databinding.ActivityTwoWayExampleBinding

class TwoWayExampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel: WeatherViewModel by viewModels()
    val binding: ActivityTwoWayExampleBinding =
      DataBindingUtil.setContentView(this, R.layout.activity_two_way_example)

    binding.weather = viewModel
    binding.lifecycleOwner = this
  }
}
