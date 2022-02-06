package com.shoppi.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoppi.app.Banner
import com.shoppi.app.model.Title
import com.shoppi.app.repository.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository): ViewModel() {

    private val _title = MutableLiveData<Title>()
    // 할당이 되었을 때 LiveData타입으로 변환되어 할당 됨
    val title: LiveData<Title> = _title

    private val _topBanners = MutableLiveData<List<Banner>>()
    val topBanners: LiveData<List<Banner>> = _topBanners

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        // TODO Data Layer - Repository에 요청
        val homeData = homeRepository.getHomeData()
        homeData?.let {
            _title.value = it.title
            _topBanners.value = it.topBanners
        }
    }
}