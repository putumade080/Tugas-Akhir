package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dasar(var id: Long, var dasar: String, var tanggal: String,
                 var status_aktif: Boolean): Parcelable