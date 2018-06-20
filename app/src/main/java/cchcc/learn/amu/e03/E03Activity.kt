package cchcc.learn.amu.e03

import android.arch.lifecycle.Observer
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
    private val adapter: RecyclerView.Adapter<E03MemoAdapter.VH> by lazy { E03MemoAdapter(viewModel.memos.value!!, viewModel::remove) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityE03Binding>(this, R.layout.activity_e03).let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }

        viewModel.listAction.observe(this, Observer {
            when(it) {
                is E03ViewModel.ListAction.Added -> onAdded()
                is E03ViewModel.ListAction.Removed -> onRemoved(it.memo, it.idx)
            }
        })

        rcv_contents.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun onAdded() {
        adapter.notifyItemInserted(0)
        adapter.notifyItemRangeChanged(1, viewModel.sizeOfMemos -1)
        rcv_contents.post {
            rcv_contents.scrollToPosition(0)
        }
    }

    private fun onRemoved(memo: E03Memo, idx: Int) {
        adapter.notifyItemRemoved(idx)
        adapter.notifyItemRangeChanged(idx, viewModel.sizeOfMemos - idx)
        Toast.makeText(this, "\"${memo.content}\" is removed", Toast.LENGTH_SHORT).show()
    }
}
