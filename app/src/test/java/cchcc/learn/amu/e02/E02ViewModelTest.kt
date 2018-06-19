package cchcc.learn.amu.e02

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class E02ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun tryResult_and_applyScore() {
        // given
        val viewModel = E02ViewModel(E02::justTrue)

        viewModel.tryResult() // when
        Assert.assertEquals(E02ViewModel.TryResult.SUCCESS, viewModel.result.value) // then

        viewModel.applyScore() // when
        Assert.assertEquals(1, viewModel.score.value)  // then
    }

}