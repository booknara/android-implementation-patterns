package com.booknara.android.apps.patterns.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.booknara.android.apps.patterns.utils.LoggingInterceptor;
import com.booknara.android.apps.patterns.viewmodel.CounterViewModel;

public class CounterViewModelFactory implements ViewModelProvider.Factory {
    private LoggingInterceptor loggingInterceptor;

    public CounterViewModelFactory(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CounterViewModel.class)) {
            return (T) new CounterViewModel(loggingInterceptor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
