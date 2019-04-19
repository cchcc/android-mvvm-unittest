package cchcc.learn.amu.e03

import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import android.view.View
import android.view.ViewConfiguration
import android.widget.EditText
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import cchcc.learn.amu.R
import cchcc.learn.amu.util.ViewActionsEx
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
        val givenNewContent = "new content"

        // when
        Espresso.onView(withId(R.id.et_content_new)).perform(click(), replaceText(givenNewContent))
        Espresso.onView(withId(R.id.bt_add)).perform(click())

        // then
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(0
                        , object : ViewAction {
                    override fun getDescription(): String = "check added memo content is \"$givenNewContent\""

                    override fun getConstraints(): Matcher<View> = hasDescendant(withText(givenNewContent))

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
        val givenIdxWillBeRemoved = 3
        var beforeContent = ""

        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(givenIdxWillBeRemoved
                        , object : ViewAction {
                    override fun getDescription(): String = "get before content"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        beforeContent = view.findViewById<TextView>(R.id.et_content).text.toString()
                    }
                }))

        // when
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(givenIdxWillBeRemoved
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
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(givenIdxWillBeRemoved
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
        val givenIdxWillBeEdit = 3
        val givenReplaceText = "replaced!!"


        // when
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(givenIdxWillBeEdit
                        , object : ViewAction {
                    override fun getDescription(): String = "get before content"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        view.findViewById<EditText>(R.id.et_content).setText(givenReplaceText)
                    }
                }))


        Espresso.onView(withId(R.id.rcv_contents))  // scrolling
                .perform(ViewActionsEx.waiting(500)
                        , RecyclerViewActions.scrollToPosition<E03MemoAdapter.VH>(15)
                        , ViewActionsEx.waiting(500)
                        , RecyclerViewActions.scrollToPosition<E03MemoAdapter.VH>(0)
                        , ViewActionsEx.waiting(500)
                )


        // then
        Espresso.onView(withId(R.id.rcv_contents))
                .perform(RecyclerViewActions.actionOnItemAtPosition<E03MemoAdapter.VH>(givenIdxWillBeEdit
                        , object : ViewAction {
                    override fun getDescription(): String = "check replaced text"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        val content = view.findViewById<TextView>(R.id.et_content).text.toString()
                        Assert.assertEquals(givenReplaceText, content)
                    }
                }))

    }
}