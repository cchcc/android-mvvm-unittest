package cchcc.learn.amu.e05

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import io.mockk.mockk
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
        val givenLine = "first"

        // when
        viewModel.add(givenLine)

        // then
        Assert.assertEquals(1, viewModel.logList.value!!.size)
        Assert.assertTrue(viewModel.logList.value!![0].contains(givenLine))

        // when
        viewModel.add(givenLine)
        viewModel.add(givenLine)
        viewModel.clear()

        // then
        Assert.assertEquals(1, viewModel.logList.value!!.size)
    }



    @Test
    fun animSpeed() {
        val viewModel = E05ViewModel()
        val lifecycle = LifecycleRegistry(mockk()).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
        viewModel.animSpeed.observe({ lifecycle }) {}

        // given
        val givenProgress = 0

        // when
        viewModel.progress.value = givenProgress

        // then
        Assert.assertEquals(1, viewModel.animSpeed.value)
    }

}