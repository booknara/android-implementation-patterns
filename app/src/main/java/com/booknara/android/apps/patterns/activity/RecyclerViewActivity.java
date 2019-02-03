package com.booknara.android.apps.patterns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.adapter.SimpleRecyclerViewAdapter;
import com.booknara.android.apps.patterns.listener.OnItemClickListener;
import com.booknara.android.apps.patterns.model.HeaderModel;
import com.booknara.android.apps.patterns.model.ModelType;
import com.booknara.android.apps.patterns.model.PhoneticModel;
import com.booknara.android.apps.patterns.utils.ToastFactory;
import com.booknara.android.apps.patterns.widget.RecyclerViewItemDecoration;

import java.util.ArrayList;

import static com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE;

public class RecyclerViewActivity extends AppCompatActivity implements OnItemClickListener {
    private static final String TAG = "RecyclerViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

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

        RecyclerView phoneticsView = findViewById(R.id.phonetic_recycler_view);
        phoneticsView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        phoneticsView.setLayoutManager(linearLayoutManager);

        ArrayList<ModelType> patternsList = new ArrayList<>();
        patternsList.add(new HeaderModel("Header"));
        patternsList.add(new PhoneticModel("Adam"));
        patternsList.add(new PhoneticModel("Boy"));
        patternsList.add(new PhoneticModel("Charles"));
        patternsList.add(new PhoneticModel("David"));
        patternsList.add(new PhoneticModel("Ethan"));
        patternsList.add(new PhoneticModel("Frank"));
        patternsList.add(new PhoneticModel("George"));
        patternsList.add(new PhoneticModel("Henry"));
        patternsList.add(new PhoneticModel("Ida"));
        patternsList.add(new PhoneticModel("John"));
        patternsList.add(new PhoneticModel("King"));
        patternsList.add(new PhoneticModel("Lincoln"));
        patternsList.add(new PhoneticModel("Mary"));
        patternsList.add(new PhoneticModel("Nora"));
        patternsList.add(new PhoneticModel("Ocean"));
        patternsList.add(new PhoneticModel("Paul"));
        patternsList.add(new PhoneticModel("Queen"));
        patternsList.add(new PhoneticModel("Robert"));
        patternsList.add(new PhoneticModel("Sam"));
        patternsList.add(new PhoneticModel("Tom"));
        patternsList.add(new PhoneticModel("Union"));
        patternsList.add(new PhoneticModel("Victor"));
        patternsList.add(new PhoneticModel("William"));
        patternsList.add(new PhoneticModel("Xray"));
        patternsList.add(new PhoneticModel("Yellow"));
        patternsList.add(new PhoneticModel("Zebra"));

        RecyclerViewItemDecoration recyclerViewItemDecoration
                = new RecyclerViewItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerViewItemDecoration.setHideFirstItemDivider(true);
        recyclerViewItemDecoration.setHideLastItemDivider(true);
        phoneticsView.addItemDecoration(recyclerViewItemDecoration);
        phoneticsView.setAdapter(new SimpleRecyclerViewAdapter(patternsList, this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view, Object object) {
        ToastFactory.showShort(this, object.toString());
    }
}
