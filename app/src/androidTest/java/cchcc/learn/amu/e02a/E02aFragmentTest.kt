package cchcc.learn.amu.e02a

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import cchcc.learn.amu.FragmentTestActivity
import cchcc.learn.amu.R
import cchcc.learn.amu.e02a.di.E02aTestFragmentModule
import cchcc.learn.amu.util.ViewActionsEx
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein

@RunWith(AndroidJUnit4::class)
class E02aFragmentTest {
    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {}

    @Before
    fun setFragment() {
        val createKodein:() -> Kodein = {
            Kodein.lazy { import(E02aTestFragmentModule) }
        }

        rule.activity.replaceFragment(E02aFragment.newInstance(createKodein))
    }


    @Test
    fun score_should_increase_when_success() {
        // when
        Espresso.onView(withId(R.id.lav_result)).perform(ViewActionsEx.setSpeed(10.0f))
        Espresso.onView(withId(R.id.bt_try)).perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(ViewActionsEx.waiting(1000)) // waiting to end animation

        // then
        val expected = String.format(rule.activity.resources.getString(R.string.e02_score_format), 1)
        Espresso.onView(withId(R.id.tv_score)).check(matches(withText(expected)))
    }


}