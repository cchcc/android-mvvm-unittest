package cchcc.learn.amu.e03

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class E03ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun add() {
        // given
        val viewModel = E03ViewModel()

        val beforeSize = viewModel.items.value!!.size
        val newContent = "new content"

        viewModel.newContent.value = newContent

        // when
        viewModel.add()

        // then
        Assert.assertEquals(newContent, viewModel.items.value!![0].content)
        Assert.assertEquals(beforeSize + 1, viewModel.items.value!!.size)
    }

    @Test
    fun remove() {
        // given
        val viewModel = E03ViewModel()

        val beforeSize = viewModel.items.value!!.size
        val idx = 3
        val beforeMemo = viewModel.items.value!![idx]

        // when
        viewModel.removeAt(idx)

        // then
        Assert.assertFalse(beforeMemo == viewModel.items.value!![idx])
        Assert.assertEquals(beforeSize - 1, viewModel.items.value!!.size)
    }

}