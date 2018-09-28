package cchcc.learn.amu.e07

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cchcc.learn.amu.e07.coordinator.E07WordCoordinator

class E07WordViewModel(val coordinator: E07WordCoordinator) : ViewModel() {
    val word = MutableLiveData<String>()

    fun backToWordList() {
        coordinator.backToWordListScreen()
    }

    override fun onCleared() {
        coordinator.navigator = null  // avoid memory leak
    }
}