package cchcc.learn.amu.e05

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class E05ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun addAndClear() {
        val viewModel = E05ViewModel()

        // given
        val line = "first"

        // when
        viewModel.add(line)

        // then
        Assert.assertEquals(1, viewModel.logList.value!!.size)
        Assert.assertTrue(viewModel.logList.value!![0].contains(line))

        // when
        viewModel.add(line)
        viewModel.add(line)
        viewModel.clear()

        // then
        Assert.assertEquals(1, viewModel.logList.value!!.size)
    }

}