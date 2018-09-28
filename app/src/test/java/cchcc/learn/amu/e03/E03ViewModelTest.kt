package cchcc.learn.amu.e03

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class E03ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun add() {
        val viewModel = E03ViewModel()

        // given
        val beforeSize = viewModel.memos.value!!.size
        val givenNewContent = "new content"

        viewModel.newContent.value = givenNewContent

        // when
        viewModel.add()

        // then
        Assert.assertEquals(givenNewContent, viewModel.memos.value!![0].content)
        Assert.assertEquals(beforeSize + 1, viewModel.memos.value!!.size)
    }

    @Test
    fun remove() {
        val viewModel = E03ViewModel()

        // given
        val beforeSize = viewModel.memos.value!!.size
        val givenIdxWillBeRemove = 3
        val beforeMemo = viewModel.memos.value!![givenIdxWillBeRemove]

        // when
        viewModel.remove(beforeMemo, givenIdxWillBeRemove)

        // then
        Assert.assertFalse(beforeMemo == viewModel.memos.value!![givenIdxWillBeRemove])
        Assert.assertEquals(beforeSize - 1, viewModel.memos.value!!.size)
    }

}