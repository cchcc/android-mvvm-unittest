package cchcc.learn.amu.e01a

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import cchcc.learn.amu.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class E01aActivityTest {
    @get:Rule
    val rule = ActivityTestRule<E01aActivity>(E01aActivity::class.java)

    @Test
    fun one_plus_one_is_two() {
        // given
        val givenVal = "1"

        // when
        Espresso.onView(ViewMatchers.withId(R.id.et_left)).perform(ViewActions.typeText(givenVal))
        Espresso.onView(ViewMatchers.withId(R.id.et_right)).perform(ViewActions.typeText(givenVal))

        // then
        val expectedText = String.format(rule.activity.getString(R.string.e01_result_format),givenVal, givenVal, "2")
        Espresso.onView(ViewMatchers.withId(R.id.tv_result)).check(matches(ViewMatchers.withText(expectedText)))
    }
}