package cchcc.learn.amu.e02

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.FragmentE02Binding
import kotlinx.android.synthetic.main.fragment_e02.*
import java.io.Serializable

class E02Fragment : Fragment() {

    private val createVMFactory: () -> ViewModelProvider.Factory by lazy {
        @Suppress("UNCHECKED_CAST")
        arguments?.getSerializable("createVMFactory") as? () -> ViewModelProvider.Factory
                ?: throw IllegalArgumentException("no createVMFactory for ${this::class.java.simpleName}")
    }
    val viewModel: E02ViewModel by lazy { ViewModelProviders.of(this, createVMFactory()).get(E02ViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.result.observe(this, Observer {
            lav_result.setAnimation(when (it) {
                E02ViewModel.TryResult.FAILED -> R.raw.e02_failed
                E02ViewModel.TryResult.SUCCESS -> R.raw.e02_succes
                else -> throw IllegalStateException()
            })
            lav_result.playAnimation()
        })

        viewModel.cleared.observe(this, Observer {
            lav_result.cancelAnimation()
            lav_result.progress = 0.0f
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentE02Binding>(inflater, R.layout.fragment_e02, container, false).also {
                it.setLifecycleOwner(this)
                it.viewModel = viewModel
            }.root

    companion object {

        @JvmStatic
        fun newInstance(createVMFactory: () -> ViewModelProvider.Factory = ::E02ViewModelFactory) =
                E02Fragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("createVMFactory", createVMFactory as Serializable)
                    }
                }
    }
}