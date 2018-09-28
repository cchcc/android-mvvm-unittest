package cchcc.learn.amu.e06

import androidx.paging.PagedListAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ListitemE06Binding
import cchcc.learn.amu.e06.data.E06Number

class E06NumberAdapter(val colorize: (E06Number?, refreshItem: () -> Unit) -> Unit) : PagedListAdapter<E06Number, E06NumberAdapter.VH>(getDiffCallback()) {
    inner class VH(val binding: ListitemE06Binding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<ListitemE06Binding>(LayoutInflater.from(parent.context), R.layout.listitem_e06, parent, false)
        val vh = VH(binding)
        binding.onClick = View.OnClickListener {
            val item = getItem(vh.layoutPosition)
            if (item != null) {
                val refreshItem = {
                    notifyItemChanged(vh.layoutPosition)
                }

                this@E06NumberAdapter.colorize(item, refreshItem)
            }
        }
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.num = getItem(position)
    }


    companion object {

        @JvmStatic
        fun getDiffCallback() = object : DiffUtil.ItemCallback<E06Number>() {
            override fun areItemsTheSame(oldItem: E06Number, newItem: E06Number): Boolean = oldItem === newItem
            override fun areContentsTheSame(oldItem: E06Number, newItem: E06Number): Boolean = oldItem.num == newItem.num
        }
    }
}