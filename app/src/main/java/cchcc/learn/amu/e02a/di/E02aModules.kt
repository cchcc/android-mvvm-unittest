package cchcc.learn.amu.e02a.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import cchcc.learn.amu.e02.E02ViewModel
import cchcc.learn.amu.e02a.E02aViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import java.util.*

val E02aViewModelModule = Kodein.Module("E02ViewModel") {
    bind<() -> Boolean>() with singleton {
        val r = Random()
        val nextBoolean: () -> Boolean = {
            r.nextBoolean()
        }

        nextBoolean
    }
}

val E02aFragmentModule = Kodein.Module("E02Fragment") {
    bind<ViewModelProvider.Factory>() with singleton {
        E02aViewModelFactory()
    }
    bind<E02ViewModel>() with provider {
        ViewModelProviders.of(context as Fragment, instance()).get(E02ViewModel::class.java)
    }
}