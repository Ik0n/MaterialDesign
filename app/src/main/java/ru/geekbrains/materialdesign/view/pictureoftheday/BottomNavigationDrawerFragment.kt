package ru.geekbrains.materialdesign.view.pictureoftheday

import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.BottomNavigationLayoutBinding
import ru.geekbrains.materialdesign.view.animations.AnimationsFragment
import ru.geekbrains.materialdesign.view.apibottom.ApiBottomFragment
import ru.geekbrains.materialdesign.view.layouts.LayoutFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding get() { return _binding!!}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomNavigationLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_one -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
                        .replace(R.id.container, ApiBottomFragment.newInstance())
                        .addToBackStack("")
                        .commit()
                }
                R.id.navigation_two -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
                        .replace(R.id.container, LayoutFragment.newInstance())
                        .addToBackStack("")
                        .commit()
                }
                R.id.navigation_three -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
                        .replace(R.id.container, AnimationsFragment.newInstance())
                        .addToBackStack("")
                        .commit()
                }
            }
            true
        }
    }

}