package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pegawai(var id: Long, var nip: String?, var nik: String, var nama: String,
                   var pangkat: String?, var golongan: String?, var jenis_kelamin: String,
                   var jabatan: String, var no_hp: String, var alamat: String, var role: String,
                   var email: String, var foto_profil: String, var status_ubah_password: Boolean,
                   var status_aktif: Boolean): Parcelable {
    override fun toString(): String {
        return "$nama - $jabatan"
    }
}
