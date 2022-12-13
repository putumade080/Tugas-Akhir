package com.example.simpkd.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpkd.models.BeritaAcara
import com.example.simpkd.models.Pegawai

class NewsViewModel: ViewModel() {
    val me = MutableLiveData<Pegawai>()
    val beritaAcaras = MutableLiveData<List<BeritaAcara>>(listOf())
}