package com.example.simpkd.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.simpkd.R
import com.example.simpkd.adapters.KendaraanArrayAdapter
import com.example.simpkd.apis.getKendaraanStaf
import com.example.simpkd.apis.getNomorSuratPemeliharaanKendaraan
import com.example.simpkd.models.Kendaraan
import com.example.simpkd.models.PemeliharaanKendaraanDetail
import com.example.simpkd.utils.showDatePicker
import com.example.simpkd.utils.snackbarWarning
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_form_vehicle_maintenance.*
import java.lang.Exception

class FormVehicleMaintenanceActivity : AppCompatActivity() {
    private var resultCode = Activity.RESULT_CANCELED
    private var vehicles: List<Kendaraan> = listOf()
    private var letterNumber = ""
    private var selectedVehicle: Kendaraan? = null
    private var vehicleDetail: PemeliharaanKendaraanDetail? = null
    private var vehicleDetailPosition: Int = 0
    private var vehicleDetails: List<PemeliharaanKendaraanDetail>? = listOf()

    private fun onSave() {
        btn_save.submit()
        date.helperText = ""
        vehicle.helperText = ""
        vehicle_total.helperText = ""
        description.helperText = ""

        if (vehicleDetail == null && vehicleDetails.isNullOrEmpty() && date.editText!!.text.isBlank()) {
            date.apply {
                helperText = "Tanggal tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (selectedVehicle == null) {
            vehicle.apply {
                helperText = "Kendaraan tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (problem_type.getChips().isEmpty()) {
            snackbarWarning(root, "Jenis kerusakan tidak boleh kosong",
                Snackbar.LENGTH_LONG).show()
            btn_save.submitFinish()
            return
        }

        try {
            if (vehicle_total_edit_text.text!!.isBlank() || "${vehicle_total_edit_text.text}".toInt() < 1) {
                vehicle_total.apply {
                    helperText = "Jumlah kendaraan tidak boleh kosong"
                    requestFocus()
                }
                btn_save.submitFinish()
                return
            }
        } catch (e: Exception) {
            vehicle_total.apply {
                helperText = "Jumlah kendaraan tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        if (description_edit_text.text!!.isBlank()) {
            description.apply {
                helperText = "Keterangan tidak boleh kosong"
                requestFocus()
            }
            btn_save.submitFinish()
            return
        }

        resultCode = Activity.RESULT_OK
        val intent = Intent()

        if (vehicleDetails.isNullOrEmpty()) intent.putExtra("letter_number", letterNumber)
        if (vehicleDetails.isNullOrEmpty()) intent.putExtra("date", "${date.editText!!.text}")
        if (vehicleDetailPosition > 0) intent.putExtra("position", vehicleDetailPosition)
        intent.putExtra("vehicle", selectedVehicle)
        intent.putExtra("problem_type", problem_type.getChips().joinToString())
        intent.putExtra("vehicle_total", "${vehicle_total_edit_text!!.text}")
        intent.putExtra("description", "${description_edit_text!!.text}")

        if (vehicleDetail != null || !vehicleDetails.isNullOrEmpty()) {
            setResult(resultCode, intent)
            finish()
            return
        }

        intent.setClass(this, VehicleMaintenanceDetailActivity::class.java)
        startActivity(intent)
        finish()
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessKendaraan(response: Any?) {
        if (response != null) {
            vehicles = (response as Array<Kendaraan>).toList().filter { v ->
                vehicleDetails.isNullOrEmpty() || (!vehicleDetails.isNullOrEmpty() &&
                            !vehicleDetails!!.map { it.kendaraan.id }.contains(v.id))
            }
            vehicle_autocomplete.setAdapter(KendaraanArrayAdapter(this, vehicles))
        }
    }

    private fun onSuccessNomorSurat(response: Any?) {
        letterNumber = "$response"
    }

    private fun onBack() {
        setResult(resultCode)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_vehicle_maintenance)
        vehicleDetail = intent.getParcelableExtra("vehicle_detail")
        vehicleDetailPosition = intent.getIntExtra("vehicle_detail_position", 0)
        vehicleDetails = intent.getParcelableArrayListExtra<PemeliharaanKendaraanDetail>("vehicle_details")?.toList()

        if (vehicleDetail != null || !vehicleDetails.isNullOrEmpty()) {
            date.visibility = View.GONE
        }

        if (vehicleDetail != null) {
            val vehicleText = "${vehicleDetail!!.kendaraan.no_polisi} - ${vehicleDetail!!.kendaraan.model.merk}"
            selectedVehicle = vehicleDetail!!.kendaraan
            vehicle_autocomplete.setText(vehicleText)

            for (problemType in vehicleDetail!!.jenis_kerusakan.split(", ")) {
                problem_type.addChip(problemType, problem_type.getChips().size.toLong())
            }

            vehicle_total_edit_text.setText("${vehicleDetail!!.jumlah}")
            description_edit_text.setText(vehicleDetail!!.keterangan)
            btn_save.setText(R.string.edit)
        }

        date.editText!!.setOnClickListener {
            showDatePicker(this, { y, m, d ->
                val dateText = "%d-%02d-%02d".format(y, m + 1, d)
                date.editText!!.setText(dateText)
            })
        }

        vehicle_autocomplete.setOnItemClickListener { _, _, _, id ->
            selectedVehicle = vehicles.find { it.id == id }
        }

        btn_back.setOnClickListener { onBack() }
        btn_save.setOnClickListener { onSave() }

        if (vehicleDetail == null && vehicleDetails.isNullOrEmpty()) {
            getNomorSuratPemeliharaanKendaraan(
                root, HashMap(), HashMap(), this::onSuccessNomorSurat, {},
                showMessage = false
            )
        }
        getKendaraanStaf(root, HashMap(), HashMap(), this::onSuccessKendaraan, {}, showMessage = false)
    }

    override fun onBackPressed() {
        onBack()
    }
}