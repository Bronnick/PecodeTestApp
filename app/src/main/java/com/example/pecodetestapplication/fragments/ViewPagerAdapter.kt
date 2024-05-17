package com.example.pecodetestapplication.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pecodetestapplication.view_models.PagerViewModel

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val viewModel: PagerViewModel
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.newInstance(position, viewModel)
    }

    override fun getItemCount(): Int {
        return viewModel.numberOfPages.value ?: 1
    }
}