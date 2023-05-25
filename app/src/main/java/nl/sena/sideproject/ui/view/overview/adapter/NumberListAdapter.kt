package nl.sena.sideproject.ui.view.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.sena.sideproject.data.remote.response.Numbers
import nl.sena.sideproject.databinding.NumberItemBinding

class NumberListAdapter(private val clickListener: (Numbers.Number) -> Unit) :
    ListAdapter<Numbers.Number, NumberListAdapter.NumberViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemBinding =
            NumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class NumberViewHolder(
        private val itemBinding: NumberItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Numbers.Number, clickListener: (Numbers.Number) -> Unit) {
            itemBinding.numberText.text = item.value
            itemBinding.root.setOnClickListener { clickListener(item) }
        }
    }

    private companion object {

        val diffCallBack = object : DiffUtil.ItemCallback<Numbers.Number>() {
            override fun areItemsTheSame(oldItem: Numbers.Number, newItem: Numbers.Number) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Numbers.Number, newItem: Numbers.Number) =
                oldItem == newItem
        }
    }
}
