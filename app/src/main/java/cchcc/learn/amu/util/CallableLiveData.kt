package cchcc.learn.amu.util

import android.arch.lifecycle.LiveData
import android.os.Looper

open class CallableLiveData<T> : LiveData<T>() {
    operator fun invoke(v: T) {
        if(Looper.myLooper() == Looper.getMainLooper())
            value = v
        else
            postValue(v)
    }
}

class SingleActionLiveData: CallableLiveData<Unit>() {
    operator fun invoke() {
        if(Looper.myLooper() == Looper.getMainLooper())
            value = Unit
        else
            postValue(Unit)
    }
}