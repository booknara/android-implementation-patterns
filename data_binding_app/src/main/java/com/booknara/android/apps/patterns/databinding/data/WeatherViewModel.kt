package com.booknara.android.apps.patterns.databinding.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {
  // This data is hard-coded but you can imagine it comes from a weather service somewhere
  private val _currentTemp = MutableLiveData(30)
  private val _currentConditions = MutableLiveData("Cloudy")
  private val _currentWindChill = MutableLiveData(2)

  // create public properties that we can bind to
  val currentTemp: MutableLiveData<Int> = _currentTemp
  val currentConditions: LiveData<String> = _currentConditions
  val currentWindChill: LiveData<Int> = _currentWindChill


  // implement methods to change the temperature
  fun onIncreaseTemp() {
    _currentTemp.value = _currentTemp.value?.plus(1)
  }

  fun onDecreaseTemp() {
    _currentTemp.value = _currentTemp.value?.minus(1)
  }
}
