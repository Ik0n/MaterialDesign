package ru.geekbrains.materialdesign.view.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.geekbrains.materialdesign.view.api.BaseFragment.Companion.BASE_FRAGMENT_NAME

private const val ADAPTER_SIZE = 3

class ViewPager2Adapter(private val fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return ADAPTER_SIZE
    }

    override fun createFragment(position: Int): Fragment {
        return BaseFragment.newInstance(position)
    }
}