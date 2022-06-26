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

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.layout_container, ConstraintLayout.newInstance())
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.action_bottom_navigation_layout_constraint -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_container, ConstraintLayout.newInstance())
                        .commit()
                    true
                }
                R.id.action_bottom_navigation_layout_coordinator -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_container, CoordinatorLayout.newInstance())
                        .commit()
                    true
                }
                R.id.action_bottom_navigation_layout_motion -> {
                    true
                }
                else -> true
            }
        }
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