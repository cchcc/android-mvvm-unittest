package cchcc.learn.amu.e06.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

interface SwipeOnRefresh {
    fun onRefresh(doneRefresh: () -> Unit)
}

@BindingAdapter("onSwipeRefresh")
fun binding_onSwipeRefresh(v: androidx.swiperefreshlayout.widget.SwipeRefreshLayout, listener: SwipeOnRefresh) {

    val doneRefresh: () -> Unit = {
        v.post { v.isRefreshing = false }
    }

    v.setOnRefreshListener { listener.onRefresh(doneRefresh) }
}