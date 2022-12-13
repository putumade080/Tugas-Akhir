package com.example.simpkd.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpkd.models.Kendaraan
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.models.SuratTanggungJawab
import com.example.simpkd.utils.FileInfo

class FormLetterOfTaskViewModel: ViewModel() {
    val letterNumber = MutableLiveData("")
    val startDate = MutableLiveData("")
    val endDate = MutableLiveData("")
    val giverTask = MutableLiveData<Long>(0)
    val vehicle = MutableLiveData<Long>(0)
    val taskLetterInfo = MutableLiveData<FileInfo>()
    val taskLetter = MutableLiveData<ByteArray>()
    val me = MutableLiveData<Pegawai>()
    val pegawais = MutableLiveData<List<Pegawai>>(listOf())
    val kendaraans = MutableLiveData<List<Kendaraan>>(listOf())
}