package cchcc.learn.amu.e01a

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE01aBinding

class E01aActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(E01aViewModel::class.java)

        DataBindingUtil.setContentView<ActivityE01aBinding>(this, R.layout.activity_e01a).let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }
    }
}
