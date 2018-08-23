package cchcc.learn.amu.e07

import android.annotation.TargetApi
import android.app.SharedElementCallback
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.TextView

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class E07WordSharedElementCallback(private val startSize: Float, private val endSize: Float) : SharedElementCallback() {
    override fun onSharedElementStart(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
        val view = sharedElements?.get(0) as TextView
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, startSize)
    }

    override fun onSharedElementEnd(sharedElementNames: MutableList<String>?, sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
        val view = sharedElements?.get(0) as TextView
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, endSize)

        val oldWidth = view.measuredWidth
        val oldHeight = view.measuredHeight

        val widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(widthSpec, heightSpec)

        val newWidth = view.measuredWidth
        val newHeight = view.measuredHeight

        val widthDiff = newWidth - oldWidth
        val heightDiff = newHeight - oldHeight
        view.layout(view.left - widthDiff / 2, view.top - heightDiff / 2,
                view.right + widthDiff / 2, view.bottom + heightDiff / 2)
    }
}