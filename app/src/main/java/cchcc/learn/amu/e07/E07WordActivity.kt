package cchcc.learn.amu.e07

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionSet
import android.view.MenuItem
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.ActivityE07WordBinding
import cchcc.learn.amu.e07.coordinator.E07NavigatorImpl
import cchcc.learn.amu.e07.coordinator.navigator
import cchcc.learn.amu.util.ChangeTextSize
import kotlinx.android.synthetic.main.activity_e07_word.*

class E07WordActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, E07ViewModelFactory(navigator))
                .get(E07WordViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator.activity = this
        DataBindingUtil.setContentView<ActivityE07WordBinding>(this, R.layout.activity_e07_word).let {
            it.viewModel = viewModel
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val transitionName = intent.getStringExtra("transitionName")
        ViewCompat.setTransitionName(tv_word, transitionName)

        val text = intent.getStringExtra("text")
        viewModel.word.value = text


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val startSize = intent.getFloatExtra("startSize", 0.0f)
            val endSize = tv_word.textSize

            setEnterSharedElementCallback(E07WordSharedElementCallback(startSize, endSize))

            window.sharedElementEnterTransition = TransitionSet().apply {
                addTransition(ChangeBounds())
                addTransition(ChangeTextSize())
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.activity = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            viewModel.backToWordList()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
