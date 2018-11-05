package cchcc.learn.amu.e02a.di

import cchcc.learn.amu.e02.E02Test
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


val E02aTestViewModelModule = Kodein.Module("E02ViewModel") {
    bind<() -> Boolean>() with singleton { E02Test::justTrue }
}