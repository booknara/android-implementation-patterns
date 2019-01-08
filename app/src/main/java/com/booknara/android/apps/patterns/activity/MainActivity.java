package com.booknara.android.apps.patterns.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.booknara.android.apps.patterns.R;

import java.util.ArrayList;
import java.util.Arrays;

import static com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_view);
        setSupportActionBar(toolbar);

        ListView activityList = findViewById(R.id.activity_list);

        String[] patterns = new String[] {
                "Simple Activity",
                "Activity & Fragment",
                "Activity & ListView (Header & Footer)",
                "Activity & RecyclerView (multiple view types)",
                "Activity & ViewPager",
                "Activity & FAB",

        };

        ArrayList<String> patternsList = new ArrayList<>();
        patternsList.addAll(Arrays.asList(patterns));

        activityList.setAdapter(new ArrayAdapter<>(this, R.layout.text_item, patternsList));
        activityList.setOnItemClickListener((parent, view, position, id) ->  {
            Log.d(TAG,"Items " +  patterns[position]);
            Intent intent = new Intent();
            intent.putExtra(ACTIVITY_TITLE, patterns[position]);
            switch (position) {
                case 0:
                    // Simple Activity
                    intent.setClass(this, SimpleActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    // Activity & Fragment
                    intent.setClass(this, SimpleFragmentActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    // Activity & ListView (Header & Footer)
                    // Simple Activity
                    intent.setClass(this, ListActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    // Activity & RecyclerView (multiple view types)
                    break;
                case 4:
                    // Activity & ViewPager
                    break;
                case 5:
                    // Activity & FAB
                    break;
                default:
                    break;
            }
        });
    }
}
