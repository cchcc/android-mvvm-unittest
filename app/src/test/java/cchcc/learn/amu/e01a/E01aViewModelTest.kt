package cchcc.learn.amu.e01a

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.*

class E01aViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val viewModel by lazy { E01aViewModel() }

    private val resultObserver = mockk<Observer<String>>()
    private val resultVisibleObserver = mockk<Observer<Boolean>>().also {
        every { it.onChanged(false) } returns Unit    // for initial value
    }

    @Before
    fun observe() {
        viewModel.result.observeForever(resultObserver)
        viewModel.visibleResult.observeForever(resultVisibleObserver)
    }

    @Test fun result_only_when_two_values_changed_both() {
        // given
        val leftVal = "1"
        val rightVal = "1"
        val expectedResultVal = "2"

        // when
        viewModel.left.value = leftVal

        // then
        Assert.assertNull(viewModel.result.value)

        // when
        every { resultObserver.onChanged(expectedResultVal) } returns Unit
        every { resultVisibleObserver.onChanged(true) } returns Unit
        viewModel.right.value = rightVal

        // then
        verify { resultObserver.onChanged(expectedResultVal) }
        verify { resultVisibleObserver.onChanged(true) }
        Assert.assertEquals(expectedResultVal, viewModel.result.value)
    }

    @After
    fun removeObserver() {
        viewModel.visibleResult.removeObserver(resultVisibleObserver)
        viewModel.result.removeObserver(resultObserver)
    }
}