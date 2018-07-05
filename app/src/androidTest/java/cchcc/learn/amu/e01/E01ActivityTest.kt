package cchcc.learn.amu.e01

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import cchcc.learn.amu.R
import cchcc.learn.amu.util.ViewAssertionsEx
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class E01ActivityTest {
    @get:Rule
    val rule = ActivityTestRule<E01Activity>(E01Activity::class.java)

    private fun plus() {
        Espresso.onView(withId(R.id.et_left)).perform(ViewActions.typeText("1"))
        Espresso.onView(withId(R.id.et_right)).perform(ViewActions.typeText("1"))
        Espresso.onView(withId(R.id.bt_plus)).perform(ViewActions.click())
    }

    @Test
    fun result_should_be_invisible_when_input() {
        // given
        plus() // result view is visible

        // when
        Espresso.onView(withId(R.id.et_left)).perform(ViewActions.typeText("11"))

        // then
        Espresso.onView(withId(R.id.tv_result)).check(ViewAssertionsEx.isInvisible())
    }
}