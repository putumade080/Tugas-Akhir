package com.example.simpkd.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.simpkd.R
import com.example.simpkd.adapters.KendaraanArrayAdapter
import com.example.simpkd.adapters.PegawaiArrayAdapter
import com.example.simpkd.apis.*
import com.example.simpkd.models.Kendaraan
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.services.FileDataPart
import com.example.simpkd.utils.*
import com.example.simpkd.viewModels.FormLetterOfTaskViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_form_letter_of_task.*
import kotlinx.android.synthetic.main.activity_form_letter_of_task.root
import kotlinx.android.synthetic.main.layout_vertical_recycler_view.*
import java.util.*
import kotlin.collections.HashMap

class FormLetterOfTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: FormLetterOfTaskViewModel
    private var letterOfTasks: List<SuratPerintahTugas> = listOf()
    private val fileChooserLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK && it.data != null) {
            val fileInfo = getFileInfo(this, it.data!!.data)
            val fileByteArray = getFileByteArray(this, it.data!!)
            viewModel.taskLetter.value = fileByteArray

            if (fileInfo != null) {
                viewModel.taskLetterInfo.value = fileInfo
                task_letter.editText!!.setText(fileInfo.displayName)
                task_letter_img.visibility = View.GONE

                if (fileInfo.mimeType.startsWith("image")) {
                    Glide.with(this).load(fileByteArray).centerCrop().into(task_letter_img)
                    task_letter_img.visibility = View.VISIBLE
                }
            }
        }
    }
    private var letterOfTask: SuratPerintahTugas? = null
    private var resultCode = Activity.RESULT_CANCELED

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessSuratPerintahTugas(response: Any?) {
        if (response != null) {
            letterOfTasks = (response as Array<SuratPerintahTugas>).filter { it.status_aktif_surat }
        }
    }

    private fun onUpdate() {
        btn_save.submit()
        start_date.helperText = ""
        end_date.helperText = ""
        giver_task.helperText = ""
        vehicle.helperText = ""
        usage_purpose.helperText = ""

        if (viewModel.startDate.value!!.isBlank()) {
            start_date.apply {
                helperText = "Tanggal mulai tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (viewModel.endDate.value!!.isBlank()) {
            end_date.apply {
                helperText = "Tanggal selesai tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (viewModel.giverTask.value!! == (0).toLong()) {
            giver_task.apply {
                helperText = "Pemberi tugas tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (receiver_task.getChipIds().isEmpty()) {
            snackbarWarning(root, "Penerima tugas tidak boleh kosong",
                Snackbar.LENGTH_LONG).show()
            btn_save.submitFinish()
            return
        }

        if (!receiver_task.getChipIds().contains(viewModel.me.value!!.id)) {
            snackbarWarning(root, "Anda harus termasuk sebagai salah satu penerima tugasnya",
                Snackbar.LENGTH_LONG).show()
            btn_save.submitFinish()
            return
        }

        if (viewModel.vehicle.value!! == (0).toLong()) {
            vehicle.apply {
                helperText = "Kendaraan tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (usage_purpose_edit_text.text.isNullOrBlank()) {
            usage_purpose.apply {
                helperText = "Tujuan pemakaian tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        val requestParams = HashMap<String, String>()
        requestParams["id"] = "${letterOfTask!!.id}"
        requestParams["id_pemberi_tugas"] = "${viewModel.giverTask.value}"
        requestParams["id_staf"] = receiver_task.getChipIds().toString()
        requestParams["id_kendaraan"] = "${viewModel.vehicle.value}"
        requestParams["tujuan_pemakaian"] = "${usage_purpose_edit_text.text}"
        requestParams["tanggal_mulai"] = "${viewModel.startDate.value}"
        requestParams["tanggal_selesai"] = "${viewModel.endDate.value}"

        val fileRequestParams = HashMap<String, FileDataPart>()

        if (viewModel.taskLetter.value != null) {
            fileRequestParams["surat_disposisi"] = FileDataPart(
                "${UUID.randomUUID()}.${viewModel.taskLetterInfo.value!!.extension}",
                viewModel.taskLetter.value!!, viewModel.taskLetterInfo.value!!.mimeType
            )
        }

        updateSuratPerintahTugas(root, requestParams, fileRequestParams, {
            btn_save.submitFinish()
            resultCode = Activity.RESULT_OK }, { btn_save.submitFinish() })
    }

    private fun onSave() {
        btn_save.submit()
        start_date.helperText = ""
        end_date.helperText = ""
        giver_task.helperText = ""
        vehicle.helperText = ""
        usage_purpose.helperText = ""
        task_letter.helperText = ""

        if (viewModel.startDate.value!!.isBlank()) {
            start_date.apply {
                helperText = "Tanggal mulai tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (viewModel.endDate.value!!.isBlank()) {
            end_date.apply {
                helperText = "Tanggal selesai tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (viewModel.giverTask.value!! == (0).toLong()) {
            giver_task.apply {
                helperText = "Pemberi tugas tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (receiver_task.getChipIds().isEmpty()) {
            snackbarWarning(root, "Penerima tugas tidak boleh kosong",
                Snackbar.LENGTH_LONG).show()
            btn_save.submitFinish()
            return
        }

        if (!receiver_task.getChipIds().contains(viewModel.me.value!!.id)) {
            snackbarWarning(root, "Anda harus termasuk sebagai salah satu penerima tugasnya",
                Snackbar.LENGTH_LONG).show()
            btn_save.submitFinish()
            return
        }

        if (viewModel.vehicle.value!! == (0).toLong()) {
            vehicle.apply {
                helperText = "Kendaraan tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (usage_purpose_edit_text.text.isNullOrBlank()) {
            usage_purpose.apply {
                helperText = "Tujuan pemakaian tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        val requestParams = HashMap<String, String>()
        requestParams["id_pemberi_tugas"] = "${viewModel.giverTask.value}"
        requestParams["id_staf"] = receiver_task.getChipIds().toString()
        requestParams["id_kendaraan"] = "${viewModel.vehicle.value}"
        requestParams["no_surat"] = "${viewModel.letterNumber.value}"
        requestParams["tujuan_pemakaian"] = "${usage_purpose_edit_text.text}"
        requestParams["tanggal_mulai"] = "${viewModel.startDate.value}"
        requestParams["tanggal_selesai"] = "${viewModel.endDate.value}"

        val fileRequestParams = HashMap<String, FileDataPart>()

        if (viewModel.taskLetter.value != null) {
            fileRequestParams["surat_disposisi"] = FileDataPart(
                "${UUID.randomUUID()}.${viewModel.taskLetterInfo.value!!.extension}",
                viewModel.taskLetter.value!!, viewModel.taskLetterInfo.value!!.mimeType
            )
        }

        createSuratPerintahTugas(root, requestParams, fileRequestParams, {
            btn_save.submitFinish()
            resultCode = Activity.RESULT_OK }, { btn_save.submitFinish() })
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessKendaraan(response: Any?) {
        if (response != null) {
            viewModel.kendaraans.value = (response as Array<Kendaraan>).toList()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessPegawai(response: Any?) {
        if (response != null) {
            viewModel.pegawais.value = (response as Array<Pegawai>).toList()
        }
    }

    private fun onSuccessNomorSurat(response: Any?) {
        viewModel.letterNumber.value = "$response"
    }

    private fun onSuccessProfile(response: Any?) {
        viewModel.me.value = response as Pegawai
    }

    private fun onBack() {
        setResult(resultCode)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_letter_of_task)
        viewModel = ViewModelProvider(this)[FormLetterOfTaskViewModel::class.java]
        letterOfTask = intent.getParcelableExtra("letter")

        if (letterOfTask != null) {
            val title = "Edit Surat Perintah Tugas"
            val giverTask = "${letterOfTask!!.pemberi_tugas.nama} - ${letterOfTask!!.pemberi_tugas.jabatan}"
            val vehicle = "${letterOfTask!!.kendaraan.no_polisi} - ${letterOfTask!!.kendaraan.model.merk}"
            viewModel.startDate.value = letterOfTask!!.tanggal_mulai
            viewModel.endDate.value = letterOfTask!!.tanggal_selesai
            viewModel.giverTask.value = letterOfTask!!.pemberi_tugas.id
            viewModel.vehicle.value = letterOfTask!!.kendaraan.id

            titlebar.text = title
            start_date.editText!!.setText(letterOfTask!!.tanggal_mulai)
            end_date.editText!!.setText(letterOfTask!!.tanggal_selesai)
            usage_purpose_edit_text.setText(letterOfTask!!.tujuan_pemakaian)
            giver_task_autocomplete.setText(giverTask)
            vehicle_autocomplete.setText(vehicle)

            for (staf in letterOfTask!!.staf) {
                val receiverTask = "${staf.nama} - ${staf.jabatan}"
                receiver_task.addChip(receiverTask, staf.id)
            }
        }

        start_date.editText!!.setOnClickListener {
            showDatePicker(this, { y, m, d ->
                val date = "%d-%02d-%02d".format(y, m + 1, d)
                viewModel.startDate.value = date
                start_date.editText!!.setText(date)
                viewModel.vehicle.value = 0
                vehicle_autocomplete.setText("")
            })
        }

        end_date.editText!!.setOnClickListener {
            showDatePicker(this, { y, m, d ->
                val date = "%d-%02d-%02d".format(y, m + 1, d)
                viewModel.endDate.value = date
                end_date.editText!!.setText(date)
                viewModel.vehicle.value = 0
                vehicle_autocomplete.setText("")
            })
        }

        giver_task_autocomplete.setOnItemClickListener { _, _, _, id ->
            viewModel.giverTask.value = id }

        vehicle_autocomplete.setOnItemClickListener { _, _, _, id -> viewModel.vehicle.value = id }

        task_letter.editText!!.setOnClickListener {
            fileChooserLauncher.launch(chooseDocument())
        }

        viewModel.startDate.observe(this, { startDate ->
            run {
                if (viewModel.endDate.value.isNullOrBlank()) {
                    return@observe
                }

                vehicle_autocomplete.setAdapter(
                    KendaraanArrayAdapter(
                        this,
                        viewModel.kendaraans.value!!.filter {
                            val task = letterOfTasks.find { item ->
                                ((startDate >= item.tanggal_mulai &&
                                        startDate <= item.tanggal_selesai) ||
                                        (viewModel.endDate.value!! >= item.tanggal_mulai &&
                                                viewModel.endDate.value!! <= item.tanggal_selesai)) &&
                                        item.kendaraan.id == it.id
                            }
                            task == null || (letterOfTask != null &&
                                    task.kendaraan.id == letterOfTask!!.kendaraan.id)
                        })
                )
            }
        })

        viewModel.endDate.observe(this, { endDate ->
            run {
                if (viewModel.startDate.value.isNullOrBlank()) {
                    return@observe
                }

                vehicle_autocomplete.setAdapter(
                    KendaraanArrayAdapter(
                        this,
                        viewModel.kendaraans.value!!.filter {
                            val task = letterOfTasks.find { item ->
                                ((viewModel.startDate.value!! >= item.tanggal_mulai &&
                                        viewModel.startDate.value!! <= item.tanggal_selesai) ||
                                        (endDate >= item.tanggal_mulai &&
                                                endDate <= item.tanggal_selesai)) &&
                                        item.kendaraan.id == it.id
                            }
                            task == null || (letterOfTask != null &&
                                    task.kendaraan.id == letterOfTask!!.kendaraan.id)
                        })
                )
            }
        })

        viewModel.pegawais.observe(this, {
            giver_task_autocomplete.setAdapter(PegawaiArrayAdapter(this, it.filter { v ->
                v.role != "Admin" }))
            receiver_task.adapter = PegawaiArrayAdapter(this, it.filter { v ->
                !listOf("Kepala UPT", "Kasubag TU").contains(v.role) })
        })

        viewModel.kendaraans.observe(this, {
            vehicle_autocomplete.setAdapter(KendaraanArrayAdapter(this, it))
        })

        btn_back.setOnClickListener { onBack() }
        btn_save.setOnClickListener { if (letterOfTask != null) onUpdate() else onSave() }

        getProfile(root, HashMap(), HashMap(), this::onSuccessProfile, {}, showMessage = false)
        getNomorSuratPerintahTugas(root, HashMap(), HashMap(), this::onSuccessNomorSurat, {},
            showMessage = false)
        getPegawai(root, HashMap(), HashMap(), this::onSuccessPegawai, {}, showMessage = false)
        getKendaraan(root, HashMap(), HashMap(), this::onSuccessKendaraan, {}, showMessage = false)
        getSuratPerintahTugas(root, HashMap(), HashMap(), this::onSuccessSuratPerintahTugas, {},
            showMessage = false)
    }

    override fun onBackPressed() { onBack() }
}