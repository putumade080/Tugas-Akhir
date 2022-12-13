package com.example.simpkd.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.PemeliharaanKendaraan

class VehicleMaintenanceViewModel: ViewModel() {
    val me = MutableLiveData<Pegawai>()
    val pemeliharaanKendaraans = MutableLiveData<List<PemeliharaanKendaraan>>(listOf())
}