package cchcc.learn.amu.e02

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import java.util.*

class E02ViewModel : ViewModel() {

    enum class TryResult { FAILED, SUCCESS }

    val score = MutableLiveData<Int>().apply { value = 0 }
    val result = MutableLiveData<TryResult>()

    private val random by lazy { Random() }

    fun tryResult() {
        result.value = if (random.nextBoolean()) TryResult.SUCCESS else TryResult.FAILED
    }



}