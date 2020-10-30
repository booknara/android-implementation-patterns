package com.booknara.android.apps.background

import android.os.Handler
import android.os.HandlerThread

class WorkerHandlerThread(name: String): HandlerThread(name) {
    lateinit var handler: Handler

    fun postTask(task: Runnable) {
        handler.post(task)
    }

    fun prepareHandler() {
        // You cannot initialize mWorkerHandler at the HandlerThread constructor call,
        // because getLooper will return null since thread is not alive yet.
        handler = Handler(looper)
    }
}