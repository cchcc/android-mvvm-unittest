package cchcc.learn.amu.e02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import cchcc.learn.amu.FragmentTestActivity
import cchcc.learn.amu.R
import cchcc.learn.amu.util.ViewActionsEx
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class E02FragmentTest {
    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {}

    private fun justTrue() = true

    @Before
    fun setFragment() {

        // given
        val createVMFactory = {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T = E02ViewModel(::justTrue).apply {
                    speedOfAnim.value = 10.0f
                } as T
            }
        }

        rule.activity.replaceFragment(E02Fragment.newInstance(createVMFactory))
    }


    @Test
    fun score_should_increase_when_success() {
        // when
        Espresso.onView(withId(R.id.bt_try)).perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(ViewActionsEx.waiting(1000)) // waiting to end animation

        // then
        val expected = String.format(rule.activity.resources.getString(R.string.e02_score_format), 1)
        Espresso.onView(withId(R.id.tv_score)).check(matches(withText(expected)))
    }


}