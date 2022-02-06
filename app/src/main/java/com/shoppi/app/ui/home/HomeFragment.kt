package com.shoppi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.ui.common.ViewModelFactory

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_text)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewPagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        viewModel.title.observe(viewLifecycleOwner, {
            toolbarTitle.text = it.text
            GlideApp.with(this)
                .load(it.iconUrl)
                .into(toolbarIcon)
//                            Glide.with(this)
//                .load(titleValue.iconUrl)
//                .into(toolbarIcon)
        })

        viewpager.adapter = HomeBannerAdapter().apply {
            viewModel.topBanners.observe(viewLifecycleOwner, {
                submitList(it)
            })
        }

        val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
        val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
        // 디바이스의 가로 길이
        val screenWidth = resources.displayMetrics.widthPixels
        val offset = screenWidth - pageWidth - pageMargin
        viewpager.offscreenPageLimit = 3
        // 구현해야할 메소드가 하나인 경우 람다식으로 변경 가
        viewpager.setPageTransformer { page, position ->
            page.translationX = position * -offset
        }
        TabLayoutMediator(viewPagerIndicator, viewpager) { tab, position ->

        }.attach()
    }
}