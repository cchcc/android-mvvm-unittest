package cchcc.learn.amu.e07

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cchcc.learn.amu.e07.coordinator.E07WordCoordinator

class E07WordViewModel(var coordinator: E07WordCoordinator?) : ViewModel() {
    val word = MutableLiveData<String>()

    fun backToWordList() {
        coordinator!!.backToWordListScreen()
    }

    override fun onCleared() {
        coordinator = null  // avoid memory leak
    }
}