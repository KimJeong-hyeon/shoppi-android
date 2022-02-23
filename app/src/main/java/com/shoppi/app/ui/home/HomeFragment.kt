package com.shoppi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.common.KEY_PRODUCT_ID
import com.shoppi.app.databinding.FragmentHomeBinding
import com.shoppi.app.ui.categorydetail.ProductPromotionAdapter
import com.shoppi.app.ui.categorydetail.SectionTitleAdapter
import com.shoppi.app.ui.common.EventObserver
import com.shoppi.app.ui.common.ProductClickListener
import com.shoppi.app.ui.common.ViewModelFactory

class HomeFragment: Fragment(), ProductClickListener {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        setToolbar()
        setNavigation()
        setTopBanners()
        setListAdapter()
    }

    override fun onProductClick(productId: String) {
        findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
            KEY_PRODUCT_ID to "desk-1"
        ))
    }

    private fun setListAdapter() {
        val titleAdapter = SectionTitleAdapter()
        val promotionAdapter = ProductPromotionAdapter(this)
        binding.rvHome.adapter = ConcatAdapter(titleAdapter, promotionAdapter)
        viewModel.promotions.observe(viewLifecycleOwner) {
            titleAdapter.submitList(listOf(it.title))
            promotionAdapter.submitList(it.items)
        }
    }

    private fun setToolbar() {
        viewModel.title.observe(viewLifecycleOwner, {
            binding.title = it
            //                            Glide.with(this)
            //                .load(titleValue.iconUrl)
            //                .into(toolbarIcon)
        })
    }

    private fun setNavigation() {
        viewModel.openProductEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
                KEY_PRODUCT_ID to it
            ))
        })
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.topBanners.observe(viewLifecycleOwner, {
                    submitList(it)
                })
            }
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            // 디바이스의 가로 길이
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin
            offscreenPageLimit = 3
            // 구현해야할 메소드가 하나인 경우 람다식으로 변경 가
            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }
            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { tab, position ->

            }.attach()
        }
    }
}