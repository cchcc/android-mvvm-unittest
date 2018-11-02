package cchcc.learn.amu.e07

import androidx.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionSet
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
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

        val adapter = E07WordListAdapter(::goWordScreen)

        rcv_list.let {
            it.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
            it.adapter = adapter
        }

        viewModel.wordList.observe(this::getLifecycle, adapter::submitList)
    }

    private fun goWordScreen(text: String, sharedElement: View) {
        val startSize = (sharedElement as TextView).textSize
        val transitionName = ViewCompat.getTransitionName(sharedElement)!!

        val extras = Bundle().apply {
            putString("text", text)
            putString("transitionName", transitionName)
            putFloat("startSize", startSize)
        }

        val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedElement, transitionName)
                .toBundle()

        val args = mapOf("extras" to extras, "bundle" to bundle)

        viewModel.goWordScreen { args }
    }
}
