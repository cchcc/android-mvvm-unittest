package cchcc.learn.amu.e07

import android.annotation.SuppressLint
import android.support.v4.view.ViewCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import kotlinx.android.synthetic.main.listitem_e07.view.*

class E07WordListAdapter(val goWordScreen: (String, View) -> Unit) : ListAdapter<String, E07WordListAdapter.VH>(getDiffCallback()) {
    class VH(view: View) : RecyclerView.ViewHolder(view)

    private val numberIter = generateSequence(0) { it + 1 }.iterator()

    @SuppressLint("ResourceType")
        override fun onCreateViewHolder(v: ViewGroup, viewType: Int): E07WordListAdapter.VH {

        val view = LayoutInflater.from(v.context).inflate(R.layout.listitem_e07, v, false)
        ViewCompat.setTransitionName(view.tv_word, "listItem#${numberIter.next()}")

        return VH(view).apply {
            itemView.setOnClickListener {
                goWordScreen(getItem(layoutPosition), itemView.tv_word)
            }
        }
    }

    override fun onBindViewHolder(vh: E07WordListAdapter.VH, position: Int) {
        val text = getItem(position)
        vh.itemView.tv_word.text = text
    }

    companion object {
        @JvmStatic
        fun getDiffCallback() = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(p0: String, p1: String): Boolean = p0 === p1

            override fun areContentsTheSame(p0: String, p1: String): Boolean = p0 == p1

        }
    }
}