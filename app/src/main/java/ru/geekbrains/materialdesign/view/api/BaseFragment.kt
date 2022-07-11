package ru.geekbrains.materialdesign.view.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.utills.*

class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        @JvmStatic
        fun newInstance(type:Int) : Fragment {
            return BaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(BASE_FRAGMENT_TYPE, type)
                    var name = ""
                    var icon = 0
                    when(type) {
                        EARTH_FRAGMENT -> {
                            name = EARTH_FRAGMENT_NAME
                            icon = EARTH_FRAGMENT_ICON
                        }
                        MARS_FRAGMENT -> {
                            name = MARS_FRAGMENT_NAME
                            icon = MARS_FRAGMENT_ICON
                        }
                        SYSTEM_FRAGMENT -> {
                            name = SYSTEM_FRAGMENT_NAME
                            icon = SYSTEM_FRAGMENT_ICON
                        }
                        else -> {
                            EARTH_FRAGMENT_NAME
                            icon = EARTH_FRAGMENT_ICON
                        }
                    }
                    putString(BASE_FRAGMENT_NAME, name)
                    putInt(BASE_FRAGMENT_ICON, icon)
                }
            }
        }
    }
}