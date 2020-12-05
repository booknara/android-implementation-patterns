package com.booknara.android.kotlincoroutines.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.booknara.android.kotlincoroutines.R
import com.booknara.android.kotlincoroutines.network.getNetworkService
import com.google.android.material.snackbar.Snackbar

/**
 * Show layout.activity_main and setup data binding.
 */
class MainActivity : AppCompatActivity() {

  /**
   * Inflate layout.activity_main and setup data binding.
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    val rootLayout: ConstraintLayout = findViewById(R.id.rootLayout)
    val title: TextView = findViewById(R.id.title)
    val taps: TextView = findViewById(R.id.taps)
    val spinner: ProgressBar = findViewById(R.id.spinner)

    // Get MainViewModel by passing a database to the factory
    val database = getDatabase(this)
    val repository = TitleRepository(getNetworkService(), database.titleDao)
    val viewModel = ViewModelProviders
      .of(this, MainViewModel.FACTORY(repository))
      .get(MainViewModel::class.java)

    // When rootLayout is clicked call onMainViewClicked in ViewModel
    rootLayout.setOnClickListener {
      viewModel.onMainViewClicked()
    }

    // update the title when the [MainViewModel.title] changes
    viewModel.title.observe(this) { value ->
      value?.let {
        title.text = it
      }
    }

    viewModel.taps.observe(this) { value ->
      Log.d(TAG, "taps value: $value")
      taps.text = value
    }

    // show the spinner when [MainViewModel.spinner] is true
    viewModel.spinner.observe(this) { value ->
      value.let { show ->
        spinner.visibility = if (show) View.VISIBLE else View.GONE
      }
    }

    // Show a snackbar whenever the [ViewModel.snackbar] is updated a non-null value
    viewModel.snackbar.observe(this) { text ->
      text?.let {
        Snackbar.make(rootLayout, text, Snackbar.LENGTH_SHORT).show()
        viewModel.onSnackbarShown()
      }
    }
  }

  companion object {
    const val TAG = "MainActivity"
  }
}
