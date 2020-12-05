package com.booknara.android.apps.patterns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.service.AppUpdateForegroundService;

import static com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE;

public class ForegroundServiceActivity extends AppCompatActivity {
    private static final String TAG = "ForegroundServiceActivity";
    public static final String TITLE = "Foreground Service";

    private Button startButton;
    private Button stopButton;

    //region Lifecycle callback
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_service);

        Intent intent = getIntent();
        String title = "";
        if (intent != null) {
            title = intent.getStringExtra(ACTIVITY_TITLE);
        }

        Toolbar toolbar = findViewById(R.id.toolbar_view);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        startButton = findViewById(R.id.start_service);
        stopButton = findViewById(R.id.stop_service);

        startButton.setOnClickListener(v -> {
            startService();
        });
        stopButton.setOnClickListener(v -> {
            stopService();
        });
    }
    //endregion

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void startService() {
        Intent serviceIntent = new Intent(this, AppUpdateForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, AppUpdateForegroundService.class);
        stopService(serviceIntent);
    }
}
