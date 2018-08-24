package cchcc.learn.amu.e07

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import cchcc.learn.amu.e07.coordinator.E07WordCoordinator
import cchcc.learn.amu.e07.coordinator.E07WordListCoordinator
import cchcc.learn.amu.e07.coordinator.E07Navigator

class E07ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        E07WordListViewModel::class.java -> E07WordListViewModel(E07WordListCoordinator())
        E07WordViewModel::class.java -> E07WordViewModel(E07WordCoordinator())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}