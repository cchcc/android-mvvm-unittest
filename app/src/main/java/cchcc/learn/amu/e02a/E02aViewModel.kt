package cchcc.learn.amu.e02a

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class E02aViewModel(private val nextBoolean: () -> Boolean) : ViewModel() {

    enum class TryResult { FAILED, SUCCESS }

    val score = MutableLiveData<Int>().apply { value = 0 }
    val result = MutableLiveData<TryResult>()

    fun tryResult() {
        result.value = if (nextBoolean()) TryResult.SUCCESS else TryResult.FAILED
    }

    fun applyScore() {
        val amount = when (result.value) {
            E02aViewModel.TryResult.FAILED -> -1
            E02aViewModel.TryResult.SUCCESS -> 1
            else -> throw IllegalStateException("result.value must be one of TryResult. Call tryResult() first.")
        }

        score.value = score.value!! + amount
    }

    fun clear() {
        score.value = 0
    }
}