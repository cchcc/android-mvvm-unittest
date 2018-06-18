package cchcc.learn.amu.e03.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

@BindingAdapter("layoutManager")
fun bind_rcv_layoutManager(v: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    v.layoutManager = layoutManager
}


@BindingAdapter("adapter")
fun <VH : RecyclerView.ViewHolder> bind_rcv_adapter(v: RecyclerView, adapter: RecyclerView.Adapter<VH>) {
    v.adapter = adapter
}