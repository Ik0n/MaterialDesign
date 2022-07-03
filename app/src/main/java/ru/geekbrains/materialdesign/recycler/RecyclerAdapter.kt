package ru.geekbrains.materialdesign.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.materialdesign.databinding.FragmentRecyclerItemEarthBinding
import ru.geekbrains.materialdesign.databinding.FragmentRecyclerItemHeaderBinding
import ru.geekbrains.materialdesign.databinding.FragmentRecyclerItemMarsBinding

const val TYPE_EARTH = 0
const val TYPE_MARS = 1
const val TYPE_HEADER = 2

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>()  {

    private var data : List<Data> = listOf()

    fun setData(data: List<Data>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                EarthViewHolder(FragmentRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context)))
            }
            TYPE_MARS -> {
                MarsViewHolder(FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context)))
            }
            TYPE_HEADER -> {
                HeaderViewHolder(FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context)))
            }
            else -> {
                MarsViewHolder(FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class EarthViewHolder(val binding: FragmentRecyclerItemEarthBinding) : BaseViewHolder(binding.root) {
        override fun bind(data : Data) {
            binding.name.text = data.name
            binding.descriptionTextView.text = data.description
        }
    }

    class MarsViewHolder(val binding: FragmentRecyclerItemMarsBinding) : BaseViewHolder(binding.root) {
        override fun bind(data : Data) {
            binding.name.text = data.name
        }
    }

    class HeaderViewHolder(val binding: FragmentRecyclerItemHeaderBinding) : BaseViewHolder(binding.root) {
        override fun bind(data : Data) {
            binding.name.text = data.name
        }
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Data)
    }
}