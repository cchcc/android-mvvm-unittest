package cchcc.learn.amu.e01a

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE01aBinding

class E01aActivity : AppCompatActivity() {

    private val viewModel: E01aViewModel by lazy {
        ViewModelProviders.of(this).get(E01aViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityE01aBinding>(this, R.layout.activity_e01a).let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }
    }
}
