package com.booknara.android.apps.patterns.viewmodel

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class LiveDataTimerViewModel: ViewModel() {
    val elapsedTime = MutableLiveData<String>()
    val initialTime: Long = SystemClock.elapsedRealtime()

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - initialTime) / 1000
                Log.d(TAG, "time : $newValue")
                elapsedTime.postValue(newValue.toString())
            }
        }, ONE_SECOND, ONE_SECOND)
    }

    companion object {
        const val TAG = "LiveDataTimerViewModel"
        const val ONE_SECOND = 1000L
    }
}