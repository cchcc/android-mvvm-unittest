package cchcc.learn.amu.e02

import android.animation.Animator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.FragmentE02Binding
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.fragment_e02.*

class E02Fragment : Fragment() {

    private val viewModel: E02ViewModel by lazy { ViewModelProviders.of(this).get(E02ViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentE02Binding>(inflater, R.layout.fragment_e02, container, false).also {
                it.setLifecycleOwner(this)
                it.host = this
                it.viewModel = viewModel
            }.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.result.observe(this, Observer<E02ViewModel.TryResult> {
            lav_state.setAnimation(when (it) {
                E02ViewModel.TryResult.FAILED -> R.raw.e02_failed
                E02ViewModel.TryResult.SUCCESS -> R.raw.e02_succes
                else -> throw IllegalStateException()
            })
            lav_state.playAnimation()
        })
    }

    fun onClickTry() {
        viewModel.tryResult()
    }

    fun onAnimationEnd() {
        val score = when (viewModel.result.value) {
            E02ViewModel.TryResult.FAILED -> -1
            E02ViewModel.TryResult.SUCCESS -> 1
            else -> throw IllegalStateException()
        }

        viewModel.score.value = viewModel.score.value!! + score
    }

    fun onClickClear() {
        lav_state.cancelAnimation()
        lav_state.progress = 0.0f
        viewModel.score.value = 0
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                E02Fragment().apply {
//                    arguments = Bundle().apply {
//                        putString("key", param1)
//                    }
                }
    }
}

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