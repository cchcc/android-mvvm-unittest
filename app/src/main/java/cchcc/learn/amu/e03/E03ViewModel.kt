package cchcc.learn.amu.e03

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cchcc.learn.amu.e03.data.E03DataRepository
import cchcc.learn.amu.e03.data.E03Memo
import cchcc.learn.amu.e03.data.E03MemoRepository

class E03ViewModel(private val repository: E03DataRepository<E03Memo> = E03MemoRepository()) : ViewModel() {
    val items: LiveData<List<E03Memo>> = MutableLiveData<List<E03Memo>>().apply {
        value = repository.list
    }

    val newContent: MutableLiveData<String> = MutableLiveData()

    fun removeAt(idx: Int) {
        repository.removeAt(idx)
    }

    fun add() {
        repository.add(E03Memo(newContent.value ?: ""))
        newContent.value = ""
    }
}