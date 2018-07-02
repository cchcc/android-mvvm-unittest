package cchcc.learn.amu.util

import android.view.View
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

object ViewMatchersEx {
    @JvmStatic
    fun isInvisible() = object : BaseMatcher<View>() {
        var v: View? = null
        override fun describeTo(description: Description) {
            val visibilityName = when (v?.visibility) {
                View.VISIBLE -> "VISIBLE"
                View.GONE -> "GONE"
                View.INVISIBLE -> "INVISIBLE"
                else -> "??"
            }
            description.appendText("view.visibility is $visibilityName")
        }

        override fun matches(item: Any): Boolean {
            v = item as? View
            return v?.visibility == View.INVISIBLE
        }
    }
}
