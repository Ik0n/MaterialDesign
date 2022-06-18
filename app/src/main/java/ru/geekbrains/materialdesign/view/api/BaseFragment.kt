package ru.geekbrains.materialdesign.view.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.view.settings.SettingsFragment

class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout = R.layout.fragment_earth

        arguments?.let {
            layout = when(it.getInt(BASE_FRAGMENT_TYPE)) {
                EARTH_FRAGMENT -> R.layout.fragment_earth
                MARS_FRAGMENT -> R.layout.fragment_mars
                SYSTEM_FRAGMENT -> R.layout.fragment_system
                else -> R.layout.fragment_earth
            }
        }

        return inflater.inflate(layout, container, false)
    }

    companion object {
        const val EARTH_FRAGMENT = 0
        const val MARS_FRAGMENT = 1
        const val SYSTEM_FRAGMENT = 2
        const val BASE_FRAGMENT_TYPE = "BASE_FRAGMENT_TYPE"
        const val BASE_FRAGMENT_NAME = "BASE_FRAGMENT_NAME"
        @JvmStatic
        fun newInstance(type:Int) : Fragment {
            return BaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(BASE_FRAGMENT_TYPE, type)
                    var name = when(type) {
                        EARTH_FRAGMENT -> "Earth"
                        MARS_FRAGMENT -> "Mars"
                        SYSTEM_FRAGMENT -> "System"
                        else -> "Earth"
                    }
                    putString(BASE_FRAGMENT_NAME, name)
                }
            }
        }
    }

}