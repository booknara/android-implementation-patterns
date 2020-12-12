package com.booknara.android.apps.patterns.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.patterns.databinding.data.SampleWeatherData
import com.booknara.patterns.databinding.R
import com.booknara.patterns.databinding.databinding.ActivitySimpleExampleBinding


class SimpleExampleActivity : AppCompatActivity() {
  private lateinit var binding: ActivitySimpleExampleBinding
  val weather = SampleWeatherData(30, "Overcast", 5, "C")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // inflate the layout using the binding library
    binding = DataBindingUtil.setContentView(this, R.layout.activity_simple_example)

    // connect the data with the layout variable
    binding.weather = weather
  }
}
