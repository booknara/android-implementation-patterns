package com.booknara.android.apps.patterns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.booknara.android.apps.patterns.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    String[] patterns = new String[]{
      SimpleActivity.TITLE,
      SimpleFragmentActivity.TITLE,
      ListActivity.TITLE,
      RecyclerViewActivity.TITLE,
      ViewPagerActivity.TITLE,
      "Activity & Navigation Drawer",
      TimerLiveDataActivity.TITLE,
      ForegroundServiceActivity.TITLE,
      AsyncTaskActivity.TITLE,
      HandlerActivity.TITLE,
      HandlerThreadActivity.TITLE,
      CircularButtonActivity.TITLE
    };

    List<String> patternsList = new ArrayList<>(Arrays.asList(patterns));

    activityList.setAdapter(new ArrayAdapter<>(this, R.layout.text_item, patternsList));
    activityList.setOnItemClickListener((parent, view, position, id) -> {
      Log.d(TAG, "Items " + patterns[position]);
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
          intent.setClass(this, ListActivity.class);
          startActivity(intent);
          break;
        case 3:
          // Activity & RecyclerView (multiple view types)
          intent.setClass(this, RecyclerViewActivity.class);
          startActivity(intent);
          break;
        case 4:
          // Activity & ViewPager
          intent.setClass(this, ViewPagerActivity.class);
          startActivity(intent);
          break;
        case 5:
          // Navigation Drawer
          Toast.makeText(this, "TO DO", Toast.LENGTH_SHORT).show();
          break;
        case 6:
          // Activity & ViewPager
          intent.setClass(this, TimerLiveDataActivity.class);
          startActivity(intent);
          break;
        case 7:
          // Foreground Service
          intent.setClass(this, ForegroundServiceActivity.class);
          startActivity(intent);
          break;
        case 8:
          // AsyncTask
          intent.setClass(this, AsyncTaskActivity.class);
          startActivity(intent);
          break;
        case 9:
          // Handler
          intent.setClass(this, HandlerActivity.class);
          startActivity(intent);
          break;
        case 10:
          // HandlerThread
          intent.setClass(this, HandlerThreadActivity.class);
          startActivity(intent);
          break;
        case 11:
          // Circular Button
          intent.setClass(this, CircularButtonActivity.class);
          startActivity(intent);
          break;
        default:
          break;
      }
    });
  }
}
