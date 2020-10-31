package com.booknara.android.apps.patterns.background

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.widget.ImageView
import androidx.annotation.WorkerThread
import com.booknara.android.apps.patterns.activity.HandlerThreadActivity
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit

class WorkerHandlerThread(
        private val uiHandler: Handler,
        private val callback: Callback
): HandlerThread(TAG) {
    private val requestMap = mutableMapOf<ImageView, String>()
    lateinit var workerHandler: Handler

    fun queueTask(url: String, side: Int, imageView: ImageView) {
        requestMap[imageView] = url
        Log.i(TAG, "$url added to the queue")
        val message = workerHandler.obtainMessage(side, imageView)
        message.sendToTarget()
    }

    fun prepareHandler() {
        // You cannot initialize mWorkerHandler at the HandlerThread constructor call,
        // because getLooper will return null since thread is not alive yet.
        workerHandler = Handler(looper) { msg ->
            try {
                TimeUnit.SECONDS.sleep(2)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val imageView = msg.obj as ImageView
            val side = if (msg.what == HandlerThreadActivity.LEFT_SIDE) "left side" else "right side"
            Log.i(TAG, java.lang.String.format("Processing %s, %s", requestMap.get(imageView), side))
            handleRequest(imageView, msg.what)
            try {
                msg.recycle()
            } catch (e: IllegalStateException) {
                workerHandler.removeMessages(msg.what)
            }

            true
        }
    }

    @WorkerThread
    private fun handleRequest(imageView: ImageView, side:Int) {
        val url = requestMap[imageView]
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            val bitmap = BitmapFactory.decodeStream(connection.content as InputStream)
            requestMap.remove(imageView)
            uiHandler.post { callback.onImageDownloaded(imageView, bitmap, side) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    interface Callback {
        fun onImageDownloaded(imageView: ImageView, bitmap: Bitmap, side: Int)
    }

    companion object {
        const val TAG = "WorkerHandlerThread"
    }
}