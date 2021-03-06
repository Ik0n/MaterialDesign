package ru.geekbrains.materialdesign.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentSettingsBinding
import ru.geekbrains.materialdesign.utills.*

class SettingsFragment : Fragment(), View.OnClickListener {

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

        binding.chipRedTheme.setOnClickListener(this)
        binding.chipGreenTheme.setOnClickListener(this)
        binding.chipBlueTheme.setOnClickListener(this)

        binding.apply {

            when(Parameters.getInstance().theme) {
                R.style.MyRedTheme -> chipRedTheme.isChecked = true
                R.style.MyGreenTheme -> chipGreenTheme.isChecked = true
                R.style.MyBlueTheme -> chipBlueTheme.isChecked = true
            }

            when(Parameters.getInstance().mode) {
                AppCompatDelegate.MODE_NIGHT_NO -> {
                    chipLightMode.isChecked = true
                    if  (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                AppCompatDelegate.MODE_NIGHT_YES -> {
                    chipDarkMode.isChecked = true
                    if  (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
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

    private fun setTheme(theme: Int) {
        var temp = when(theme) {
            RED_THEME -> R.style.MyRedTheme
            GREEN_THEME -> R.style.MyGreenTheme
            BLUE_THEME -> R.style.MyBlueTheme
            else -> R.style.MyBlueTheme
        }
        Parameters.getInstance().theme = temp
        requireActivity().recreate()
        saveTheme(temp)
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

    override fun onClick(chip: View?) {
        when(chip) {
            binding.chipRedTheme -> setTheme(RED_THEME)
            binding.chipGreenTheme -> setTheme(GREEN_THEME)
            binding.chipBlueTheme -> setTheme(BLUE_THEME)
        }
    }
}