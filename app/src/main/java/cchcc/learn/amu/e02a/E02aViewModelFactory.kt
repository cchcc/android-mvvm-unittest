package cchcc.learn.amu.e02a

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import cchcc.learn.amu.e02.E02ViewModel
import cchcc.learn.amu.e02a.di.E02aViewModelModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class E02aViewModelFactory : ViewModelProvider.Factory, KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(E02aViewModelModule)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val vm by kodein.newInstance { E02ViewModel(instance()) }

        return when (modelClass) {
            E02ViewModel::class.java -> vm
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
    }

}