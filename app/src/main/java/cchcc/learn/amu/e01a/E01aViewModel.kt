package cchcc.learn.amu.e01a

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class E01aViewModel : ViewModel() {
    val left = MutableLiveData<String>()
    val right = MutableLiveData<String>()
    val result = MediatorLiveData<String>().apply {

        val plus: (String?) -> Unit = plus@{
            val leftNum = left.value?.toIntOrNull()
            if (leftNum == null) {
                visibleResult.value = false
                return@plus
            }

            val rightNum = right.value?.toIntOrNull()
            if (rightNum == null) {
                visibleResult.value = false
                return@plus
            }

            value = (leftNum + rightNum).toString()
            visibleResult.value = true
        }

        addSource(left, plus)
        addSource(right, plus)
    }

    val visibleResult = MutableLiveData<Boolean>().apply { value = false }
}