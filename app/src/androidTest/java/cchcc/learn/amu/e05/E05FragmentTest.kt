package cchcc.learn.amu.e05

import androidx.lifecycle.Observer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.rule.ActivityTestRule
import cchcc.learn.amu.FragmentTestActivity
import cchcc.learn.amu.R
import cchcc.learn.amu.e02.E02ViewModel
import cchcc.learn.amu.util.ViewActionsEx
import cchcc.learn.amu.util.ViewAssertionsEx
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class E05FragmentTest {
    @get:Rule
    val rule = ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java)

    lateinit var fragment: E05Fragment
    lateinit var viewModel: E02ViewModel

    private fun justTrue() = true

    @Before
    fun setFragment() {
        UiThreadStatement.runOnUiThread {
            viewModel = E02ViewModel(::justTrue)
            fragment = E05Fragment.newInstance { viewModel }
            rule.activity.replaceFragment(fragment)
        }
    }

    @Test
    fun tryResult_should_add_list() {
        // when
        UiThreadStatement.runOnUiThread {
            viewModel.tryResult()
            viewModel.applyScore()
        }

        // then
        onView(withId(R.id.rcv_log)).check(ViewAssertionsEx.hasItemCountOfRecyclerView(4))
    }

    @Test
    fun clear_should_clear_list() {
        // when
        UiThreadStatement.runOnUiThread { viewModel.tryResult()
            viewModel.applyScore()
            viewModel.clear()
        }

        // then
        onView(withId(R.id.rcv_log)).check(ViewAssertionsEx.hasItemCountOfRecyclerView(1))
    }

    @Test
    fun observing_animSpeed_works() {
        UiThreadStatement.runOnUiThread {
            viewModel.speedOfAnim.observe(fragment, Observer {} )
        }

        // given
        val givenProgress = 2

        // when
        onView(withId(R.id.sb_speed)).perform(ViewActionsEx.setProgress(givenProgress))

        // then
        Assert.assertEquals(3.0f, viewModel.speedOfAnim.value)
    }
}