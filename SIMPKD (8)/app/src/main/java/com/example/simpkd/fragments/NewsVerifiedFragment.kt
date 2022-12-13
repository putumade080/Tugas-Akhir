package com.example.simpkd.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.simpkd.R
import com.example.simpkd.adapters.NewsAdapter
import com.example.simpkd.viewModels.NewsViewModel
import kotlinx.android.synthetic.main.layout_vertical_recycler_view.view.*

class NewsVerifiedFragment: Fragment() {
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.layout_vertical_recycler_view, container,
            false)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        viewModel.beritaAcaras.observe(requireActivity(), {
            val filteredData = it.filter { v ->
                if (viewModel.me.value!!.role == "Kasubag TU") {
                    v.status_kasubag_tu > 0
                } else {
                    v.status_pegawai > 0
                }
            }

            if (filteredData.isEmpty()) {
                view.root.gravity = Gravity.CENTER
                view.recycler_view.visibility = View.GONE
                view.empty_container.visibility = View.VISIBLE
            } else {
                view.root.gravity = Gravity.NO_GRAVITY
                view.empty_container.visibility = View.GONE
                view.recycler_view.visibility = View.VISIBLE
                view.recycler_view.adapter = NewsAdapter(requireContext(), filteredData, viewModel,
                    null)
            }
        })

        return view
    }
}