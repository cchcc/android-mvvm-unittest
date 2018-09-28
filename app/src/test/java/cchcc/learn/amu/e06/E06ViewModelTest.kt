package cchcc.learn.amu.e06

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import cchcc.learn.amu.e06.data.E06DataRepository
import cchcc.learn.amu.e06.data.E06Number
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class E06ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun refreshList() {
        val repo = mockk<E06DataRepository>()
        val viewModel = E06ViewModel(repo)

        // given
        every { repo.refresh() } returns Unit

        // when
        viewModel.refreshList {  }

        // then
        verify { repo.refresh() }
    }


    @Test
    fun moveToTopOfList() {
        val viewModel = E06ViewModel(E06DataRepository())
        val lifecycle = LifecycleRegistry(mockk()).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        // given
        val observe = mockk<(Unit?) -> Unit>()
        every { observe.invoke(Unit) } returns Unit
        viewModel.moveToTopAction.observe({ lifecycle }, observe)

        // when
        viewModel.moveToTopOfList()

        // then
        verify { observe.invoke(Unit) }
    }

    @Test
    fun colorizeListItem() {
        val viewModel = E06ViewModel(E06DataRepository())

        // given
        val numWithAlphaIsLess = E06Number(1, 0.1f)

        // when
        viewModel.colorizeListItem(numWithAlphaIsLess) {}

        // then
        Assert.assertEquals(0.2f, numWithAlphaIsLess.alpha)


        // given
        val numWithAlphaIs1 = E06Number(1, 1.0f)

        // when
        viewModel.colorizeListItem(numWithAlphaIs1) {}

        // then
        Assert.assertEquals(1.0f, numWithAlphaIs1.alpha)
    }

}