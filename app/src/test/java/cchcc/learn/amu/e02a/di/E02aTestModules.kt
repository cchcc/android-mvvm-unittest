package cchcc.learn.amu.e02a.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val justTrue = { true }

val E02aTestViewModelModule = Kodein.Module {
    bind<() -> Boolean>() with singleton { justTrue }
}