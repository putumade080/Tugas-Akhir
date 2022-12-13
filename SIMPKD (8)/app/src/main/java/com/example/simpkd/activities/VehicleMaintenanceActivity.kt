package com.example.simpkd.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.simpkd.R
import com.example.simpkd.adapters.VehicleMaintenanceAdapter
import com.example.simpkd.apis.getProfile
import com.example.simpkd.apis.getPemeliharaanKendaraan
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.PemeliharaanKendaraan
import com.example.simpkd.viewModels.VehicleMaintenanceViewModel
import kotlinx.android.synthetic.main.activity_vehicle_maintenance.*
import kotlinx.android.synthetic.main.layout_vertical_recycler_view.*

class VehicleMaintenanceActivity : AppCompatActivity() {
    private lateinit var viewModel: VehicleMaintenanceViewModel
    private val detailLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.pemeliharaanKendaraans.value = listOf()
            getPemeliharaanKendaraan(root, HashMap(), HashMap(),
                this::onSuccessPemeliharaanKendaraan, {}, showMessage = false)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessPemeliharaanKendaraan(response: Any?) {
        if (response != null) {
            viewModel.pemeliharaanKendaraans.value = (response as Array<PemeliharaanKendaraan>).filter {
                it.status_aktif_surat && (viewModel.me.value!!.id == it.kasubagTU.id ||
                        viewModel.me.value!!.id == it.kepalaUPT.id ||
                        viewModel.me.value!!.id == it.staf.id ||
                        viewModel.me.value!!.id == it.pptk.id )
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessProfile(response: Any?) {
        if (response != null) {
            viewModel.me.value = response as Pegawai
            getPemeliharaanKendaraan(root, HashMap(), HashMap(),
                this::onSuccessPemeliharaanKendaraan, {}, showMessage = false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_maintenance)
        viewModel = ViewModelProvider(this)[VehicleMaintenanceViewModel::class.java]

        btn_back.setOnClickListener { finish() }
        btn_add.setOnClickListener {
            val intent = Intent(this, FormVehicleMaintenanceActivity::class.java)
            startActivity(intent)
        }

        viewModel.pemeliharaanKendaraans.observe(this, {
            val layoutParams = layout_vertical_container.layoutParams as
                    ConstraintLayout.LayoutParams

            if (it.isEmpty()) {
                layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID

                root.gravity = Gravity.CENTER
                recycler_view.visibility = View.GONE
                empty_container.visibility = View.VISIBLE
            } else {
                layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                root.gravity = Gravity.NO_GRAVITY
                empty_container.visibility = View.GONE
                recycler_view.visibility = View.VISIBLE
                recycler_view.adapter = VehicleMaintenanceAdapter(this, it, viewModel,
                    detailLauncher)
            }
        })

        getProfile(root, HashMap(), HashMap(), this::onSuccessProfile, {}, showMessage = false)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.me.value != null) {
            viewModel.pemeliharaanKendaraans.value = listOf()
            getPemeliharaanKendaraan(root, HashMap(), HashMap(),
                this::onSuccessPemeliharaanKendaraan, {}, showMessage = false
            )
        }
    }
}