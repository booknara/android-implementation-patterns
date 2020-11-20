package com.booknara.android.apps.patterns.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.databinding.ActivityCounterBinding;
import com.booknara.android.apps.patterns.utils.LoggingInterceptor;
import com.booknara.android.apps.patterns.viewmodel.CounterViewModel;
import com.booknara.android.apps.patterns.viewmodel.factory.CounterViewModelFactory;

public class CounterViewModelActivity extends FragmentActivity {

    private ActivityCounterBinding binding;
    private CounterViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter);
        binding.incrementalButton.setOnClickListener(this::incrementClickCount);

        CounterViewModelFactory counterViewModelFactory = new CounterViewModelFactory(new LoggingInterceptor());
        viewModel = ViewModelProviders.of(this, counterViewModelFactory).get(CounterViewModel.class);
        displayClickCount(viewModel.getCount());
    }

    public void incrementClickCount(View button) {
        int number = Integer.parseInt(binding.numberText.getText().toString());
        displayClickCount(++number);
    }

    private void displayClickCount(int counter) {
        binding.numberText.setText(String.valueOf(counter));
    }
}
