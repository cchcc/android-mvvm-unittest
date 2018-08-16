package cchcc.learn.amu.e06

import android.arch.lifecycle.ViewModel
import cchcc.learn.amu.e06.data.E06DataRepository
import cchcc.learn.amu.e06.data.E06Number
import cchcc.learn.amu.util.SingleActionLiveData

class E06ViewModel : ViewModel() {
    private val repo = E06DataRepository()
    val numLists = repo.listingThreeTimesTable()

    val moveToTopAction = SingleActionLiveData()

    fun refreshList(doneRefresh: () -> Unit) {
        repo.refresh()
        doneRefresh()
    }

    fun moveToTopOfList() {
        moveToTopAction()
    }

    fun colorizeListItem(num: E06Number?, refreshItem: () -> Unit) {
        if (num != null && num.alpha < 1.0f) {
            num.alpha += 0.1f
            refreshItem()
        }
    }
}