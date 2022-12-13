package com.example.simpkd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.simpkd.R
import com.example.simpkd.adapters.LetterOfResponsibilityViewPagerAdapter
import com.example.simpkd.apis.getProfile
import com.example.simpkd.apis.getSuratTanggungJawab
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.SuratTanggungJawab
import com.example.simpkd.viewModels.LetterViewModel
import kotlinx.android.synthetic.main.activity_letter_of_responsibility.*

class LetterOfResponsibilityActivity : AppCompatActivity() {
    private lateinit var viewModel: LetterViewModel

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessSuratTanggungJawab(response: Any?) {
        if (response != null) {
            viewModel.suratTanggungJawabs.value = (response as Array<SuratTanggungJawab>).filter {
                it.status_aktif_surat && (viewModel.me.value!!.id == it.pemberi.id ||
                        viewModel.me.value!!.id == it.penerima.id)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessProfile(response: Any?) {
        if (response != null) {
            viewModel.me.value = response as Pegawai
            getSuratTanggungJawab(root, HashMap(), HashMap(), this::onSuccessSuratTanggungJawab, {},
                showMessage = false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_of_responsibility)
        viewModel = ViewModelProvider(this)[LetterViewModel::class.java]

        view_pager.adapter = LetterOfResponsibilityViewPagerAdapter(supportFragmentManager)
        tab.setupWithViewPager(view_pager)

        viewModel.suratTanggungJawabs.observe(this, {
            if (viewModel.me.value != null && it.isEmpty()) {
                getSuratTanggungJawab(root, HashMap(), HashMap(), this::onSuccessSuratTanggungJawab, {},
                    showMessage = false)
            }
        })

        btn_back.setOnClickListener { finish() }

        getProfile(root, HashMap(), HashMap(), this::onSuccessProfile, {}, showMessage = false)
    }
}