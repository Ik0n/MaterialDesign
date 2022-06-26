package ru.geekbrains.materialdesign.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.databinding.FragmentConstraintLayoutBinding
import ru.geekbrains.materialdesign.databinding.FragmentLayoutBinding

class ConstraintLayout : Fragment() {

    private var _binding: FragmentConstraintLayoutBinding? = null
    private val binding: FragmentConstraintLayoutBinding get() { return _binding!! }
    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View {
        _binding = FragmentConstraintLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.group1.visibility = View.GONE
        binding.button1.setOnClickListener {
            flag = !flag
            if (flag) {
                binding.group1.visibility = View.GONE
            } else {
                binding.group1.visibility = View.VISIBLE
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
            return ConstraintLayout()
        }
    }


}