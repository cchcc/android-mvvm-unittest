package cchcc.learn.amu.e02a

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cchcc.learn.amu.e02.E02ViewModel
import cchcc.learn.amu.e02a.di.E02aTestViewModelModule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class E02aViewModelTest : KodeinAware {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    override val kodein: Kodein = Kodein.lazy {
        import(E02aTestViewModelModule)
    }

    @Test
    fun tryResult_and_applyScore() {
        val viewModel by kodein.newInstance { E02ViewModel(instance()) }
        // given
        // none

        // when
        viewModel.tryResult()
        // then
        Assert.assertEquals(E02ViewModel.TryResult.SUCCESS, viewModel.result.value)

        // when
        viewModel.applyScore()
        // then
        Assert.assertEquals(1, viewModel.score.value)
    }

}