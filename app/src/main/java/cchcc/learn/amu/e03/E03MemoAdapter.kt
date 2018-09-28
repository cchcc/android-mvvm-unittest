package cchcc.learn.amu.e03

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ListitemE03Binding
import cchcc.learn.amu.e03.data.E03Memo

typealias OnListItemEvent = (E03Memo, Int) -> Unit

class E03MemoAdapter(private val items: List<E03Memo>, val onClickRemove: OnListItemEvent) : androidx.recyclerview.widget.RecyclerView.Adapter<E03MemoAdapter.VH>() {

    inner class VH(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val content = MutableLiveData<String>()
        
        fun onClickRemove(): Unit = this@E03MemoAdapter.onClickRemove(items[layoutPosition], layoutPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val lifecycleOwner = parent.context as LifecycleOwner
        val binding = DataBindingUtil.inflate<ListitemE03Binding>(LayoutInflater.from(parent.context), R.layout.listitem_e03, parent, false)
        val vh = VH(binding.root)

        binding.let {
            it.setLifecycleOwner(lifecycleOwner)
            it.vh = vh
        }

        vh.content.observe(lifecycleOwner, Observer {
            val memo = items[vh.layoutPosition]
            memo.content = it ?: ""
        })
        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int): Unit = with(holder) {
        val memo = items[position]
        content.value = memo.content
    }

}