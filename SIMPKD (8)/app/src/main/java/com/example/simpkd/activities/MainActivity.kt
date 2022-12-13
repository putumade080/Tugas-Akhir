package com.example.simpkd.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.simpkd.R
import com.example.simpkd.activities.auth.LoginActivity
import com.example.simpkd.adapters.MainViewPagerAdapter
import com.example.simpkd.apis.getProfile
import com.example.simpkd.models.Pegawai
import com.example.simpkd.viewModels.MainViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val bottomNavigationItems = listOf(R.id.nav_home, R.id.nav_list, R.id.nav_profile)
    private val appBarTitles = listOf(R.string.nav_home, R.string.nav_list, R.string.nav_profile)

    private fun onSuccessProfile(response: Any?) {
        if (response != null) {
            viewModel.me.value = response as Pegawai
            viewModel.refetchProfile.value = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        view_pager.adapter = MainViewPagerAdapter(supportFragmentManager)

        main_bottom_navigation.setOnItemSelectedListener {
            view_pager.setCurrentItem(bottomNavigationItems.indexOf(it.itemId), false)
            true
        }

        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                val viewPagerParam = view_pager.layoutParams as CoordinatorLayout.LayoutParams

                main_top_appbar_toolbar.setTitle(appBarTitles[position])
                main_top_appbar_layout.visibility = if (position == 0) View.GONE else View.VISIBLE
                main_top_appbar_toolbar.menu.clear()
                viewPagerParam.behavior = if (position == 0) null else
                    AppBarLayout.ScrollingViewBehavior(this@MainActivity, null)

                if (position == appBarTitles.lastIndex) {
                    main_top_appbar_toolbar.inflateMenu(R.menu.profile_appbar_menu)
                    main_top_appbar_toolbar.setOnMenuItemClickListener {
                        MaterialAlertDialogBuilder(this@MainActivity)
                            .setTitle("Konfirmasi Keluar")
                            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi ini?")
                            .setPositiveButton("Batal") { dialog, _ -> dialog.dismiss() }
                            .setNegativeButton("Yakin") { dialog, _ ->
                                val sharedPreferences = getSharedPreferences("SESSIONS",
                                    Context.MODE_PRIVATE)
                                sharedPreferences.edit().clear().apply()
                                dialog.dismiss()

                                val intent = Intent(this@MainActivity,
                                    LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .show()
                        true
                    }
                }
                main_bottom_navigation.selectedItemId = bottomNavigationItems[position]
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        viewModel.refetchProfile.observe(this, {
            if (it) {
                getProfile(root, HashMap(), HashMap(), this::onSuccessProfile, {},
                    showMessage = false)
            }
        })
    }
}