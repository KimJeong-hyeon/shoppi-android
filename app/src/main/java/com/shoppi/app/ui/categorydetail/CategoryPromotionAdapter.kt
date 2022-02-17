package com.shoppi.app.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.ProductDetail
import com.shoppi.app.databinding.ItemCategoryPromotionBinding

class CategoryPromotionAdapter: ListAdapter<ProductDetail, CategoryPromotionAdapter.CategoryPromotionViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPromotionViewHolder {
        val binding = ItemCategoryPromotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryPromotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryPromotionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryPromotionViewHolder(private val binding: ItemCategoryPromotionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductDetail) {
            binding.product = product
            binding.executePendingBindings()
        }
    }
}

class ProductDiffCallback: DiffUtil.ItemCallback<ProductDetail>() {
    override fun areItemsTheSame(oldItem: ProductDetail, newItem: ProductDetail): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: ProductDetail, newItem: ProductDetail): Boolean {
        return oldItem == newItem
    }
}