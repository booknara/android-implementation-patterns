package com.booknara.android.apps.patterns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.utils.ToastFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE;

public class SimpleActivity extends AppCompatActivity {
    private static final String TAG = "SimpleActivity";

    private FloatingActionButton mainFAB;
    private FloatingActionButton sub1FAB;
    private FloatingActionButton sub2FAB;
    private FloatingActionButton sub3FAB;

    private boolean fabOpened = false;
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

        mainFAB = findViewById(R.id.main_fab);
        sub1FAB = findViewById(R.id.sub_fab1);
        sub2FAB = findViewById(R.id.sub_fab2);
        sub3FAB = findViewById(R.id.sub_fab3);

        mainFAB.setOnClickListener(v -> {
            if (fabOpened) {
                closeFABMenu();
            } else {
                showFABMenu();
            }

//            Snackbar.make(v, "Here is a Snackbar", Snackbar.LENGTH_SHORT)
//                    .setAction("Action", null).show();
        });
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

    private void showFABMenu() {
        fabOpened = true;
        sub1FAB.animate().translationY(-getResources().getDimension(R.dimen.fab_sub_1_height));
        sub2FAB.animate().translationY(-getResources().getDimension(R.dimen.fab_sub_2_height));
        sub3FAB.animate().translationY(-getResources().getDimension(R.dimen.fab_sub_3_height));
    }

    private void closeFABMenu() {
        fabOpened = false;
        sub1FAB.animate().translationY(0);
        sub2FAB.animate().translationY(0);
        sub3FAB.animate().translationY(0);
    }
}
