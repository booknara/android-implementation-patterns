package com.booknara.android.apps.patterns.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.booknara.android.apps.background.HttpAsyncTask
import com.booknara.android.apps.patterns.R

class AsyncTaskActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_view)
        setSupportActionBar(toolbar)

        HttpAsyncTask(this).execute("https://developer.android.com")
    }
}