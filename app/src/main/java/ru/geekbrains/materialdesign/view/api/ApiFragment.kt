package ru.geekbrains.materialdesign.view.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentApiBinding
import ru.geekbrains.materialdesign.databinding.FragmentSettingsBinding
import ru.geekbrains.materialdesign.view.settings.SettingsFragment

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
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
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