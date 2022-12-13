package com.example.simpkd.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.simpkd.R
import com.example.simpkd.adapters.LetterOfTaskAdapter
import com.example.simpkd.apis.getProfile
import com.example.simpkd.apis.getSuratPerintahTugas
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.viewModels.LetterOfTaskViewModel
import kotlinx.android.synthetic.main.activity_letter_of_task.*
import kotlinx.android.synthetic.main.layout_vertical_recycler_view.*

class LetterOfTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: LetterOfTaskViewModel
    private val detailLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.suratPerintahTugass.value = listOf()
            getSuratPerintahTugas(root, HashMap(), HashMap(), this::onSuccessSuratPerintahTugas, {},
                showMessage = false)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessSuratPerintahTugas(response: Any?) {
        if (response != null) {
            viewModel.suratPerintahTugass.value = (response as Array<SuratPerintahTugas>).filter {
                it.status_aktif_surat && (viewModel.me.value!!.id == it.kasubagTU.id ||
                        viewModel.me.value!!.id == it.pemberi_tugas.id ||
                        it.staf.any { staf -> staf.id == viewModel.me.value!!.id })
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessProfile(response: Any?) {
        if (response != null) {
            viewModel.me.value = response as Pegawai
            getSuratPerintahTugas(root, HashMap(), HashMap(), this::onSuccessSuratPerintahTugas, {},
                showMessage = false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_of_task)
        viewModel = ViewModelProvider(this)[LetterOfTaskViewModel::class.java]

        btn_back.setOnClickListener { finish() }
        btn_add.setOnClickListener {
            val intent = Intent(this, FormLetterOfTaskActivity::class.java)
            startActivity(intent)
        }

        viewModel.suratPerintahTugass.observe(this, {
            val layoutParams = layout_vertical_container.layoutParams as
                    ConstraintLayout.LayoutParams

            if (it.isEmpty()) {
                layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID

                root.gravity = Gravity.CENTER
                recycler_view.visibility = View.GONE
                empty_container.visibility = View.VISIBLE
            } else {
                layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                root.gravity = Gravity.NO_GRAVITY
                empty_container.visibility = View.GONE
                recycler_view.visibility = View.VISIBLE
                recycler_view.adapter = LetterOfTaskAdapter(this, it, viewModel,
                    detailLauncher)
            }
        })

        getProfile(root, HashMap(), HashMap(), this::onSuccessProfile, {}, showMessage = false)
    }
}