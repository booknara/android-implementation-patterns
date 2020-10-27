Android Service
===================================
### Service list
* Service
    - This runs on the same main thread which invokes this service and performs some background operation. For any long running operation happening on the main thread it is recommended to create a new thread and do the job (eg; Handler) by not impacting the main thread's performance.
    - Drawback: Runs on the main thread
* IntentService
    - Intent service also helps in doing some long running (indefinite) background task. The only difference is that it creates a new thread to perform this task and does not run on the main thread. Does the given job on it's onHandleIntent.
    - Drawback: The job given to the IntentService would get lost when the application is killed
* JobIntentService
    - Job intent service is very similar to IntentService but with few benefits like the application can kill this job at any time and it can start the job from the beginning once the application gets recreated/up.

### Background Execution Limit from Android Oreo, 8.0 (API 26)
* Background Service Limitations
    - While an app is idle, there are limits to its use of background services. This does not apply to foreground services, which are more noticeable to the user.
* Foreground Service
    - Android 8.0 introduces the new method startForegroundService() to start a new service in the foreground. After the system has created the service, the app has five seconds to call the service's startForeground() method to show the new service's user-visible notification. If the app does not call startForeground() within the time limit, the system stops the service and declares the app to be ANR. 




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
