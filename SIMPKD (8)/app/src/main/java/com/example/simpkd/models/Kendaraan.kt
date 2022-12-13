package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kendaraan(var id: Long, var model: ModelKendaraan, var no_stnk: String,
                     var no_polisi: String, var no_bpkb: String, var no_mesin: String,
                     var no_rangka: String, var no_register_barang: String, var warna: String,
                     var harga_perolehan: Int, var tgl_samsat_pertama: String,
                     var status_aktif: Boolean, var status_pemakaian: Boolean): Parcelable {
    override fun toString(): String {
        return "$no_polisi - ${model.merk}"
    }
 }