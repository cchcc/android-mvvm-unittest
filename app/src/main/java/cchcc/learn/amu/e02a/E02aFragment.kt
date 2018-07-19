package cchcc.learn.amu.e02a

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.FragmentE02Binding
import cchcc.learn.amu.e02.E02ViewModel
import cchcc.learn.amu.e02a.di.E02aFragmentModule
import kotlinx.android.synthetic.main.fragment_e02.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.io.Serializable

class E02aFragment : Fragment(), KodeinAware {

    // to getting kodein is usually done by closestKodein() that is declared dependencies from parent(Activity, Application) layer.
    override val kodein: Kodein by lazy {
        @Suppress("UNCHECKED_CAST")
        val createKodein = arguments?.getSerializable("createKodein") as? () -> Kodein
                ?: throw IllegalArgumentException("no createKodein for ${this::class.java.simpleName}")

        createKodein()
    }

    private val viewModel: E02ViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.result.observe(this, Observer<E02ViewModel.TryResult> {
            lav_result.setAnimation(when (it) {
                E02ViewModel.TryResult.FAILED -> R.raw.e02_failed
                E02ViewModel.TryResult.SUCCESS -> R.raw.e02_succes
                else -> throw IllegalStateException()
            })
            lav_result.playAnimation()
        })

        viewModel.clearAction.observe(this, Observer {
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

        private val _createKodein: () -> Kodein by lazy {
            {
                Kodein.lazy { import(E02aFragmentModule) }
            }
        }

        @JvmStatic
        fun newInstance(createKodein: () -> Kodein = _createKodein) =
                E02aFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("createKodein", createKodein as Serializable)
                    }
                }
    }
}

