package com.booknara.android.apps.patterns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.utils.ToastFactory;

import static com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE;

public class SimpleActivity extends AppCompatActivity {
    private static final String TAG = "SimpleActivity";

    //region Lifecycle callback
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //endregion

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //region Menu callback
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shown_menu:
                ToastFactory.showShort(this, "shown menu");
                return true;
            case R.id.overflow_menu_1:
                ToastFactory.showShort(this, "overflow menu 1");
                return true;
            case R.id.overflow_menu_2:
                ToastFactory.showShort(this, "overflow menu 2");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion
}
