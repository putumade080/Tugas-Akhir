package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BeritaAcara(var id: Long, var no_surat: Array<String>, var tanggal_surat: String,
                       var status_pegawai: Int, var status_kasubag_tu: Int,
                       var status_aktif_surat: Boolean, var kendaraan: Kendaraan,
                       var kasubag_tu: Pegawai, var pegawai: Pegawai, var admin: Pegawai): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BeritaAcara

        if (id != other.id) return false
        if (!no_surat.contentEquals(other.no_surat)) return false
        if (tanggal_surat != other.tanggal_surat) return false
        if (status_pegawai != other.status_pegawai) return false
        if (status_kasubag_tu != other.status_kasubag_tu) return false
        if (status_aktif_surat != other.status_aktif_surat) return false
        if (kendaraan != other.kendaraan) return false
        if (kasubag_tu != other.kasubag_tu) return false
        if (pegawai != other.pegawai) return false
        if (admin != other.admin) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + no_surat.contentHashCode()
        result = 31 * result + tanggal_surat.hashCode()
        result = 31 * result + status_pegawai
        result = 31 * result + status_kasubag_tu
        result = 31 * result + status_aktif_surat.hashCode()
        result = 31 * result + kendaraan.hashCode()
        result = 31 * result + kasubag_tu.hashCode()
        result = 31 * result + pegawai.hashCode()
        result = 31 * result + admin.hashCode()
        return result
    }
}