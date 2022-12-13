package com.example.simpkd.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpkd.models.PemeliharaanKendaraanDetail

class VehicleMaintenanceDetailViewModel: ViewModel() {
    val id = MutableLiveData<Long>(0)
    val letterNumber = MutableLiveData("")
    val letterDate = MutableLiveData("")
    val vehicleDetails = MutableLiveData<List<PemeliharaanKendaraanDetail>>(listOf())
}