package com.example.simpkd.fragments

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.simpkd.R
import com.example.simpkd.adapters.StringArrayAdapter
import com.example.simpkd.apis.updateProfile
import com.example.simpkd.services.FileDataPart
import com.example.simpkd.utils.choosePhoto
import com.example.simpkd.utils.isEmail
import com.example.simpkd.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap

class ProfileFragment:Fragment() {
    private lateinit var viewModel: MainViewModel
    private var avatar: ByteArray? = null
    private val avatarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK && it.data != null) {
            if (it.data!!.data != null) {
                val uri = it.data!!.data
                avatar =
                    requireView().context.contentResolver
                        .openInputStream(uri!!)?.buffered()?.use { bis ->
                            bis.readBytes()
                        }
                Glide.with(requireView().context).load(uri).centerCrop().into(requireView().avatar)
                return@registerForActivityResult
            }

            val bitmap = it.data!!.extras!!.get("data") as Bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            avatar = stream.toByteArray()
            Glide.with(requireView().context).load(bitmap).centerCrop().into(requireView().avatar)
        }
    }

    private fun onSuccessSave(response: Any?) {
        if (response == null) {
            viewModel.refetchProfile.value = true
            requireView().btn_save.submitFinish()
        }
    }

    private fun onSave() {
        requireView().btn_save.submit()

        val name = "${requireView().name_edit_text.text}"
        val gender = "${requireView().gender_autocomplete.text}"
        val email = "${requireView().email_edit_text.text}"
        val phone = "${requireView().phone_edit_text.text}"
        val address = "${requireView().address_edit_text.text}"

        requireView().name_input_layout.helperText = ""
        requireView().email_input_layout.helperText = ""
        requireView().phone_input_layout.helperText = ""
        requireView().address_input_layout.helperText = ""

        if (name.isBlank()) {
            requireView().name_input_layout.apply {
                helperText = "Nama tidak boleh kosong"
                requestFocus()
            }
            requireView().btn_save.submitFinish()
            return
        }

        if (email.isBlank() || !email.isEmail()) {
            requireView().email_input_layout.apply {
                helperText = "Email tidak valid"
                requestFocus()
            }
            requireView().btn_save.submitFinish()
            return
        }

        if (phone.isBlank() || phone.length < 10 || phone.length > 13) {
            requireView().email_input_layout.apply {
                helperText = "Nomor telepon harus 10-13 digit angka"
                requestFocus()
            }
            requireView().btn_save.submitFinish()
            return
        }

        if (address.isBlank()) {
            requireView().address_input_layout.apply {
                helperText = "Alamat tidak boleh kosong"
                requestFocus()
            }
            requireView().btn_save.submitFinish()
            return
        }

        val requestParams = HashMap<String, String>()
        requestParams["nama"] = name
        requestParams["jenis_kelamin"] = if (gender == "Laki-laki") "L" else "P"
        requestParams["email"] = email
        requestParams["no_hp"] = phone
        requestParams["alamat"] = address

        val fileRequestParams = HashMap<String, FileDataPart>()

        if (avatar != null) {
            fileRequestParams["foto_profil"] = FileDataPart(UUID.randomUUID().toString() +
                    ".jpeg", avatar!!, "image/jpeg")
        }

        updateProfile(requireView(), requestParams, fileRequestParams, this::onSuccessSave, {})
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        view.gender_autocomplete.setAdapter(StringArrayAdapter(view.context,
            arrayListOf("Laki-laki", "Perempuan")))

        view.btn_avatar.setOnClickListener { avatarLauncher.launch(choosePhoto()) }
        view.btn_save.setOnClickListener { onSave() }

        viewModel.me.observe(requireActivity(), {
            Glide.with(view).load(it.foto_profil).centerCrop().into(view.avatar)
            view.name_edit_text.setText(it.nama)
            view.gender_autocomplete.setText(if (it.jenis_kelamin == "L") "Laki-laki" else
                "Perempuan")
            view.nip_edit_text.setText(it.nip)
            view.position_edit_text.setText(it.jabatan)
            view.email_edit_text.setText(it.email)
            view.phone_edit_text.setText(it.no_hp)
            view.address_edit_text.setText(it.alamat)
        })

        return view
    }
}