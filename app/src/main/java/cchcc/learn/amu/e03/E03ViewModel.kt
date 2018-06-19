package cchcc.learn.amu.e03

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cchcc.learn.amu.e03.data.E03DataRepository
import cchcc.learn.amu.e03.data.E03Memo
import cchcc.learn.amu.e03.data.E03MemoRepository

class E03ViewModel(private val repository: E03DataRepository<E03Memo> = E03MemoRepository()) : ViewModel() {
    sealed class ListAction {
        class Added : ListAction()
        class Removed(val memo: E03Memo, val idx: Int) : ListAction()
    }

    val memos: LiveData<List<E03Memo>> = MutableLiveData<List<E03Memo>>().apply {
        value = repository.list
    }

    val listAction: MutableLiveData<ListAction> = MutableLiveData()
    val newContent: MutableLiveData<String> = MutableLiveData()
    val sizeOfMemos: Int = memos.value!!.size

    fun remove(memo: E03Memo, idx: Int) {
        repository.removeAt(idx)
        listAction.value = ListAction.Removed(memo, idx)
    }

    fun add() {
        repository.add(E03Memo(newContent.value ?: ""))
        newContent.value = ""
        listAction.value = ListAction.Added()
    }
}