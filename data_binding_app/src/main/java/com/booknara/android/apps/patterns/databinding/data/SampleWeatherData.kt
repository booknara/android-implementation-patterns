package com.booknara.android.apps.patterns.databinding.data

data class SampleWeatherData(
  val temp: Int,
  val conditions: String,
  val windchill: Int,
  val units: String) {

  fun convertToF() = (temp * (9 / 5.0)) + 32
}
