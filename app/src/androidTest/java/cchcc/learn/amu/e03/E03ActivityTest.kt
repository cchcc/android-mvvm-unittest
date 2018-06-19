package cchcc.learn.amu.e03

import android.support.test.espresso.Espresso
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewConfiguration
import android.widget.EditText
import android.widget.TextView
import cchcc.learn.amu.R
import cchcc.learn.amu.util.WaitingViewAction
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class E03ActivityTest {
    @get:Rule
    val rule = object : ActivityTestRule<E03Activity>(E03Activity::class.java) {}

    @Test
    fun add() {
        // given
        val newContent = "new content"

        // when
        Espresso.onView(withId(R.id.et_content_new)).perform(click(), replaceText(newContent))
        Espresso.onView(withId(R.id.bt_add)).perform(click())

        // then
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(0
                        , object : ViewAction {
                    override fun getDescription(): String = "check added memo content is \"$newContent\""

                    override fun getConstraints(): Matcher<View> = hasDescendant(withText(newContent))

                    override fun perform(uiController: UiController?, view: View?) {
                        @Suppress("UNCHECKED_CAST")
                        val matcher = constraints as Matcher<in View?>
                        Assert.assertThat(view, matcher)
                    }

                }))
    }


    @Test
    fun remove() {
        // given
        val idx = 3

        var beforeContent = ""

        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(idx
                        , object : ViewAction {
                    override fun getDescription(): String = "get before content"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        beforeContent = view.findViewById<TextView>(R.id.et_content).text.toString()
                    }
                }))

        // when
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(idx
                        , object : ViewAction {
                    override fun getDescription(): String = "click remove"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        view.findViewById<View>(R.id.bt_remove).performClick()
                        uiController.loopMainThreadForAtLeast(ViewConfiguration.getTapTimeout().toLong())
                    }
                }))


        // then
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(idx
                        , object : ViewAction {
                    override fun getDescription(): String = "check remove"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        val content = view.findViewById<TextView>(R.id.et_content).text.toString()
                        Assert.assertFalse(content == beforeContent)
                    }
                }))
    }


    @Test
    fun edit() {
        // given
        val idx = 3
        val replaceText = "replaced!!"


        // when
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(idx
                        , object : ViewAction {
                    override fun getDescription(): String = "get before content"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        view.findViewById<EditText>(R.id.et_content).setText(replaceText)
                    }
                }))


        Espresso.onView(withId(R.id.rcv_contents))  // scrolling
                .perform(WaitingViewAction(500)
                        , RecyclerViewActions.scrollToPosition<E03MemoAdapter.VH>(15)
                        , WaitingViewAction(500)
                        , RecyclerViewActions.scrollToPosition<E03MemoAdapter.VH>(0)
                        , WaitingViewAction(500)
                )


        // then
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(idx
                        , object : ViewAction {
                    override fun getDescription(): String = "check replaced text"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        val content = view.findViewById<TextView>(R.id.et_content).text.toString()
                        Assert.assertEquals(replaceText, content)
                    }
                }))

    }
}