package com.example.simpkd.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.models.SuratTanggungJawab

class LetterOfTaskViewModel: ViewModel() {
    val me = MutableLiveData<Pegawai>()
    val suratPerintahTugass = MutableLiveData<List<SuratPerintahTugas>>(listOf())
}