package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuratPerintahTugas(var id: Long, var no_surat: String, var tujuan_pemakaian: String,
                              var tanggal_mulai: String, var tanggal_selesai: String,
                              var surat_disposisi: String, var status_pemberi_tugas: Int,
                              var status_kasubagTU: Int, var status_aktif_surat: Boolean,
                              var dasar: Dasar, var kasubagTU: Pegawai, var pemberi_tugas: Pegawai,
                              var kendaraan: Kendaraan, var staf: Array<Pegawai>): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SuratPerintahTugas

        if (id != other.id) return false
        if (no_surat != other.no_surat) return false
        if (tujuan_pemakaian != other.tujuan_pemakaian) return false
        if (tanggal_mulai != other.tanggal_mulai) return false
        if (tanggal_selesai != other.tanggal_selesai) return false
        if (surat_disposisi != other.surat_disposisi) return false
        if (status_pemberi_tugas != other.status_pemberi_tugas) return false
        if (status_kasubagTU != other.status_kasubagTU) return false
        if (status_aktif_surat != other.status_aktif_surat) return false
        if (dasar != other.dasar) return false
        if (kasubagTU != other.kasubagTU) return false
        if (pemberi_tugas != other.pemberi_tugas) return false
        if (kendaraan != other.kendaraan) return false
        if (!staf.contentEquals(other.staf)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + no_surat.hashCode()
        result = 31 * result + tujuan_pemakaian.hashCode()
        result = 31 * result + tanggal_mulai.hashCode()
        result = 31 * result + tanggal_selesai.hashCode()
        result = 31 * result + surat_disposisi.hashCode()
        result = 31 * result + status_pemberi_tugas
        result = 31 * result + status_kasubagTU
        result = 31 * result + status_aktif_surat.hashCode()
        result = 31 * result + dasar.hashCode()
        result = 31 * result + kasubagTU.hashCode()
        result = 31 * result + pemberi_tugas.hashCode()
        result = 31 * result + kendaraan.hashCode()
        result = 31 * result + staf.contentHashCode()
        return result
    }
}