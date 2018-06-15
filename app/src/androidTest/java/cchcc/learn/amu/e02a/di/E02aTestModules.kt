package cchcc.learn.amu.e02a.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import cchcc.learn.amu.e02a.E02aViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val justTrue = { true }

val E02aTestFragmentModule = Kodein.Module {
    bind<ViewModelProvider.Factory>() with singleton {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = E02aViewModel(justTrue) as T
        }
    }
    bind<E02aViewModel>() with provider {
        ViewModelProviders.of(receiver as Fragment, instance()).get(E02aViewModel::class.java)
    }
}