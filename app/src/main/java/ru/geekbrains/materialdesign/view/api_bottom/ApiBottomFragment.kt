package ru.geekbrains.materialdesign.view.api_bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentApiBottomBinding
import ru.geekbrains.materialdesign.utills.EARTH_FRAGMENT
import ru.geekbrains.materialdesign.utills.MARS_FRAGMENT
import ru.geekbrains.materialdesign.utills.SYSTEM_FRAGMENT
import ru.geekbrains.materialdesign.view.api.BaseFragment

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

        startFragment()

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.action_bottom_navigation_view_earth -> startFragment(EARTH_FRAGMENT)
                R.id.action_bottom_navigation_view_mars -> startFragment(MARS_FRAGMENT)
                R.id.action_bottom_navigation_view_system -> startFragment(SYSTEM_FRAGMENT)
                else -> startFragment(EARTH_FRAGMENT)
            }
            true
        }
    }

    private fun startFragment(fragment: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_api_bottom_container, BaseFragment.newInstance(fragment))
            .addToBackStack("")
            .commit()
    }

    private fun startFragment() {
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