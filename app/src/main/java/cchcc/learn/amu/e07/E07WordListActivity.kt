package cchcc.learn.amu.e07

import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionSet
import android.view.View
import android.widget.TextView
import cchcc.learn.amu.R
import cchcc.learn.amu.e07.coordinator.E07NavigatorImpl
import kotlinx.android.synthetic.main.activity_e07_wordlist.*

class E07WordListActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders
                .of(this, E07ViewModelFactory())
                .get(E07WordListViewModel::class.java).apply {
                    coordinator.navigator = navigator
                }
    }

    private val navigator by lazy { E07NavigatorImpl(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_e07_wordlist)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.enterTransition = TransitionSet().apply {
                addTransition(Fade())
                addTransition(Slide())
            }
        }

        val adapter = E07WordListAdapter(::goDetail)

        rcv_list.let {
            it.layoutManager = GridLayoutManager(this, 2)
            it.adapter = adapter
        }

        viewModel.wordList.observe(this::getLifecycle, adapter::submitList)
    }

    private fun goDetail(text: String, sharedElement: View) {
        val startSize = (sharedElement as TextView).textSize
        navigator.navOptionsWord = Triple(sharedElement, text, startSize)
        viewModel.goWordScreen()
    }
}
