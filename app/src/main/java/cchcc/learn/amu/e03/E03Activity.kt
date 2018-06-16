package cchcc.learn.amu.e03

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE03Binding

class E03Activity : AppCompatActivity() {

    private val viewModel: E03ViewModel by lazy { ViewModelProviders.of(this).get(E03ViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityE03Binding>(this, R.layout.activity_e03).also {
            it.setLifecycleOwner(this)
            it.host = this
            it.viewModel = viewModel
        }
    }


}
