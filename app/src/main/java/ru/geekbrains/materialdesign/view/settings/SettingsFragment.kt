package ru.geekbrains.materialdesign.view.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

            chipRedTheme.setOnClickListener {
                Parameters.getInstance().theme = R.style.MyRedTheme
                requireActivity().recreate()
            }
            chipGreenTheme.setOnClickListener {
                Parameters.getInstance().theme = R.style.MyGreenTheme
                requireActivity().recreate()
            }
            chipBlueTheme.setOnClickListener {
                Parameters.getInstance().theme = R.style.MyBlueTheme
                requireActivity().recreate()
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