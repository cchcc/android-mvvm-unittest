package cchcc.learn.amu.e01

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE01Binding

class E01Activity : AppCompatActivity() {

    private val viewModel: E01ViewModel by lazy {
        ViewModelProviders.of(this).get(E01ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityE01Binding>(this, R.layout.activity_e01).also {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }

        val setInvisibleResult = Observer<String?> { viewModel.visibleResult.value = false }
        viewModel.left.observe(this, setInvisibleResult)
        viewModel.right.observe(this, setInvisibleResult)
    }
}
