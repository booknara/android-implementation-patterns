package com.booknara.android.apps.patterns.databinding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.booknara.patterns.databinding.R

object TestWeatherData {
    const val temp = 38
    const val conditions = "Sunny"
    const val windchill = 6
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: display data the old way using findViewById:


        // set event handlers the old way using findViewById:
        findViewById<Button>(R.id.simplebind).setOnClickListener {
            startActivity(Intent(this, SimpleExampleActivity::class.java))
        }
        findViewById<Button>(R.id.obsfldbind).setOnClickListener {
            startActivity(Intent(this, ObservableFieldsActivity::class.java))
        }
        findViewById<Button>(R.id.viewmodeldbind).setOnClickListener {
            startActivity(Intent(this, ViewModExampleActivity::class.java))
        }
        findViewById<Button>(R.id.btnTwoWayBind).setOnClickListener {
            startActivity(Intent(this, TwoWayExampleActivity::class.java))
        }

    }
}
