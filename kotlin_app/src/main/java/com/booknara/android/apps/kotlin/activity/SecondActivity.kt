package com.booknara.android.apps.kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.kotlin.R
import com.booknara.android.apps.kotlin.databinding.ActivitySecondBinding
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
  private lateinit var binding: ActivitySecondBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_second)

    showRandomNumber()
  }

  private fun showRandomNumber() {
    val totalCount = intent.getIntExtra(TOTAL_COUNT, 0)

    val randomInt = when {
      totalCount > 0 -> Random.nextInt(totalCount + 1)
      else -> 0
    }

    binding.textviewRandom.text = randomInt.toString()
    binding.textviewLabel.text = getString(R.string.random_heading, totalCount)
  }

  companion object {
    const val TOTAL_COUNT = "total_count"
  }
}
