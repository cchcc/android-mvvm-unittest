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

        @Suppress("UNCHECKED_CAST")
        val createFactory = intent.getSerializableExtra("createVMFactory") as? () -> E06ViewModelFactory
            ?: createVMFactory
        val viewModelFactory = createFactory()

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(E06ViewModel::class.java)
        val adapter = E06NumberAdapter(viewModel::colorizeListItem)

        DataBindingUtil.setContentView<ActivityE06Binding>(this, R.layout.activity_e06).also {
            it.setLifecycleOwner(this)
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

    companion object {
        val createVMFactory: () -> E06ViewModelFactory = { E06ViewModelFactory() }
    }
}
