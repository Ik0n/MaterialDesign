package ru.geekbrains.materialdesign.view.pictureoftheday

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
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentPictureOfTheDayBinding
import ru.geekbrains.materialdesign.view.MainActivity
import ru.geekbrains.materialdesign.view.api.ApiFragment
import ru.geekbrains.materialdesign.view.api.BaseFragment
import ru.geekbrains.materialdesign.view.settings.SettingsFragment
import ru.geekbrains.materialdesign.viewmodel.*
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheDayFragment : Fragment() {

    var isMain = true

    private var _binding: FragmentPictureOfTheDayBinding? = null
    val binding: FragmentPictureOfTheDayBinding get() { return _binding!! }

    val viewModel:PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.app_bar_fav -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, ApiFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }
            R.id.app_bar_settings -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SettingsFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        viewModel.getLiveDataFromViewToObserve().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendServerRequest(TODAY)
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        val behavior =
            (binding.bottomSheet.bottomSheetContainer.layoutParams
                    as CoordinatorLayout.LayoutParams).behavior as BottomSheetBehavior

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })

        binding.fab.setOnClickListener {
            if (isMain) {
                binding.apply {
                    bottomAppBar.navigationIcon = null
                    bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                    binding.fab.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_back_fab)
                    )
                    binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
                }
            } else {
                binding.apply {
                    bottomAppBar.navigationIcon = ContextCompat.getDrawable(
                        requireContext(), R.drawable.ic_hamburger_menu_bottom_bar
                    )
                    bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    binding.fab.setImageDrawable(
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_plus_fab)
                    )
                    binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
                }
            }
            isMain = !isMain
        }

        binding.chip1.setOnClickListener {
            viewModel.getLiveDataFromViewToObserve().observe(viewLifecycleOwner, Observer {
                renderData(it)
            })
            viewModel.sendServerRequest(TODAY)
        }
        binding.chip2.setOnClickListener {
            viewModel.getLiveDataFromViewToObserve().observe(viewLifecycleOwner, Observer {
                renderData(it)
            })
            viewModel.sendServerRequest(YESTERDAY)
        }
        binding.chip3.setOnClickListener {
            viewModel.getLiveDataFromViewToObserve().observe(viewLifecycleOwner, Observer {
                renderData(it)
            })
            viewModel.sendServerRequest(DAY_BEFORE_YESTERDAY)
        }
    }

    private fun renderData(appState: AppState) {

        when(appState) {
            is AppState.Success -> {
                binding.imageView.load(appState.serverResponseData.hdurl) {
                    // placeholder - error - transform -
                }
                binding.let {
                    it.bottomSheet.title.text = appState.serverResponseData.title
                    it.bottomSheet.explanation.text = appState.serverResponseData.explanation
                }
            }
            is AppState.Loading -> {
                binding.imageView.load(R.drawable.ic_no_photo_vector)
                binding.let {
                    it.bottomSheet.title.text = ""
                    it.bottomSheet.explanation.text = ""
                }
            }
            is AppState.Error -> {
                binding.imageView.load(R.drawable.ic_no_photo_vector)
                binding.let {
                    it.bottomSheet.title.text = "Error"
                    it.bottomSheet.explanation.text = appState.error.message
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()
    }
}