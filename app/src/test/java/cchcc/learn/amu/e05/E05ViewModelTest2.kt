package cchcc.learn.amu.e05

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.*

class E05ViewModelTest2 {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val viewModel by lazy { E05ViewModel() }

    private val observer = mockk< Observer<Int>>().apply {
        every { this@apply.onChanged(1) } returns Unit
    }

    @Before
    fun observe() {
        viewModel.animSpeed.observeForever(observer)
    }

    @Test
    fun animSpeed() {
        // when
        viewModel.progress.value = 0

        // then
        verify { observer.onChanged(1) }
        Assert.assertEquals(1, viewModel.animSpeed.value)
    }

    @After
    fun removeObserver() {
        viewModel.animSpeed.removeObserver(observer)
    }
}