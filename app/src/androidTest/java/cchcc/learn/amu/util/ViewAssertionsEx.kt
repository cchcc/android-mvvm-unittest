package cchcc.learn.amu.util

import androidx.test.espresso.ViewAssertion
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import org.junit.Assert

object ViewAssertionsEx {
    @JvmStatic
    fun isInvisible() = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null ) throw noViewFoundException
        Assert.assertEquals(View.INVISIBLE, view.visibility)
    }

    @JvmStatic
    fun hasItemCountOfRecyclerView(count: Int) = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null ) throw noViewFoundException
        val v = view as androidx.recyclerview.widget.RecyclerView
        Assert.assertEquals(count, v.adapter!!.itemCount)
    }
}