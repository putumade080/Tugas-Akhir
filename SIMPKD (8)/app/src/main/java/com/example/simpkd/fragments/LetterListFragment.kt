package com.example.simpkd.fragments

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.simpkd.R
import com.example.simpkd.adapters.LetterListAdapter
import com.example.simpkd.apis.getBeritaAcara
import com.example.simpkd.apis.getPemeliharaanKendaraan
import com.example.simpkd.apis.getSuratPerintahTugas
import com.example.simpkd.apis.getSuratTanggungJawab
import com.example.simpkd.models.BeritaAcara
import com.example.simpkd.models.PemeliharaanKendaraan
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.models.SuratTanggungJawab
import com.example.simpkd.viewModels.MainViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_letter_list.view.*


class LetterListFragment: Fragment() {
    private lateinit var viewModel: MainViewModel
    private val newsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.beritaAcaras.value = listOf()
            getBeritaAcara(requireView().rootView, HashMap(), HashMap(),
                this::onSuccessBeritaAcara, {}, showMessage = false)
        }
    }
    private val letterLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.suratTanggungJawabs.value = listOf()
            getSuratTanggungJawab(requireView().rootView, HashMap(), HashMap(),
                this::onSuccessSuratTanggungJawab, {}, showMessage = false)
        }
    }
    private val vehicleMaintenanceLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.pemeliharaanKendaraans.value = listOf()
            getPemeliharaanKendaraan(requireView().rootView, HashMap(), HashMap(),
                this::onSuccessPemeliharaanKendaraan, {}, showMessage = false)
        }
    }
    private val letterOfTaskLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.suratPerintahTugass.value = listOf()
            getSuratPerintahTugas(requireView().rootView, HashMap(), HashMap(),
                this::onSuccessSuratPerintahTugas, {}, showMessage = false)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessBeritaAcara(response: Any?) {
        if (response != null) {
            viewModel.beritaAcaras.value = (response as Array<BeritaAcara>).filter {
                if (viewModel.me.value!!.id == it.kasubag_tu.id) {
                    it.kasubag_tu.id == viewModel.me.value!!.id && it.status_aktif_surat
                } else {
                    it.pegawai.id == viewModel.me.value!!.id && it.status_aktif_surat
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun onSuccessSuratTanggungJawab(response: Any?) {
        if (response != null) {
            viewModel.suratTanggungJawabs.value = (response as Array<SuratTanggungJawab>).filter {
                it.status_aktif_surat && (viewModel.me.value!!.id == it.pemberi.id ||
                        viewModel.me.value!!.id == it.penerima.id)
            }
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
    private fun onSuccessSuratPerintahTugas(response: Any?) {
        if (response != null) {
            viewModel.suratPerintahTugass.value = (response as Array<SuratPerintahTugas>).filter {
                it.status_aktif_surat && (viewModel.me.value!!.id == it.kasubagTU.id ||
                        viewModel.me.value!!.id == it.pemberi_tugas.id ||
                        it.staf.any { staf -> staf.id == viewModel.me.value!!.id })
            }
        }
    }

    private fun onSetupList() {
        if (view == null) return

        val listValues = arrayListOf<List<Parcelable>?>()
        if (requireView().chip_news.isChecked) listValues.add(viewModel.beritaAcaras.value)
        if (requireView().chip_letter_of_responsibility.isChecked) listValues.add(viewModel.suratTanggungJawabs.value)
        if (requireView().chip_vehicle_maintenance.isChecked) listValues.add(viewModel.pemeliharaanKendaraans.value)
        if (requireView().chip_task_order.isChecked) listValues.add(viewModel.suratPerintahTugass.value)

        if (listValues.any { it == null }) return

        if (listValues.all { it != null && it.isEmpty() }) {
            requireView().recycler_view.visibility = View.GONE
            requireView().empty_container.visibility = View.VISIBLE
        } else {
            requireView().empty_container.visibility = View.GONE
            requireView().recycler_view.visibility = View.VISIBLE
            requireView().recycler_view.adapter = LetterListAdapter(requireContext(),
                listValues.flatMap { it!! }, viewModel, newsLauncher, letterLauncher,
                vehicleMaintenanceLauncher, letterOfTaskLauncher)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_letter_list, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.beritaAcaras.observe(requireActivity(), { onSetupList() })
        viewModel.suratTanggungJawabs.observe(requireActivity(), { onSetupList() })
        viewModel.pemeliharaanKendaraans.observe(requireActivity(), { onSetupList() })
        viewModel.suratPerintahTugass.observe(requireActivity(), { onSetupList() })

        viewModel.me.observe(requireActivity(), {
            getBeritaAcara(view.rootView, HashMap(), HashMap(),
                this::onSuccessBeritaAcara, {}, showMessage = false)
            getSuratTanggungJawab(view.rootView, HashMap(), HashMap(),
                this::onSuccessSuratTanggungJawab, {}, showMessage = false)
            getPemeliharaanKendaraan(view.rootView, HashMap(), HashMap(),
                this::onSuccessPemeliharaanKendaraan, {}, showMessage = false)
            getSuratPerintahTugas(view.rootView, HashMap(), HashMap(),
                this::onSuccessSuratPerintahTugas, {}, showMessage = false)
        })

        for (chip in view.chip_group.children) {
            if (chip is Chip) {
                chip.setOnCheckedChangeListener { _, isChecked ->
                    if (chip.id == R.id.chip_all && isChecked) {
                        view.chip_news.isChecked = true
                        view.chip_letter_of_responsibility.isChecked = true
                        view.chip_task_order.isChecked = true
                        view.chip_vehicle_maintenance.isChecked = true
                    } else {
                        view.chip_all.isChecked = view.chip_group.checkedChipIds.isEmpty() ||
                                view.chip_group.checkedChipIds.size == view.chip_group.childCount ||
                                (view.chip_group.checkedChipIds.size == view.chip_group.childCount - 1
                                && !view.chip_group.checkedChipIds.contains(R.id.chip_all))
                    }
                    onSetupList()
                }
            }
        }

        return view
    }
}