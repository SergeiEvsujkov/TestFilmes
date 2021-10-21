package com.coffeemolkin.testfilmes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.coffeemolkin.testfilmes.App
import com.coffeemolkin.testfilmes.adapter.RvAdapter
import com.coffeemolkin.testfilmes.data.FilmsRepo
import com.coffeemolkin.testfilmes.databinding.FragmentFilmsBinding
import com.coffeemolkin.testfilmes.images.GlideImageLoader
import com.coffeemolkin.testfilmes.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class FilmsFragment : MvpAppCompatFragment(), FilmsView, BackButtonListener {

    private var vb: FragmentFilmsBinding? = null

    private val presenter by moxyPresenter { FilmsPresenter(FilmsRepo(), App.instance.router) }

    private val adapter by lazy {
        RvAdapter(
            presenter.repoListPresenter,
            GlideImageLoader()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFilmsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        val mGridLayoutManager = GridLayoutManager(requireContext(), 2)

        val onSpanSizeLookup: SpanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.getItemViewType(position) == 2) 1 else 2
            }
        }
        mGridLayoutManager.spanSizeLookup = onSpanSizeLookup

        vb?.recyclerView?.layoutManager = mGridLayoutManager
        vb?.recyclerView?.adapter = adapter

    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }


}