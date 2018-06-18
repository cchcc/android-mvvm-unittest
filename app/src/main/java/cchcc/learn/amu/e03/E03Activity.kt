package cchcc.learn.amu.e03

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE03Binding
import cchcc.learn.amu.e03.data.E03Memo
import kotlinx.android.synthetic.main.activity_e03.*

class E03Activity : AppCompatActivity() {

    private val viewModel: E03ViewModel by lazy { ViewModelProviders.of(this).get(E03ViewModel::class.java) }
    val layoutManager: RecyclerView.LayoutManager by lazy { LinearLayoutManager(this) }
    val adapter: RecyclerView.Adapter<E03MemoAdapter.VH> by lazy { E03MemoAdapter(viewModel.items.value!!, this::onClickRemoveItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityE03Binding>(this, R.layout.activity_e03).also {
            it.setLifecycleOwner(this)
            it.host = this
            it.viewModel = viewModel
        }
    }

    fun onClickAdd() {
        viewModel.add()
        adapter.notifyItemInserted(0)
        adapter.notifyItemRangeChanged(1, viewModel.items.value!!.size -1)
        rcv_contents.post {
            rcv_contents.scrollToPosition(0)
        }
    }

    fun onClickRemoveItem(memo: E03Memo, idx: Int) {
        viewModel.removeAt(idx)
        adapter.notifyItemRemoved(idx)
        adapter.notifyItemRangeChanged(idx, viewModel.items.value!!.size - idx)
        Toast.makeText(this@E03Activity, "\"${memo.content}\" is removed", Toast.LENGTH_SHORT).show()
    }
}
