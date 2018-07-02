package cchcc.learn.amu.e05

import android.arch.lifecycle.Observer
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import cchcc.learn.amu.FragmentTestActivity
import cchcc.learn.amu.R
import cchcc.learn.amu.e02.E02
import cchcc.learn.amu.e02.E02ViewModel
import cchcc.learn.amu.util.ViewActionsEx
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class E05FragmentTest2 {
    @get:Rule
    val rule = object : ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {
        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            activity.replaceFragment(E05Fragment.newInstance { e02ViewModel })
        }
    }

    val e02ViewModel by lazy { E02ViewModel(E02::justTrue) }

    val animObserver = mockk<Observer<Float>>().also {
        every { it.onChanged(1.0f) } returns Unit   // for initial value
    }

    @Before
    fun observe() {
        UiThreadStatement.runOnUiThread {
            e02ViewModel.animSpeed.observeForever(animObserver)
        }
    }

    @Test
    fun observing_animSpeed_works() {
        // given
        val setValue = 2
        val expectedValue = 3.0f

        // when
        every { animObserver.onChanged(expectedValue) } returns Unit
        onView(withId(R.id.sb_speed)).perform(ViewActionsEx.progress(setValue))

        // then
        verify { animObserver.onChanged(expectedValue) }
        Assert.assertEquals(expectedValue, e02ViewModel.animSpeed.value)
    }

    @After
    fun removeObserver() {
        UiThreadStatement.runOnUiThread {
            e02ViewModel.animSpeed.removeObserver(animObserver)
        }
    }
}