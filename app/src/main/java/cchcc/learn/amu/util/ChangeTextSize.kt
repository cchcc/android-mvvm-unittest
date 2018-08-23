package cchcc.learn.amu.util

import android.animation.ObjectAnimator
import android.util.TypedValue
import android.widget.TextView
import android.view.ViewGroup
import android.animation.Animator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.util.Property

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class ChangeTextSize : Transition {
    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun captureStartValues(transitionValues: TransitionValues) = captureValues(transitionValues)

    override fun captureEndValues(transitionValues: TransitionValues) = captureValues(transitionValues)

    private fun captureValues(transitionValues: TransitionValues) {
        if (transitionValues.view is TextView) {
            val textView = transitionValues.view as TextView
            transitionValues.values.put(PROPNAME_TEXT_SIZE, textView.textSize)
        }
    }

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?,
                                endValues: TransitionValues?): Animator? {
        val startSize = startValues?.values?.get(PROPNAME_TEXT_SIZE) as? Float ?: return null
        val endSize = endValues?.values?.get(PROPNAME_TEXT_SIZE) as? Float ?: return null

        val view = endValues.view as TextView
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, startSize)
        return ObjectAnimator.ofFloat<TextView>(view, TEXT_SIZE_PROPERTY, startSize, endSize)
    }

    companion object {
        const val PROPNAME_TEXT_SIZE = "cchcc.learn.amu:ChangeTextSize:textsize"

        private val TEXT_SIZE_PROPERTY = object : Property<TextView, Float>(Float::class.java, "textSize") {
            override fun get(textView: TextView): Float = textView.textSize

            override fun set(textView: TextView, textSizePixels: Float) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePixels)
            }
        }
    }
}