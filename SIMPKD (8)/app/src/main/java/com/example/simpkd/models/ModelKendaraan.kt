package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelKendaraan(var id: Long, var tipe: String, var merk: String,
                          var tahun_pembuatan: String, var isi_silinder: Int, var jumlah_roda: Int,
                          var status_aktif: Boolean): Parcelable