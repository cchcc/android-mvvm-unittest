package cchcc.learn.amu.util

import android.support.test.espresso.ViewAssertion
import android.support.v7.widget.RecyclerView
import android.view.View
import org.junit.Assert

object ViewAssertionsEx {
    @JvmStatic
    fun isInvisible() = ViewAssertion { view, noViewFoundException ->
        noViewFoundException?.let { throw it }
        Assert.assertEquals(View.INVISIBLE, view.visibility)
    }

    @JvmStatic
    fun hasItemCountOfRecyclerView(count: Int) = ViewAssertion { view, noViewFoundException ->
        noViewFoundException?.let { throw it }
        val v = view as RecyclerView
        Assert.assertEquals(count, v.adapter.itemCount)
    }
}