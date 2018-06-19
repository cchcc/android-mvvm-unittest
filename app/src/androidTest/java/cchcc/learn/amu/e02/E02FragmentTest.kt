package cchcc.learn.amu.e02

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.test.espresso.Espresso
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import cchcc.learn.amu.FragmentTestActivity
import cchcc.learn.amu.R
import cchcc.learn.amu.util.WaitingViewAction
import com.airbnb.lottie.LottieAnimationView
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class E02FragmentTest {
    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {}

    @Before
    fun setFragment() {

        // given
        val createVMFactory = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T = E02ViewModel(E02::justTrue) as T
            }
        }

        rule.activity.replaceFragment(E02Fragment.newInstance(createVMFactory))
    }


    @Test
    fun score_should_increase_when_success() {
        // when
        Espresso.onView(withId(R.id.bt_try)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.lav_result)).perform(object : ViewAction {
            override fun getDescription(): String = "set animation faster"

            override fun getConstraints(): Matcher<View> = Matchers.any(View::class.java)

            override fun perform(uiController: UiController, view: View) {
                (view as LottieAnimationView).speed = 10.0f
            }
        })
        Espresso.onView(isRoot()).perform(WaitingViewAction(1500)) // waiting to end animation

        // then
        val expected = String.format(rule.activity.resources.getString(R.string.e02_score_format), 1)
        Espresso.onView(withId(R.id.tv_score)).check(matches(withText(expected)))
    }


}