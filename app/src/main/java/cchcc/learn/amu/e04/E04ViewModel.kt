package cchcc.learn.amu.e04

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel


class E04ViewModel : ViewModel() {

    val nameAndPhone = MutableLiveData<Pair<String, String>>()
    val contact = Transformations.map(nameAndPhone) {
        (name, phone) -> "Name : $name\nPhone : $phone"
    }
    val pickContactAction = MutableLiveData<Unit>()

    fun pickContact() {
        pickContactAction.value = Unit
    }
}