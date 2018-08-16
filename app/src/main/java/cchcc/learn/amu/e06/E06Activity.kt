package cchcc.learn.amu.e06

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE06Binding
import kotlinx.android.synthetic.main.activity_e06.*

class E06Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(E06ViewModel::class.java)
        val adapter = E06NumberAdapter(viewModel::colorizeListItem)

        DataBindingUtil.setContentView<ActivityE06Binding>(this, R.layout.activity_e06).also {
            it.viewModel = viewModel
        }

        rcv_numbers.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }

        viewModel.numLists.observe(this::getLifecycle, adapter::submitList)
        viewModel.moveToTopAction.observe(this::getLifecycle) {
            rcv_numbers.scrollToPosition(0)
        }
    }
}
