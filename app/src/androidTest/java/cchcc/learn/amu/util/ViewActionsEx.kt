package cchcc.learn.amu.util

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.widget.ProgressBar
import org.hamcrest.Matcher

object ViewActionsEx {
    @JvmStatic
    fun waiting(milliSec: Long) = object : ViewAction {
        override fun getDescription(): String = "waiting $milliSec milli seconds"

        override fun getConstraints(): Matcher<View> = ViewMatchers.isDisplayed()

        override fun perform(uiController: UiController, view: View) =
                uiController.loopMainThreadForAtLeast(milliSec)
    }

    @JvmStatic
    fun progress(value: Int) = object : ViewAction {
        override fun getDescription(): String = "set progress to $value"

        override fun getConstraints(): Matcher<View> = ViewMatchers.isAssignableFrom(ProgressBar::class.java)

        override fun perform(uiController: UiController, view: View) {
            with(view as ProgressBar) {
                progress = value
            }
        }
    }

}