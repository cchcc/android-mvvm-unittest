package cchcc.learn.amu.e02a

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cchcc.learn.amu.e02a.di.E02aViewModelModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class E02aViewModel : ViewModel(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(E02aViewModelModule)
    }

    private val nextBoolean: () -> Boolean by instance()

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