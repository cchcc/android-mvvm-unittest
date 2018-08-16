package cchcc.learn.amu.e06.binding

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.graphics.drawable.ColorDrawable
import android.support.v4.widget.SwipeRefreshLayout

interface SwipeOnRefresh {
    fun onRefresh(doneRefresh: () -> Unit)
}

@BindingAdapter("onSwipeRefresh")
fun binding_onSwipeRefresh(v: SwipeRefreshLayout, listener: SwipeOnRefresh) {

    val doneRefresh: () -> Unit = {
        v.post { v.isRefreshing = false }
    }

    v.setOnRefreshListener { listener.onRefresh(doneRefresh) }
}