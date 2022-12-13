package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuratTanggungJawab(var id: Long, var no_surat: String, var tanggal_surat: String,
                              var status_pemberi: Int, var status_penerima: Int,
                              var status_aktif_surat: Boolean, var kendaraan: Array<Kendaraan>,
                              var pemberi: Pegawai, var penerima: Pegawai, var admin: Pegawai): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SuratTanggungJawab

        if (id != other.id) return false
        if (no_surat != other.no_surat) return false
        if (tanggal_surat != other.tanggal_surat) return false
        if (status_pemberi != other.status_pemberi) return false
        if (status_penerima != other.status_penerima) return false
        if (status_aktif_surat != other.status_aktif_surat) return false
        if (!kendaraan.contentEquals(other.kendaraan)) return false
        if (pemberi != other.pemberi) return false
        if (penerima != other.penerima) return false
        if (admin != other.admin) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + no_surat.hashCode()
        result = 31 * result + tanggal_surat.hashCode()
        result = 31 * result + status_pemberi
        result = 31 * result + status_penerima
        result = 31 * result + status_aktif_surat.hashCode()
        result = 31 * result + kendaraan.contentHashCode()
        result = 31 * result + pemberi.hashCode()
        result = 31 * result + penerima.hashCode()
        result = 31 * result + admin.hashCode()
        return result
    }
}