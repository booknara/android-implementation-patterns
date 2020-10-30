package com.booknara.android.apps.patterns.viewmodel;

import android.os.SystemClock;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class LiveDataTimerViewModel extends ViewModel {
    private static final String TAG = "LiveDataTimerViewModel";
    private static final int ONE_SECOND = 1000;

    public MutableLiveData<Long> elapsedTime = new MutableLiveData<>();

    private long initialTime;

    public LiveDataTimerViewModel() {
        initialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long newValue = (SystemClock.elapsedRealtime() - initialTime) / 1000;
                elapsedTime.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);
    }
}
