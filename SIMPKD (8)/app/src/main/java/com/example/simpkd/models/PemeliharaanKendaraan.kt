package com.example.simpkd.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PemeliharaanKendaraan(var id: Long, var nomor_surat: String, var tanggal: String,
                                 var status_kepalaUPT: Int, var status_kasubagTU: Int,
                                 var status_pptk: Int, var status_aktif_surat: Boolean,
                                 var kepalaUPT: Pegawai, var kasubagTU: Pegawai, var pptk: Pegawai,
                                 var staf: Pegawai, var detail: Array<PemeliharaanKendaraanDetail>): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PemeliharaanKendaraan

        if (id != other.id) return false
        if (nomor_surat != other.nomor_surat) return false
        if (tanggal != other.tanggal) return false
        if (status_kepalaUPT != other.status_kepalaUPT) return false
        if (status_kasubagTU != other.status_kasubagTU) return false
        if (status_pptk != other.status_pptk) return false
        if (status_aktif_surat != other.status_aktif_surat) return false
        if (kepalaUPT != other.kepalaUPT) return false
        if (kasubagTU != other.kasubagTU) return false
        if (pptk != other.pptk) return false
        if (staf != other.staf) return false
        if (!detail.contentEquals(other.detail)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nomor_surat.hashCode()
        result = 31 * result + tanggal.hashCode()
        result = 31 * result + status_kepalaUPT
        result = 31 * result + status_kasubagTU
        result = 31 * result + status_pptk
        result = 31 * result + status_aktif_surat.hashCode()
        result = 31 * result + kepalaUPT.hashCode()
        result = 31 * result + kasubagTU.hashCode()
        result = 31 * result + pptk.hashCode()
        result = 31 * result + staf.hashCode()
        result = 31 * result + detail.contentHashCode()
        return result
    }
}