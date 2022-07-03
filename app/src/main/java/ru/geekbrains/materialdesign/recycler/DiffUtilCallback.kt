package ru.geekbrains.materialdesign.recycler

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldItems : List<Pair<Data, Boolean>>,
    private val newItems : List<Pair<Data, Boolean>>
    ) : DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].first.id == newItems[newItemPosition].first.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].first.name == newItems[newItemPosition].first.name &&
                oldItems[oldItemPosition].first.description == newItems[newItemPosition].first.description
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return Change(oldItems[oldItemPosition], newItems[newItemPosition])
    }
}