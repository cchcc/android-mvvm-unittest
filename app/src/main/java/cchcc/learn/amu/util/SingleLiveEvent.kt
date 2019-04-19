package cchcc.learn.amu.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

// https://github.com/googlesamples/android-architecture/blob/dev-todo-mvvm-live/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/SingleLiveEvent.java
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    override fun postValue(value: T?) {
        pending.set(true)
        super.postValue(value)
    }
}