package com.booknara.android.apps.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.kotlin.R
import com.booknara.android.apps.kotlin.databinding.ActivityEditBinding
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
  private lateinit var binding: ActivityEditBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)

    binding.nameView.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        button.isEnabled = s.isNullOrEmpty() == false
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

      override fun afterTextChanged(s: Editable?) { }
    })
  }

  fun greet(v: View) {
    // apply scope function
    val intent = Intent(this@EditActivity, GreetActivity::class.java).apply {
      putExtra(GreetActivity.KEY_NAME, binding.nameView.text.toString().trim())
    }
    startActivity(intent)
  }
}
