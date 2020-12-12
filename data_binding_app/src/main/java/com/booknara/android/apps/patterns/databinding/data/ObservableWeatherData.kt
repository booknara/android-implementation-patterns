package com.booknara.android.apps.patterns.databinding.data

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

data class ObservableWeatherData(
  val currentTemp: ObservableInt,
  val currentCond: ObservableField<String>,
  val currentWindchill: ObservableInt) {

  // TODO: define some functions to increase or decrease the temperature
  fun onIncreaseTemp() {
    currentTemp.set(currentTemp.get() + 1)
  }
  fun onDecreaseTemp() {
    currentTemp.set(currentTemp.get() - 1)
  }
}
