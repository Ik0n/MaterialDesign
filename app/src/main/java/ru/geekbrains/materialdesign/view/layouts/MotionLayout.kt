package ru.geekbrains.materialdesign.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.databinding.FragmentCoordinatorLayoutBinding
import ru.geekbrains.materialdesign.databinding.FragmentMotionLayoutEndBinding
import ru.geekbrains.materialdesign.view.layouts.behaviors.FadeBehavior

class MotionLayout : Fragment() {

    private var _binding: FragmentMotionLayoutEndBinding? = null
    private val binding: FragmentMotionLayoutEndBinding get() { return _binding!! }
    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View {
        _binding = FragmentMotionLayoutEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() : Fragment {
            return MotionLayout()
        }
    }


}