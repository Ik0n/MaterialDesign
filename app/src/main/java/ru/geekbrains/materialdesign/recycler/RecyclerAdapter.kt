package ru.geekbrains.materialdesign.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentRecyclerItemEarthBinding
import ru.geekbrains.materialdesign.databinding.FragmentRecyclerItemHeaderBinding
import ru.geekbrains.materialdesign.databinding.FragmentRecyclerItemMarsBinding

const val TYPE_EARTH = 0
const val TYPE_MARS = 1
const val TYPE_HEADER = 2

class RecyclerAdapter(val callback: ActionRecyclerAdapter) :
    RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(), ItemTouchHelperAdapter  {

    private var data : MutableList<Pair<Data, Boolean>> = mutableListOf()

    fun setData(data: MutableList<Pair<Data, Boolean>>) {
        this.data = data
        notifyDataSetChanged()
    }

    private fun generateItem() = Pair(Data("Mars o99o", "", TYPE_MARS), false)

    override fun getItemViewType(position: Int): Int {
        return data[position].first.type
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

    class EarthViewHolder(val binding: FragmentRecyclerItemEarthBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data : Pair<Data, Boolean>) {
            binding.name.text = data.first.name
            binding.descriptionTextView.text = data.first.description
        }
    }

    inner class MarsViewHolder(val binding: FragmentRecyclerItemMarsBinding) :
        BaseViewHolder(binding.root), ItemTouchHelperViewHolder {
        override fun bind(data : Pair<Data, Boolean>) {
            with (binding) {
                this.name.text = data.first.name
                this.marsDescriptionTextView.visibility = if (data.second) View.VISIBLE else View.GONE
                this.addItemImageView.setOnClickListener {
                    this@RecyclerAdapter.data.add(adapterPosition, generateItem())
                    notifyItemInserted(adapterPosition)
                }
                this.removeItemImageView.setOnClickListener {
                    this@RecyclerAdapter.data.removeAt(layoutPosition)
                    notifyItemRemoved(layoutPosition)
                }
                this.moveItemUp.setOnClickListener {
                    this@RecyclerAdapter.data.removeAt(layoutPosition).apply {
                        this@RecyclerAdapter.data.add(layoutPosition - 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition - 1)
                }
                this.moveItemDown.setOnClickListener {
                    this@RecyclerAdapter.data.removeAt(layoutPosition).apply {
                        this@RecyclerAdapter.data.add(layoutPosition + 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition + 1)
                }
                this.marsImageView.setOnClickListener {
                    this@RecyclerAdapter.data[layoutPosition] = this@RecyclerAdapter.data[layoutPosition].let {
                        it.first to !it.second
                    }
                    notifyItemChanged(layoutPosition)
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    class HeaderViewHolder(val binding: FragmentRecyclerItemHeaderBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data : Pair<Data, Boolean>) {
            binding.name.text = data.first.name
        }
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<Data, Boolean>)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        this.data.removeAt(fromPosition).apply {
            this@RecyclerAdapter.data.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        this.data.removeAt(position)
        notifyItemRemoved(position)
    }
}