package cchcc.learn.amu.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.util.SparseArray

private val activityResults by lazy { SparseArray<(Int, Intent?) -> Unit>() }

fun Activity.startActivityWithResult(intent: Intent, resultBlock: (Int, Intent?) -> Unit) {
    val requestCode = Math.abs((hashCode() + resultBlock.hashCode()).toShort().toInt())
    activityResults.put(requestCode, resultBlock)
    startActivityForResult(intent, requestCode)
}

fun Activity.activityWithResult(requestCode: Int, resultCode: Int, data: Intent?) {
    activityResults.get(requestCode)?.let {
        activityResults.remove(requestCode)
        it(resultCode, data)
    }
}