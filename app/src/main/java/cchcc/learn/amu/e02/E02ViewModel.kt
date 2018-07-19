package cchcc.learn.amu.e02

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class E02ViewModel(private val nextBoolean: () -> Boolean) : ViewModel() {

    enum class TryResult { FAILED, SUCCESS }

    val score = MutableLiveData<Int>().apply { value = 0 }
    val result = MutableLiveData<TryResult>()
    val speedOfAnim = MutableLiveData<Float>().apply { value = 1.0f }

    val clearAction = MutableLiveData<Unit>()

    fun tryResult() {
        result.value = if (nextBoolean()) TryResult.SUCCESS else TryResult.FAILED
    }

    fun applyScore() {
        val amount = when (result.value) {
            E02ViewModel.TryResult.FAILED -> -1
            E02ViewModel.TryResult.SUCCESS -> 1
            else -> throw IllegalStateException("result.value must be one of TryResult. Call tryResult() first.")
        }

        score.value = score.value!! + amount
    }

    fun clear() {
        score.value = 0
        clearAction.value = Unit
    }
}