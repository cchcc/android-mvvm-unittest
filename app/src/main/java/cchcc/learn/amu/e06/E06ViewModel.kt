package cchcc.learn.amu.e06

import androidx.lifecycle.ViewModel
import cchcc.learn.amu.e06.data.E06DataRepository
import cchcc.learn.amu.e06.data.E06Number
import cchcc.learn.amu.util.SingleLiveEvent

class E06ViewModel(private val repo: E06DataRepository) : ViewModel() {
    val numLists by lazy { repo.listingThreeTimesTable() }

    val moveToTopAction = SingleLiveEvent<Unit>()

    fun refreshList(doneRefresh: () -> Unit) {
        repo.refresh()
        doneRefresh()
    }

    fun moveToTopOfList() {
        moveToTopAction.value = Unit
    }

    fun colorizeListItem(num: E06Number?, refreshItem: () -> Unit) {
        if (num != null && num.alpha < 1.0f) {
            num.alpha += 0.1f
            refreshItem()
        }
    }
}