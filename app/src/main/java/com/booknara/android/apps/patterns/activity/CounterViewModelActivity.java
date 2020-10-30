package com.booknara.android.apps.patterns.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.utils.LoggingInterceptor;
import com.booknara.android.apps.patterns.viewmodel.CounterViewModel;
import com.booknara.android.apps.patterns.viewmodel.factory.CounterViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CounterViewModelActivity extends FragmentActivity {

    @BindView(R.id.number_text)
    protected TextView numberText;

    private CounterViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        ButterKnife.bind(this);

        CounterViewModelFactory counterViewModelFactory = new CounterViewModelFactory(new LoggingInterceptor());
        viewModel = ViewModelProviders.of(this, counterViewModelFactory).get(CounterViewModel.class);
        displayClickCount(viewModel.getCount());
    }

    @OnClick(R.id.incremental_button)
    public void incrementClickCount(View button) {
        int number = Integer.parseInt(numberText.getText().toString());
        displayClickCount(++number);
    }

    private void displayClickCount(int counter) {
        numberText.setText(String.valueOf(counter));
    }
}
