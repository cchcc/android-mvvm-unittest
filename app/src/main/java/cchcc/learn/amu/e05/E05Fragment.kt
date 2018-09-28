package cchcc.learn.amu.e05

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.FragmentE05Binding
import cchcc.learn.amu.e02.E02ViewModel
import kotlinx.android.synthetic.main.fragment_e05.*
import java.io.Serializable

class E05Fragment : androidx.fragment.app.Fragment() {

    private val viewModel: E05ViewModel by lazy { ViewModelProviders.of(this).get(E05ViewModel::class.java) }
    private val adapter = E05LogListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.logList.observe(this, Observer {
            adapter.submitList(it)
        })

        val e02ViewModel: E02ViewModel by lazy {
            @Suppress("UNCHECKED_CAST")
            val getE02ViewModel = arguments?.getSerializable("getE02ViewModel") as? () -> E02ViewModel
                    ?: throw IllegalArgumentException("no getE02ViewModel for ${this::class.java.simpleName}\"")
            getE02ViewModel()
        }

        e02ViewModel.clearAction.observe(this, Observer {
            viewModel.clear()
        })

        fun <T : Any> createObserverForAddingLine(prefix: String): Observer<T> = Observer {
            viewModel.add("$prefix${it.toString()}")
            adapter.notifyItemInserted(adapter.itemCount - 1)
            rcv_log.scrollToPosition(adapter.itemCount - 1)
        }

        e02ViewModel.result.observe(this, createObserverForAddingLine("try result : "))
        e02ViewModel.score.observe(this, createObserverForAddingLine("score : "))

        val setSpeedObserver = createObserverForAddingLine<Int>("set speed  ✕ ")
        viewModel.animSpeed.observe(this, Observer {
            e02ViewModel.speedOfAnim.value = it!!.toFloat()
            setSpeedObserver.onChanged(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = DataBindingUtil.inflate<FragmentE05Binding>(inflater, R.layout.fragment_e05, container, false).also {
        it.setLifecycleOwner(this)
        it.viewModel = viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcv_log.adapter = adapter
        rcv_log.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(getE02ViewModel: () -> E02ViewModel) =
                E05Fragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("getE02ViewModel", getE02ViewModel as Serializable)
                    }
                }
    }
}
