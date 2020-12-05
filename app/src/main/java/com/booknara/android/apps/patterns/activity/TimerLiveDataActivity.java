package com.booknara.android.apps.patterns.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.databinding.ActivityTimerBinding;
import com.booknara.android.apps.patterns.viewmodel.LiveDataTimerViewModel;

public class TimerLiveDataActivity extends FragmentActivity {
    private static final String TAG = "TimerLiveDataActivity";
    public static final String TITLE = "Activity Timer using ViewModel";

    private ActivityTimerBinding binding;
    private LiveDataTimerViewModel liveDataTimerViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timer);
        binding.setLifecycleOwner(this);

        liveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);
        binding.setViewModel(liveDataTimerViewModel);
//        subscribeElapsedTimeObserver();
//        getLifecycle().addObserver(new ActivityObserver());
    }

//    private void subscribeElapsedTimeObserver() {
//        liveDataTimerViewModel.elapsedTime.observe(this, value -> {
//            displayTimerValue(String.valueOf(value));
//        });
//    }
//
//    private void displayTimerValue(String value) {
//        binding.timerValueText.setText(value);
//    }
}
