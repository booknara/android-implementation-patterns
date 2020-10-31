Android ViewModel and LiveData
===================================
### LiveData
* Purpose
- In Android, activities, fragments and views can be destroyed at almost any time, so any reference to one of these components can cause a leak or a NullPointerException.
- LiveData was designed to implement the observer pattern, allowing communication between the View controller (activities, fragments, etc.) and the source of the UI data (usually a ViewModel). 
- With LiveData, this communication is safer: the data will only be received by the View if it’s active, thanks to its lifecycle awareness.
- The advantage, in short, is that you don’t need to manually cancel subscriptions between View and ViewModel.

* LiveData beyond the ViewModel
- The observable paradigm works really well between the View controller and the ViewModel, so you can use it to observe __other components__ of your app and take advantage of lifecycle awareness.
- Use cass
    - Observe changes in SharedPreferences
    - Observe a document or collection in Firestore
    - Observe the current user with an Authentication SDK like FirebaseAuth
    - Observe a query in Room (which supports LiveData out of the box)
- Advantage: The UI is updated automatically when the data changes because everything is wired together
- Disadvantage: LiveData does not come with a toolkit for __combining streams of data or managing threads__, like Rx does.

* Transformations Patterns
- MediatorLiveData is used for this in combination with the helpers in the Transformations class:
    - __Transformations.map__: One-to-one static transformation 
    - __Transformations.switchMap__: One-to-one dynamic transformation

* One-to-many dependency — MediatorLiveData
- MediatorLiveData lets you add one or multiple sources of data to a single LiveData observable. The below example updates the result when any of the sources change. Note: the data is not combined for you. MediatorLiveData simply takes care of notifications.
 ```kotlin
val liveData1: LiveData<Int> = ...
val liveData2: LiveData<Int> = ...

val result = MediatorLiveData<Int>()

result.addSource(liveData1) { value ->
    result.setValue(value)
}
result.addSource(liveData2) { value ->
    result.setValue(value)
}
```
* Combination two different liveDatas into one
- A way to use MediatorLiveData to combine data is to add the sources and set the value in a different method:
 ```kotlin
fun blogpostBoilerplateExample(newUser: String): LiveData<UserDataResult> {

    val liveData1 = userOnlineDataSource.getOnlineTime(newUser)
    val liveData2 = userCheckinsDataSource.getCheckins(newUser)

    val result = MediatorLiveData<UserDataResult>()

    result.addSource(liveData1) { value ->
        result.value = combineLatestData(liveData1, liveData2)
    }
    result.addSource(liveData2) { value ->
        result.value = combineLatestData(liveData1, liveData2)
    }
    return result
}
```
- The actual combination of data is done in the combineLatestData method.
```kotlin
private fun combineLatestData(
        onlineTimeResult: LiveData<Long>,
        checkinsResult: LiveData<CheckinsResult>
): UserDataResult {

    val onlineTime = onlineTimeResult.value
    val checkins = checkinsResult.value

    // Don't send a success until we have both results
    if (onlineTime == null || checkins == null) {
        return UserDataLoading()
    }

    // TODO: Check for errors and return UserDataError if any.

    return UserDataSuccess(timeOnline = onlineTime, checkins = checkins)
}
```

* When not to use LiveData
- Even if you want to “go reactive” you need to understand the advantages before adding LiveData everywhere. If a component of your app has no connection to the UI, it probably doesn’t need LiveData.
- If part of your app does not affect the UI, you probably don’t need LiveData.


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
