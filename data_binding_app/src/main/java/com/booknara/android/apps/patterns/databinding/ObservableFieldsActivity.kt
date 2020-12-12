package com.booknara.android.apps.patterns.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.booknara.android.apps.patterns.databinding.data.ObservableWeatherData
import com.booknara.patterns.databinding.R
import com.booknara.patterns.databinding.databinding.ActivityObservableFieldsBinding

class ObservableFieldsActivity : AppCompatActivity() {
  // ObservableWeatherData model
  private val observableWeatherData = ObservableWeatherData(
    ObservableInt(30),
    ObservableField<String>("Cloudy"),
    ObservableInt(5),
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding: ActivityObservableFieldsBinding =
      DataBindingUtil.setContentView(this, R.layout.activity_observable_fields)

    // TODO: bind to the observable model
    binding.weather = observableWeatherData
  }
}
