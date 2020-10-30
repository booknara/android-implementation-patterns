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
* HandlerThread
* AsyncTask : All tasks on UI thread (which drives the user interface event loop) are executed in sequential manner, because it makes code more predictable - you are not falling into pitfall of concurrent changes from multiple threads, so if some task is running too long, you'll get ANR (Application Not Responding) warning. AsyncTask is one-shot task, so it cannot be reused by calling execute method on the same instance once again - you should create another instance for a new job.
* Service : Services are executed on the main thread, so they aren't really asynchronous. You have to create a separate thread for them to use if you want them to be asynchronous. With both of these you still have a new thread being created and destroyed.
* IntentService : 

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
