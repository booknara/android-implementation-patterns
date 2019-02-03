package com.booknara.android.apps.patterns.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.fragment.SimpleFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE;

public class SimpleFragmentActivity extends AppCompatActivity {
    private static final String TAG = "SimpleFragmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);

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

        Fragment fragment = SimpleFragment.newInstance();
        setDetailContainer(fragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setDetailContainer(Fragment fragment) {
        if (fragment == null) {
            Log.w(TAG, "fragment is null");
            return;
        }

        FragmentManager fragmentManager = getFragmentManager();
        Log.v(TAG, "fragment count : " + fragmentManager.getBackStackEntryCount());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.simple_fragment, fragment, SimpleFragment.TAG);
        ft.commitAllowingStateLoss();
    }

}
