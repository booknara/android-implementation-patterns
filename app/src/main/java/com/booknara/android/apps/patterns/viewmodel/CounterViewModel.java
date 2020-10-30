package com.booknara.android.apps.patterns.viewmodel;

import androidx.lifecycle.ViewModel;

import com.booknara.android.apps.patterns.utils.LoggingInterceptor;

public class CounterViewModel extends ViewModel {
    private LoggingInterceptor loggingInterceptor;
    private int count;

    public CounterViewModel(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        loggingInterceptor.intercept(count);
    }
}
