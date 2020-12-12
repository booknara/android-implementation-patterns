package com.booknara.android.apps.patterns.databinding.adapter

object Converters {
  @JvmStatic
  fun tempToDescription(temp: Int): String {
    return when {
      temp < 0 -> "Man, it's cold!"
      temp in 0..5 -> "Oooh, it's chilly!"
      temp in 6..10 -> "Cool, but not cold!"
      temp in 11..15 -> "Brisk but pleasant!"
      temp in 16..20 -> "Quite nice!"
      temp in 21..25 -> "A bit warm!"
      temp in 25..35 -> "Something of a scorcher!"
      else -> "You could fry an egg!"
    }
  }
}
