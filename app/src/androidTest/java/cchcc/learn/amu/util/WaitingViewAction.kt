package cchcc.learn.amu.util

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import org.hamcrest.Matcher

class WaitingViewAction(private val milliSec: Long) : ViewAction {
    override fun getDescription(): String = "waiting $milliSec milli seconds"

    override fun getConstraints(): Matcher<View> = ViewMatchers.isDisplayed()

    override fun perform(uiController: UiController, view: View?) {
        uiController.loopMainThreadForAtLeast(milliSec)
    }

}