package cchcc.learn.amu.e05

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import cchcc.learn.amu.R
import cchcc.learn.amu.databinding.FragmentE05Binding
import cchcc.learn.amu.e02.E02ViewModel
import kotlinx.android.synthetic.main.fragment_e05.*
import java.io.Serializable

class E05Fragment : Fragment() {

    private val viewModel: E05ViewModel by lazy { ViewModelProviders.of(this).get(E05ViewModel::class.java) }
    private val adapter by lazy {
        SimpleAdapter(context, viewModel.logList.value!!
                , R.layout.listitem_e05, arrayOf("line"), intArrayOf(R.id.tv_line))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val e02ViewModel: E02ViewModel by lazy {
            @Suppress("UNCHECKED_CAST")
            val getE02ViewModel = arguments?.getSerializable("getE02ViewModel") as? () -> E02ViewModel
                    ?: throw IllegalArgumentException("no getE02ViewModel for ${this::class.java.simpleName}\"")
            getE02ViewModel()
        }

        e02ViewModel.cleared.observe(this, Observer {
            viewModel.clear()
            adapter.notifyDataSetChanged()
        })
        e02ViewModel.result.observe(this, Observer {
            viewModel.add("try result : $it")
            adapter.notifyDataSetChanged()
            lv_log.setSelection(adapter.count-1)
        })
        e02ViewModel.score.observe(this, Observer {
            viewModel.add("score : $it")
            adapter.notifyDataSetChanged()
            lv_log.setSelection(adapter.count-1)
        })

        viewModel.animSpeed.observe(this, Observer {
            e02ViewModel.animSpeed.value = it!!.toFloat()
            viewModel.add("set speed  ✕ $it")
            adapter.notifyDataSetChanged()
            lv_log.setSelection(adapter.count-1)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = DataBindingUtil.inflate<FragmentE05Binding>(inflater, R.layout.fragment_e05, container, false).also {
        it.setLifecycleOwner(this)
        it.viewModel = viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lv_log.adapter = adapter
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
