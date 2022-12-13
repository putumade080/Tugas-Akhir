package com.example.simpkd.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.simpkd.fragments.NewsUnverifiedFragment
import com.example.simpkd.fragments.NewsVerifiedFragment

class NewsViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = listOf(
        NewsUnverifiedFragment(),
        NewsVerifiedFragment()
    )

    private val pageTitles = listOf("Belum Diverifikasi", "Terverifikasi")

    override fun getPageTitle(position: Int): CharSequence {
        return pageTitles[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}