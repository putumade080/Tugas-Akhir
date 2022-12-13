package com.example.simpkd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.simpkd.R
import com.example.simpkd.adapters.NewsViewPagerAdapter
import com.example.simpkd.apis.getBeritaAcara
import com.example.simpkd.apis.getProfile
import com.example.simpkd.models.BeritaAcara
import com.example.simpkd.models.Pegawai
import com.example.simpkd.viewModels.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessBeritaAcara(response: Any?) {
        if (response != null) {
            viewModel.beritaAcaras.value = (response as Array<BeritaAcara>).filter {
                if (viewModel.me.value!!.id == it.kasubag_tu.id) {
                    it.kasubag_tu.id == viewModel.me.value!!.id && it.status_aktif_surat
                } else {
                    it.pegawai.id == viewModel.me.value!!.id && it.status_aktif_surat
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessProfile(response: Any?) {
        if (response != null) {
            viewModel.me.value = response as Pegawai
            getBeritaAcara(root, HashMap(), HashMap(), this::onSuccessBeritaAcara, {},
                showMessage = false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        view_pager.adapter = NewsViewPagerAdapter(supportFragmentManager)
        tab.setupWithViewPager(view_pager)

        viewModel.beritaAcaras.observe(this, {
            if (viewModel.me.value != null && it.isEmpty()) {
                getBeritaAcara(root, HashMap(), HashMap(), this::onSuccessBeritaAcara, {},
                    showMessage = false)
            }
        })

        btn_back.setOnClickListener { finish() }

        getProfile(root, HashMap(), HashMap(), this::onSuccessProfile, {}, showMessage = false)
    }
}