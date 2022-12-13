package com.example.simpkd.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.simpkd.R
import com.example.simpkd.adapters.LetterAdapter
import com.example.simpkd.viewModels.LetterViewModel
import kotlinx.android.synthetic.main.layout_vertical_recycler_view.view.*

class LetterVerifiedFragment: Fragment() {
    private lateinit var viewModel: LetterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_vertical_recycler_view, container,
            false)
        viewModel = ViewModelProvider(requireActivity())[LetterViewModel::class.java]

        viewModel.suratTanggungJawabs.observe(requireActivity(), {
            val filteredData = it.filter { v ->
                if (viewModel.me.value!!.id == v.pemberi.id) {
                    v.status_pemberi > 0
                } else {
                    v.status_penerima > 0
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
                view.recycler_view.adapter = LetterAdapter(requireContext(), filteredData, viewModel,
                    null)
            }
        })

        return view
    }
}