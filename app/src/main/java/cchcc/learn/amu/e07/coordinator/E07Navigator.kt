package cchcc.learn.amu.e07.coordinator

import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import cchcc.learn.amu.e07.E07WordActivity
import java.lang.IllegalStateException

interface E07Navigator {
    fun navigateWord()
    fun navigateBackToWordList()
}

class E07NavigatorImpl(val activity: AppCompatActivity) : E07Navigator {

    var navOptionsWord: Triple<View, String, Float>? = null

    override fun navigateWord() {
        val (sharedElement, text, startSize) = navOptionsWord
                ?: throw IllegalStateException("set navigator.navOptionsWord first")

        navOptionsWord = null  // avoid memory leak

        val transitionName = ViewCompat.getTransitionName(sharedElement)!!
        val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedElement, transitionName).toBundle()

        activity.startActivity(Intent(activity, E07WordActivity::class.java)
                .putExtra("text", text)
                .putExtra("transitionName", transitionName)
                .putExtra("startSize", startSize)
                , bundle)
    }

    override fun navigateBackToWordList() {
        activity.onBackPressed()
    }
}
