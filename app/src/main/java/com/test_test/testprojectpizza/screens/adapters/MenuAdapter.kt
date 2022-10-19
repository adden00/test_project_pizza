package com.test_test.testprojectpizza.screens.adapters

import android.view.LayoutInflater
import com.test_test.testprojectpizza.data.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test_test.testprojectpizza.R
import com.test_test.testprojectpizza.databinding.MenuLayoutItemBinding

class MenuAdapter(): ListAdapter<MenuItem, MenuAdapter.ItemHolder>(ItemComparator()) {


    class ItemHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val binding = MenuLayoutItemBinding.bind(view)

        fun setData(menuItem: MenuItem)  {
            binding.tvName.text = menuItem.title
            binding.tvIngredients.text = menuItem.description
            binding.tvPrice.text = "от ${listOf(290, 200, 300, 310, 320, 330).random()} р"
            Glide.with(view.context).load(menuItem.image).centerCrop().into(binding.imageView)

        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.menu_layout_item, parent, false))
            }
        }

    }

    class ItemComparator: DiffUtil.ItemCallback<MenuItem>(){
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }





}