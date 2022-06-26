package ru.geekbrains.materialdesign.view.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentApiBinding
import ru.geekbrains.materialdesign.utills.*

class ApiFragment : Fragment() {

    private var _binding: FragmentApiBinding? = null
    val binding: FragmentApiBinding get() { return _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPager2Adapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager
        ) { tab, position -> tab.text =
            BaseFragment.newInstance(position).arguments?.getString(BASE_FRAGMENT_NAME)
                .toString()
            tab.icon = when (
                BaseFragment.newInstance(position).arguments?.getInt(BASE_FRAGMENT_ICON)
            ) {
                EARTH_FRAGMENT_ICON -> resources.getDrawable(R.drawable.ic_earth)
                MARS_FRAGMENT_ICON -> resources.getDrawable(R.drawable.ic_mars)
                SYSTEM_FRAGMENT_ICON -> resources.getDrawable(R.drawable.ic_system)
                else -> resources.getDrawable(R.drawable.ic_earth)
            }
        }.attach()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ApiFragment()
    }

}