package ru.geekbrains.materialdesign.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentLayoutBinding

class LayoutFragment : Fragment() {

    private var _binding: FragmentLayoutBinding? = null
    private val binding: FragmentLayoutBinding get() { return _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startFragment(ConstraintLayout.newInstance())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.action_bottom_navigation_layout_constraint -> {
                    startFragment(ConstraintLayout.newInstance())
                    true
                }
                R.id.action_bottom_navigation_layout_coordinator -> {
                    startFragment(CoordinatorLayout.newInstance())
                    true
                }
                R.id.action_bottom_navigation_layout_motion -> {
                    startFragment(MotionLayout.newInstance())
                    true
                }
                else -> true
            }
        }
    }

    private fun startFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.animator.fragment_fade_in, R.animator.fragment_fade_out)
            .replace(R.id.layout_container, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() : Fragment {
            return LayoutFragment()
        }
    }

}