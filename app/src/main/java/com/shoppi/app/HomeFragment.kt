package com.shoppi.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import org.json.JSONObject

class HomeFragment: Fragment() {

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


        val assetLoader = AssetLoader()
        //getJsonString에서 nonnull타입의 context를 필요로 하기때문에 nonnull타입의 context를 반환하는 requireContext()를 사
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")
        Log.d("homeData", homeJsonString ?: "")

        if (!homeJsonString.isNullOrEmpty()) {
            val gson = Gson()
            val homeData =  gson.fromJson(homeJsonString,HomeData::class.java)

            toolbarTitle.text = homeData.title.text
//            Glide.with(this)
//                .load(titleValue.iconUrl)
//                .into(toolbarIcon)
            GlideApp.with(this)
                .load(homeData.title.iconUrl)
                .into(toolbarIcon)

            viewpager.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanners)
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
}