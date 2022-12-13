package com.example.simpkd.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpkd.models.*

class MainViewModel: ViewModel() {
    val beritaAcaras = MutableLiveData<List<BeritaAcara>?>(null)
    val suratTanggungJawabs = MutableLiveData<List<SuratTanggungJawab>?>(null)
    val pemeliharaanKendaraans = MutableLiveData<List<PemeliharaanKendaraan>?>(null)
    val suratPerintahTugass = MutableLiveData<List<SuratPerintahTugas>?>(null)
    val me = MutableLiveData<Pegawai>()
    val refetchProfile = MutableLiveData(true)
}