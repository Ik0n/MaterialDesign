package ru.geekbrains.materialdesign.view.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.geekbrains.materialdesign.view.api.BaseFragment.Companion.BASE_FRAGMENT_NAME

const val ADAPTER_SIZE = 3

class ViewPagerAdapter(private val fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return ADAPTER_SIZE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return BaseFragment.newInstance(position).arguments?.getString(BASE_FRAGMENT_NAME).toString()
    }

    override fun getItem(position: Int): Fragment {
        return BaseFragment.newInstance(position)
    }
}