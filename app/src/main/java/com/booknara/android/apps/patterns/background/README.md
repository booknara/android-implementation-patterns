Threading With HandlerThread in Android
===================================
### Topics
* Handler
* Message
* Runnable
* Looper
* MessageQueue
* HandlerThread : Thread + Looper + MessageQueue

### Comparison
* HandlerThread : They are useful when you need to implement a Producer/Consumer paradigm(long operations) between different threads.
- HandlerThreads run outside of your activity’s lifecycle, so they need to be cleaned up properly or else you will have thread leaks.
- There is no convenient mechanism for posting results back to the main thread. This means more boilerplate code.

* AsyncTask : All tasks on UI thread (which drives the user interface event loop) are executed in sequential manner, because it makes code more predictable - you are not falling into pitfall of concurrent changes from multiple threads, so if some task is running too long, you'll get ANR (Application Not Responding) warning. AsyncTask is one-shot task, so it cannot be reused by calling execute method on the same instance once again - you should create another instance for a new job.
- AsyncTasks should ideally be used for short operations (a few seconds at the most.) If you need to keep threads running for long periods of time, it is highly recommended you use the various APIs provided by the java.util.concurrent package such as Executor, ThreadPoolExecutor and FutureTask.
- _Note - This class was deprecated in API level 30_ [90]
```kotlin
for (Request req : allPendingRequest) { 
   new BatchTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, req); 
}
```

* Service & IntentService : [100] 

### Credit & References
* [Nikita][200][201]

License
-------
MIT License

Copyright (c) 2020 Daehee Han

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


[90]: https://developer.android.com/reference/android/os/AsyncTask
[100]: https://github.com/booknara/android-implementation-patterns/tree/master/app/src/main/java/com/booknara/android/apps/patterns/service
[200]: https://blog.nikitaog.me/2014/10/11/android-looper-handler-handlerthread-i/
[210]: https://blog.nikitaog.me/2014/10/18/android-looper-handler-handlerthread-ii/