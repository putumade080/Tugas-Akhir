package com.example.simpkd.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.simpkd.R
import com.example.simpkd.activities.LetterOfResponsibilityActivity
import com.example.simpkd.activities.LetterOfTaskActivity
import com.example.simpkd.activities.NewsActivity
import com.example.simpkd.activities.VehicleMaintenanceActivity
import com.example.simpkd.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardFragment: Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.me.observe(requireActivity(), {
            Glide.with(view).load(it.foto_profil).centerCrop().into(view.avatar)
            view.name.text = it.nama
            view.position.text = it.jabatan

            view.news.visibility = if (it.role in listOf("Kasubag TU", "Staf", "Kepala UPT"))
                View.VISIBLE else View.GONE
            view.letter_of_responsibility.visibility = if (it.role !in listOf("Admin", "Super Admin"))
                View.VISIBLE else View.GONE
            view.vehicle_maintenance.visibility = if (it.role !in listOf("Admin", "Super Admin"))
                View.VISIBLE else View.GONE
            view.letter_of_task.visibility = if (it.role !in listOf("Admin", "Super Admin"))
                View.VISIBLE else View.GONE
        })

        view.news.setOnClickListener {
            val intent = Intent(requireContext(), NewsActivity::class.java)
            requireActivity().startActivity(intent)
        }

        view.letter_of_responsibility.setOnClickListener {
            val intent = Intent(requireContext(), LetterOfResponsibilityActivity::class.java)
            requireActivity().startActivity(intent)
        }

        view.vehicle_maintenance.setOnClickListener {
            val intent = Intent(requireContext(), VehicleMaintenanceActivity::class.java)
            requireActivity().startActivity(intent)
        }

        view.letter_of_task.setOnClickListener {
            val intent = Intent(requireContext(), LetterOfTaskActivity::class.java)
            requireActivity().startActivity(intent)
        }

        return view
    }
}