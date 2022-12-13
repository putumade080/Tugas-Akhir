package com.example.simpkd.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.models.Pegawai
import kotlinx.android.synthetic.main.layout_detail_item_task_receiver.view.*

class DetailTaskReceiverViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {
    fun onBindItem(receiver: Pegawai) {
        view.receiver_name.text = receiver.nama
        view.receiver_nik.text = receiver.nik
        view.receiver_position.text = receiver.jabatan
        view.receiver_address.text = receiver.alamat
        view.receiver_phone.text = receiver.no_hp
    }
}