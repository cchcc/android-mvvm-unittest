package cchcc.learn.amu.e06

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cchcc.learn.amu.e06.data.E06DataRepository

class E06ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        E06ViewModel::class.java -> E06ViewModel(E06DataRepository())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}