package com.booknara.android.apps.patterns.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.booknara.android.apps.patterns.R
import com.booknara.android.apps.patterns.background.WorkerHandlerThread
import com.booknara.android.apps.patterns.databinding.ActivityHandlerThreadBinding
import java.util.*


class HandlerThreadActivity: AppCompatActivity(), WorkerHandlerThread.Callback {
    private lateinit var workerHandlerThread: WorkerHandlerThread
    private lateinit var binding: ActivityHandlerThreadBinding

    private var activityVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_handler_thread)
        val toolbar = binding.toolbarView as Toolbar
        setSupportActionBar(toolbar)

        val urls = arrayOf("https://developer.android.com/design/media/principles_delight.png",
                "https://developer.android.com/design/media/principles_real_objects.png",
                "https://developer.android.com/design/media/principles_make_it_mine.png",
                "https://developer.android.com/design/media/principles_get_to_know_me.png")

        val uiHandler = Handler()
        workerHandlerThread = WorkerHandlerThread(uiHandler, this)

        workerHandlerThread.start()
        workerHandlerThread.prepareHandler()
        val random = Random()
        for (url in urls) {
            workerHandlerThread.queueTask(url, random.nextInt(2), ImageView(this))
        }
    }

    override fun onResume() {
        activityVisible = true
        super.onResume()
    }

    override fun onPause() {
        activityVisible = false
        super.onPause()
    }

    override fun onDestroy() {
        // Attention: to stop started thread in HandlerThread
        workerHandlerThread.quit()
        super.onDestroy()
    }

    @MainThread
    override fun onImageDownloaded(imageView: ImageView, bitmap: Bitmap, side: Int) {
        imageView.setImageBitmap(bitmap)
        if (activityVisible && side == LEFT_SIDE) {
            binding.leftSideLayout.addView(imageView)
        } else if (activityVisible && side == RIGHT_SIDE) {
            binding.rightSideLayout.addView(imageView)
        }
    }

    companion object {
        const val TAG = "HandlerActivity"
        const val LEFT_SIDE = 0
        const val RIGHT_SIDE = 1
    }
}