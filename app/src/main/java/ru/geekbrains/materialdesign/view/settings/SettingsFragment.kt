package ru.geekbrains.materialdesign.view.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentPictureOfTheDayBinding
import ru.geekbrains.materialdesign.databinding.FragmentSettingsBinding
import ru.geekbrains.materialdesign.utills.Parameters
import ru.geekbrains.materialdesign.view.MainActivity
import ru.geekbrains.materialdesign.viewmodel.AppState
import ru.geekbrains.materialdesign.viewmodel.PictureOfTheDayViewModel

const val THEME = "THEME"
const val MODE = "MODE"

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    val binding: FragmentSettingsBinding get() { return _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            when(Parameters.getInstance().theme) {
                R.style.MyRedTheme -> chipRedTheme.isChecked = true
                R.style.MyGreenTheme -> chipGreenTheme.isChecked = true
                R.style.MyBlueTheme -> chipBlueTheme.isChecked = true
            }

            when(Parameters.getInstance().mode) {
                AppCompatDelegate.MODE_NIGHT_NO -> chipLightMode.isChecked = true
                AppCompatDelegate.MODE_NIGHT_YES -> chipDarkMode.isChecked = true
            }

            chipRedTheme.setOnClickListener {
                Parameters.getInstance().theme = R.style.MyRedTheme
                requireActivity().recreate()
                saveTheme(R.style.MyRedTheme)
            }
            chipGreenTheme.setOnClickListener {
                Parameters.getInstance().theme = R.style.MyGreenTheme
                requireActivity().recreate()
                saveTheme(R.style.MyGreenTheme)
            }
            chipBlueTheme.setOnClickListener {
                Parameters.getInstance().theme = R.style.MyBlueTheme
                requireActivity().recreate()
                saveTheme(R.style.MyBlueTheme)
            }
            chipLightMode.setOnClickListener {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Parameters.getInstance().mode = AppCompatDelegate.MODE_NIGHT_NO
                saveMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            chipDarkMode.setOnClickListener {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Parameters.getInstance().mode = Parameters.getInstance().mode
                saveMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun saveTheme(theme: Int) {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putInt(THEME, theme)
                apply()
            }
        }
    }

    private fun saveMode(mode: Int) {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putInt(MODE, mode)
                apply()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}