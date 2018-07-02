package cchcc.learn.amu.e05

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.ListView
import cchcc.learn.amu.FragmentTestActivity
import cchcc.learn.amu.R
import cchcc.learn.amu.e02.E02
import cchcc.learn.amu.e02.E02ViewModel
import cchcc.learn.amu.util.ViewActionsEx
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class E05FragmentTest {
    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {
        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            activity.replaceFragment(E05Fragment.newInstance { e02ViewModel })
        }
    }

    val e02ViewModel by lazy { E02ViewModel(E02::justTrue) }

    @Test
    fun tryResult_should_add_list() {
        // when
        UiThreadStatement.runOnUiThread { e02ViewModel.tryResult() }
        UiThreadStatement.runOnUiThread { e02ViewModel.applyScore() }

        // then
        onView(withId(R.id.lv_log))
                .check { view, noViewFoundException ->
                    noViewFoundException?.let { throw it }
                    (view as? ListView)?.let {
                        it.count == 2
                    } ?: throw NoMatchingViewException.Builder().build()

                }

    }

    @Test
    fun clear_should_clear_list() {
        // when
        UiThreadStatement.runOnUiThread { e02ViewModel.tryResult() }
        UiThreadStatement.runOnUiThread { e02ViewModel.applyScore() }
        UiThreadStatement.runOnUiThread { e02ViewModel.clear() }

        // then
        onView(withId(R.id.lv_log))
                .check { view, noViewFoundException ->
                    noViewFoundException?.let { throw it }
                    (view as? ListView)?.let {
                        it.count == 0
                    } ?: throw NoMatchingViewException.Builder().build()
                }
    }
}