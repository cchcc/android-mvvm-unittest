package cchcc.learn.amu.e02

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import java.util.*

class E02ViewModelFactory : ViewModelProvider.Factory {

    private fun randomBooleanGenerator(): () -> Boolean {
        val r = Random()
        return { r.nextBoolean() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        E02ViewModel::class.java -> E02ViewModel(randomBooleanGenerator())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T

}