package com.example.simpkd.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.SuratTanggungJawab

class LetterViewModel: ViewModel() {
    val me = MutableLiveData<Pegawai>()
    val suratTanggungJawabs = MutableLiveData<List<SuratTanggungJawab>>(listOf())
}