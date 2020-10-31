package com.booknara.android.apps.patterns.background

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

class HttpAsyncTask(val context: Context): AsyncTask<String, Void, Int>() {
    private val weakReference = WeakReference(context)

    override fun onPreExecute() {
        super.onPreExecute()
        if (weakReference.get() != null) {
            Toast.makeText(weakReference.get(), "Let's start", Toast.LENGTH_SHORT).show()
        }
    }

    override fun doInBackground(vararg params: String?): Int {
        val connection: HttpURLConnection
        try {
            connection = URL(params[0]).openConnection() as HttpURLConnection
            return connection.responseCode
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return -1
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        Log.i(TAG, "response code : $result")
        if (weakReference.get() != null) {
            if (result != -1) {
                Toast.makeText(weakReference.get(), "Got the following code: $result",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    companion object {
        const val TAG = "HttpAsyncTask"
    }
}