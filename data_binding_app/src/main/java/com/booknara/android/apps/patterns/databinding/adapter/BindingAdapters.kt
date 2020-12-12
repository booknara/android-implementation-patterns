package com.booknara.android.apps.patterns.databinding.adapter

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

// create a Binding Adapter that hides the wind chill setting if it's zero
@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, value: Int) {
  view.visibility = if (value == 0) View.GONE else View.VISIBLE
}

@BindingAdapter("android:textColor")
fun setTempColor(view: TextView, value: Int) {
  when {
    value < 15 -> view.setTextColor(Color.BLUE)
    value in 16..21 -> view.setTextColor(Color.CYAN)
    value > 21 -> view.setTextColor(Color.RED)
  }
}

// TODO: Programming Challenge: set the color of the temp text based on value

