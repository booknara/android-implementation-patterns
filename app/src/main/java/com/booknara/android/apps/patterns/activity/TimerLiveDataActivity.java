package com.booknara.android.apps.patterns.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.viewmodel.LiveDataTimerViewModel;
import com.booknara.android.apps.patterns.viewmodel.lifecycleobserver.ActivityObserver;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimerLiveDataActivity extends FragmentActivity {
    private static final String TAG = "TimerLiveDataActivity";

    @BindView(R.id.timer_value_text)
    protected TextView timerText;

    private LiveDataTimerViewModel liveDataTimerViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        ButterKnife.bind(this);

        liveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);
        subscribeElapsedTimeObserver();
        getLifecycle().addObserver(new ActivityObserver());
    }

    private void subscribeElapsedTimeObserver() {
        liveDataTimerViewModel.elapsedTime.observe(this, value -> {
            displayTimerValue(String.valueOf(value));
        });
    }

    private void displayTimerValue(String value) {
        timerText.setText(value);
    }
}
