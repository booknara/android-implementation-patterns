package com.booknara.android.apps.kotlin.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.booknara.android.apps.kotlin.R
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    companion object {
        const val TOTAL_COUNT = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        showRandomNumber()
    }

    fun showRandomNumber() {
        val totalCount = intent.getIntExtra(TOTAL_COUNT, 0)

        var randomInt = 0
        if (totalCount > 0) {
            randomInt = Random.nextInt(totalCount + 1)
        }

        findViewById<TextView>(R.id.textview_random).text = Integer.toString(randomInt)

        findViewById<TextView>(R.id.textview_label).text = getString(R.string.random_heading, totalCount)
    }
}
