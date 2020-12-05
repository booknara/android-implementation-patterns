package com.booknara.android.apps.patterns.activity

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.patterns.R
import com.booknara.android.apps.patterns.databinding.ActivityCircularButtonBinding

class CircularButtonActivity: AppCompatActivity() {
  private lateinit var binding: ActivityCircularButtonBinding

  private val buttonList = mutableListOf<Button>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_circular_button)
    createButtons()

    Handler().postDelayed({
      repeatList()
    }, 2000)
  }

  private fun createButtons() {
    val count = 7
    val angle = 360 / (count + 1)
    for (i in 0..count) {
      val button = Button(this)
      button.id = View.generateViewId()
      button.background = ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)
      val layout = ConstraintLayout.LayoutParams(
        50.toPx(),
        50.toPx()
      )
      layout.circleRadius = 120.toPx()
      layout.circleConstraint = R.id.main_button
      layout.circleAngle = (i * angle).toFloat()
      layout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
      layout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
      layout.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
      layout.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
      button.layoutParams = layout
      buttonList.add(button)
      binding.mainLayout.addView(button)
    }
  }

  private fun repeatList() {
    Thread {
      var buttonListIndex = -1
      val list = getAllColors()
      list.forEachIndexed { index: Int, color: Int ->
        Thread.sleep(100)
        if (buttonListIndex == buttonList.size - 1) {
          buttonListIndex = -1
        }
        buttonListIndex++
        runOnUiThread {
          val dr = ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)
          dr?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY)
          buttonList[buttonListIndex].background = dr
        }

        if (index == list.size - 1) {
          repeatList()
        }
      }
    }.start()
  }

  private fun getAllColors(): List<Int> {
    val field = Class.forName("$packageName.R\$color").declaredFields
    val list = mutableListOf<Int>()
    field.forEach {
      list.add(ContextCompat.getColor(this, it.getInt(null)))
    }
    return list
  }

  private fun Int.toPx(): Int =
    TypedValue.applyDimension(
      TypedValue.COMPLEX_UNIT_DIP,
      this.toFloat(), resources.displayMetrics
    ).toInt()

  companion object {
    const val TITLE = "Circular Button"
  }
}
