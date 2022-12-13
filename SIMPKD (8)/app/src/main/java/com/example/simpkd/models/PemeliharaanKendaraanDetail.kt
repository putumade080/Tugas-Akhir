package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PemeliharaanKendaraanDetail(var kendaraan: Kendaraan, var jenis_kerusakan: String,
                                       var jumlah: Int, var keterangan: String): Parcelable