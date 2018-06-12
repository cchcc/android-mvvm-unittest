package cchcc.learn.amu.e02a

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.FragmentE02aBinding
import cchcc.learn.amu.e02a.di.E02aFragmentModule
import kotlinx.android.synthetic.main.fragment_e02a.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class E02aFragment : Fragment(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(E02aFragmentModule)
    }

    private val viewModel: E02aViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.result.observe(this, Observer<E02aViewModel.TryResult> {
            lav_result.setAnimation(when (it) {
                E02aViewModel.TryResult.FAILED -> R.raw.e02_failed
                E02aViewModel.TryResult.SUCCESS -> R.raw.e02_succes
                else -> throw IllegalStateException()
            })
            lav_result.playAnimation()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentE02aBinding>(inflater, R.layout.fragment_e02a, container, false).also {
                it.setLifecycleOwner(this)
                it.host = this
                it.viewModel = viewModel
            }.root

    fun onClickTry() {
        viewModel.tryResult()
    }

    fun onAnimationEnd() {
        viewModel.applyScore()
    }

    fun onClickClear() {
        lav_result.cancelAnimation()
        lav_result.progress = 0.0f
        viewModel.clear()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                E02aFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}