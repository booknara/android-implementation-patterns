package com.booknara.android.apps.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.kotlin.R
import com.booknara.android.apps.kotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
      binding.toastButton.setOnClickListener { toastMe(it) }
      binding.countButton.setOnClickListener { countMe(it) }
      binding.randomButton.setOnClickListener { randomMe(it) }
      binding.editButton.setOnClickListener { editMe(it) }
    }

    fun toastMe(view: View) {
      Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT).show()
    }

    fun countMe(view: View) {
      val countString = binding.textView.text.toString()
      var count: Int = Integer.parseInt(countString)
      count++
      textView.text = count.toString()
    }

    fun randomMe(view: View) {
      val countString = textView.text.toString()
      val count = Integer.parseInt(countString)

      val intent = Intent(this, SecondActivity::class.java)
      intent.putExtra(SecondActivity.TOTAL_COUNT, count)
      startActivity(intent)
    }

    fun editMe(view: View) {
      val intent = Intent(this@MainActivity, EditActivity::class.java)
      startActivity(intent)
    }
}
