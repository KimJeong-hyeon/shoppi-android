package com.shoppi.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.Banner
import com.shoppi.app.databinding.ItemHomeBannerBinding

class HomeBannerAdapter(private val viewModel: HomeViewModel) : ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(
    BannerDiffCallback()
) {
    private lateinit var binding: ItemHomeBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {

        binding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeBannerViewHolder(private val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(banner : Banner) {
            // private val binding로 선언해야지 변수로 사용가능함
            binding.banner = banner
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }
    }
}

class BannerDiffCallback : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.product.productId == newItem.product.productId
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }

}