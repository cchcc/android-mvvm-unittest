package cchcc.learn.amu.e04

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class E04ViewModel : ViewModel() {
    val contact = MutableLiveData<String>()
    val pickContactAction = MutableLiveData<Unit>()

    fun pickContact() {
        pickContactAction.value = Unit
    }

}