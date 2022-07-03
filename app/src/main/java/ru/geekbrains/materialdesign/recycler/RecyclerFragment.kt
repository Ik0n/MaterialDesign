package ru.geekbrains.materialdesign.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.materialdesign.databinding.FragmentLayoutBinding
import ru.geekbrains.materialdesign.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding get() { return _binding!! }
    private var isNewList = false
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
                Pair(Data(0,"Header", "header", TYPE_HEADER), false),
                Pair(Data(1,"Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data(2,"Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data(3,"Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data(4,"MARS", "MARS", TYPE_MARS), false),
                Pair(Data(5,"Header", "header", TYPE_HEADER), false),
                Pair(Data(6,"Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data(7,"Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data(8,"Earth1", "Earth1", TYPE_EARTH), false),
                Pair(Data(9,"MARS", "MARS", TYPE_MARS), false),
            ))
        }

        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)

        binding.recyclerActivityDiffUtilFAB.setOnClickListener {
            changeAdapterData()
        }

    }

    private fun changeAdapterData() {
        adapter.setData(createItemList(isNewList).map { it }.toMutableList())
        isNewList = !isNewList
    }

    private fun createItemList(instanceNumber: Boolean): MutableList<Pair<Data, Boolean>> {
        return when (instanceNumber) {
            false -> mutableListOf(
                Pair(Data(id = 0, type = TYPE_HEADER, name = "Header"), false),
                Pair(Data(id = 1, type = TYPE_MARS, name = "Mars", description = ""), false),
                Pair(Data(id = 2, type = TYPE_MARS, name = "Mars", description = ""), false),
                Pair(Data(id = 3, type = TYPE_MARS, name = "Mars", description = ""), false),
                Pair(Data(id = 4, type = TYPE_MARS, name = "Mars", description = ""), false),
                Pair(Data(id = 5, type = TYPE_MARS, name = "Mars", description = ""), false),
                Pair(Data(id = 6, type = TYPE_MARS, name = "Mars", description = ""), false)
            )
            true -> mutableListOf(
                Pair(Data(id = 0, type = TYPE_HEADER, name = "Header"), false),
                Pair(Data(id = 1, type = TYPE_MARS, name = "Mars", description = ""), false),
                Pair(Data(id = 2, type = TYPE_MARS, name = "Jupiter", description = ""), false),
                Pair(Data(id = 3, type = TYPE_MARS, name = "Mars", description = ""), false),
                Pair(Data(id = 4, type = TYPE_MARS, name = "Neptune", description = ""), false),
                Pair(Data(id = 5, type = TYPE_MARS, name = "Saturn", description = ""), false),
                Pair(Data(id = 6, type = TYPE_MARS, name = "Mars", description = ""), false)
            )
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

class ItemTouchHelperCallback(val adapterCallback : ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.START or ItemTouchHelper.END
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapterCallback.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapterCallback.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                if (viewHolder is RecyclerAdapter.MarsViewHolder)
                    viewHolder.onItemSelected()
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is RecyclerAdapter.MarsViewHolder)
            viewHolder.onItemClear()
        super.clearView(recyclerView, viewHolder)
    }

}