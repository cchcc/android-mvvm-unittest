package cchcc.learn.amu.e01

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE01Binding

class E01Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(E01ViewModel::class.java)

        DataBindingUtil.setContentView<ActivityE01Binding>(this, R.layout.activity_e01).let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }

        val setInvisibleResult = Observer<String?> { viewModel.visibleResult.value = false }
        viewModel.left.observe(this, setInvisibleResult)
        viewModel.right.observe(this, setInvisibleResult)
    }
}
