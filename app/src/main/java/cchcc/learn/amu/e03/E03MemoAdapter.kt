package cchcc.learn.amu.e03

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ListitemE03Binding
import cchcc.learn.amu.e03.data.E03Memo

typealias OnListItemEvent = (E03Memo, Int) -> Unit

class E03MemoAdapter(private val items: List<E03Memo>, val onClickRemove: OnListItemEvent) : RecyclerView.Adapter<E03MemoAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        var idx: Int = -1
        lateinit var memo: E03Memo
        val content = MutableLiveData<String>()

        fun onClickRemove(): Unit = this@E03MemoAdapter.onClickRemove(memo, idx)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val lifecycleOwner = parent.context as LifecycleOwner
        val binding = DataBindingUtil.inflate<ListitemE03Binding>(LayoutInflater.from(parent.context), R.layout.listitem_e03, parent, false)
        val vh = VH(binding.root)

        binding.let {
            it.setLifecycleOwner(lifecycleOwner)
            it.vh = vh
        }

        vh.content.observe(lifecycleOwner, Observer { vh.memo.content = it ?: "" })
        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int): Unit = with(holder) {
        idx = position
        memo = items[position]
        content.value = memo.content
    }

}