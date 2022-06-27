package ru.geekbrains.materialdesign.view.pictureoftheday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentPictureOfTheDayBinding
import ru.geekbrains.materialdesign.utills.DAY_BEFORE_YESTERDAY
import ru.geekbrains.materialdesign.utills.TODAY
import ru.geekbrains.materialdesign.utills.YESTERDAY
import ru.geekbrains.materialdesign.view.MainActivity
import ru.geekbrains.materialdesign.view.api.ApiFragment
import ru.geekbrains.materialdesign.view.settings.SettingsFragment
import ru.geekbrains.materialdesign.viewmodel.AppState
import ru.geekbrains.materialdesign.viewmodel.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {

    private var isMain = true

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding get() { return _binding!! }

    private val viewModel:PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        init()
    }

    private fun init() {
        setHasOptionsMenu(true)

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)

        sendServerRequest()

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
            sendServerRequest()
        }
        binding.chip2.setOnClickListener {
            sendServerRequest(YESTERDAY)
        }
        binding.chip3.setOnClickListener {
            sendServerRequest(DAY_BEFORE_YESTERDAY)
        }
    }

    private fun sendServerRequest(date: String) {
        viewModel.getLiveDataFromViewToObserve().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendServerRequest(date)
    }

    private fun sendServerRequest() {
        viewModel.getLiveDataFromViewToObserve().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendServerRequest(TODAY)
    }

    private fun renderData(appState: AppState) {
        when(appState) {
            is AppState.Success -> {
                if (appState.serverResponseData.mediaType == "video") {
                    with(binding) {
                        imageView.visibility = View.GONE
                        videoUrl.visibility = View.VISIBLE
                        videoUrl.text = "Сегодня у нас нет картинки," +
                                " но есть видео! ${appState.serverResponseData.url} \n Кликни чтобы открыть!"
                        videoUrl.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(appState.serverResponseData.url)
                            }
                            startActivity(intent)
                        }
                    }
                } else {
                    binding.imageView.load(appState.serverResponseData.hdurl)
                    binding.let {
                        it.bottomSheet.title.text = appState.serverResponseData.title
                        it.bottomSheet.explanation.text = appState.serverResponseData.explanation
                    }
                }
            }
            is AppState.Loading -> {
                binding.imageView.load(R.drawable.ic_no_photo_vector)

                binding.let {
                    it.bottomSheet.title.text = ""
                    it.bottomSheet.explanation.text = ""
                    it.videoUrl.visibility = View.GONE
                }
            }
            is AppState.Error -> {
                binding.imageView.load(R.drawable.ic_no_photo_vector)
                binding.let {
                    it.bottomSheet.title.text = "Error"
                    it.bottomSheet.explanation.text = appState.error.message
                    it.videoUrl.visibility = View.GONE
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