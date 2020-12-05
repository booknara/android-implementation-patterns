package com.booknara.android.apps.patterns.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.booknara.android.apps.patterns.Constants.IntentKey.ACTIVITY_TITLE
import com.booknara.android.apps.patterns.R
import com.booknara.android.apps.patterns.databinding.ActivitySimpleFragmentBinding
import com.booknara.android.apps.patterns.fragment.SimpleFragment

class SimpleFragmentActivity: AppCompatActivity() {

  lateinit var binding: ActivitySimpleFragmentBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil
      .setContentView(this, R.layout.activity_simple_fragment)

    val title = intent?.getStringExtra(ACTIVITY_TITLE) ?: ""

    binding.toolbarView.title = title
    setSupportActionBar(binding.toolbarView)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)

    setDetailContainer(SimpleFragment.newInstance())
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  private fun setDetailContainer(fragment: Fragment) {
//        val ft = supportFragmentManager.beginTransaction()
//        ft.add(R.id.simple_fragment, fragment, SimpleFragment.TAG)
//        ft.commitAllowingStateLoss()

    supportFragmentManager.commit(allowStateLoss = true) {
      add(R.id.simple_fragment, fragment, SimpleFragment.TAG)
    }
  }

  companion object {
    const val TAG = "SimpleFragmentActivity"
    const val TITLE = "Activity & Fragment"
  }
}
