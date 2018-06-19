package cchcc.learn.amu.e02.binding

import android.animation.Animator
import android.databinding.BindingAdapter
import android.util.Log
import com.airbnb.lottie.LottieAnimationView

interface LottieOnAnimationEnd {
    fun onAnimationEnd(v: LottieAnimationView)
}

@BindingAdapter("onAnimationEnd")
fun bind_onAnimationEnd(v: LottieAnimationView, listener: LottieOnAnimationEnd) {
    Log.d("bind_onAnimationEnd", "binding")

    v.addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) = Unit

        override fun onAnimationEnd(animation: Animator?) {
            listener.onAnimationEnd(v)
        }

        override fun onAnimationCancel(animation: Animator?) = Unit

        override fun onAnimationStart(animation: Animator?) = Unit

    })

}