package ru.geekbrains.materialdesign.view.api_bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentApiBinding
import ru.geekbrains.materialdesign.databinding.FragmentApiBottomBinding
import ru.geekbrains.materialdesign.view.api.BaseFragment
import ru.geekbrains.materialdesign.view.api.BaseFragment.Companion.EARTH_FRAGMENT
import ru.geekbrains.materialdesign.view.api.BaseFragment.Companion.MARS_FRAGMENT
import ru.geekbrains.materialdesign.view.api.BaseFragment.Companion.SYSTEM_FRAGMENT

class ApiBottomFragment : Fragment() {

    private var _binding: FragmentApiBottomBinding? = null
    val binding: FragmentApiBottomBinding get() { return _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApiBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initContainer()

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.action_bottom_navigation_view_earth -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_api_bottom_container, BaseFragment.newInstance(EARTH_FRAGMENT))
                        .addToBackStack("")
                        .commit()
                }
                R.id.action_bottom_navigation_view_mars -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_api_bottom_container, BaseFragment.newInstance(MARS_FRAGMENT))
                        .addToBackStack("")
                        .commit()
                }
                R.id.action_bottom_navigation_view_system -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_api_bottom_container, BaseFragment.newInstance(SYSTEM_FRAGMENT))
                        .addToBackStack("")
                        .commit()
                }
                else -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_api_bottom_container, BaseFragment.newInstance(EARTH_FRAGMENT))
                        .addToBackStack("")
                        .commit()
                }
            }
            true
        }
    }

    private fun initContainer() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_api_bottom_container, BaseFragment.newInstance(EARTH_FRAGMENT))
            .commit()

        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.action_bottom_navigation_view_system)
        badge.number = 99
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ApiBottomFragment()
    }


}