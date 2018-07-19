package cchcc.learn.amu.e05

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class E05ViewModel : ViewModel() {

    val logList: MutableLiveData<MutableList<String>> = MutableLiveData<MutableList<String>>().apply {
        value = mutableListOf()
    }
    val progress = MutableLiveData<Int>().apply { value = 0 }
    val animSpeed: LiveData<Int> = Transformations.map(progress) { it + 1 }

    val sdf = SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault())

    fun add(line: String) {
        logList.value!!.add("${sdf.format(Date())}   $line")
    }

    fun clear() {
        logList.value = mutableListOf("clearAction")
    }
}