package ru.geekbrains.materialdesign.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.databinding.FragmentLayoutBinding
import ru.geekbrains.materialdesign.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding get() { return _binding!! }

    private val adapter = RecyclerAdapter { position, data ->

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter.apply {
            setData(arrayListOf(
                Pair(Data("Header", "header", TYPE_HEADER), false),
                Pair(Data("Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data("Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data("Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data("MASR", "MASR", TYPE_MARS), false),
                Pair(Data("Header", "header", TYPE_HEADER), false),
                Pair(Data("Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data("Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data("Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data("MASR", "MASR", TYPE_MARS), false),
            ))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }


}