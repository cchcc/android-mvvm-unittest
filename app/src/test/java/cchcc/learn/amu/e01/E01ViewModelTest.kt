package cchcc.learn.amu.e01

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class E01ViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val viewModel by lazy { E01ViewModel() }

    private fun setValues(value: String) {
        viewModel.left.value = value
        viewModel.right.value = value
    }

    @Test
    fun plus() {
        // given
        val givenVal = "1"
        setValues(givenVal)

        // when
        viewModel.plus()

        // then
        Assert.assertEquals("2", viewModel.result.value)
    }


    @Test
    fun clear() {
        // given
        val givenVal = "1"
        setValues(givenVal)

        // when
        viewModel.clear()

        // then
        Assert.assertEquals("", viewModel.left.value)
        Assert.assertEquals("", viewModel.right.value)
        Assert.assertEquals("", viewModel.result.value)
    }
}