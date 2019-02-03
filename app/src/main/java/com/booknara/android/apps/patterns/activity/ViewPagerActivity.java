package com.booknara.android.apps.patterns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.fragment.SimpleFragment;

import static com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE;

public class ViewPagerActivity extends AppCompatActivity {
    private static final String TAG = "ViewPagerActivity";
    private static final int NUM_PAGES = 5;

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

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

        mViewPager = findViewById(R.id.view_pager);
        mPagerAdapter = new TextSlidePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }

    }

    private class TextSlidePagerAdapter extends FragmentPagerAdapter {

        public TextSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return SimpleFragment.newInstance(String.valueOf(i));
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
