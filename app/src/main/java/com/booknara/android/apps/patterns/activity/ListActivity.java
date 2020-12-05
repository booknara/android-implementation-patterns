package com.booknara.android.apps.patterns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.adapter.SimpleArrayAdapter;
import com.booknara.android.apps.patterns.listener.OnItemClickListener;
import com.booknara.android.apps.patterns.utils.ToastFactory;

import java.util.ArrayList;
import java.util.Arrays;

import static com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE;

public class ListActivity extends AppCompatActivity implements OnItemClickListener {
    private static final String TAG = "ListActivity";
    public static final String TITLE = "Activity & ListView (Header & Footer)";

    //region Lifecycle callback
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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

        ListView phonetics = findViewById(R.id.phonetic_list);
        String[] patterns = new String[] {
                "Adam",
                "Boy",
                "Charles",
                "David",
                "Ethan",
                "Frank",
                "George",
                "Henry",
                "Ida",
                "John",
                "King",
                "Lincoln",
                "Mary",
                "Nora",
                "Ocean",
                "Paul",
                "Queen",
                "Robert",
                "Sam",
                "Tom",
                "Union",
                "Victor",
                "William",
                "Xray",
                "Yellow",
                "Zebra"
        };

        ArrayList<String> patternsList = new ArrayList<>();
        patternsList.addAll(Arrays.asList(patterns));

        phonetics.addHeaderView(getLayoutInflater().inflate(R.layout.header_item, null));
        phonetics.setAdapter(new SimpleArrayAdapter(this, R.layout.text_item, patternsList, this));
        phonetics.addFooterView(getLayoutInflater().inflate(R.layout.footer_item, null));
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

    @Override
    public void onClick(View view, Object object) {
        ToastFactory.showShort(this, (String)object);
    }
}
