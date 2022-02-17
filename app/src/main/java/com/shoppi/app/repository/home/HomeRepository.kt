package com.shoppi.app.repository.home

import com.shoppi.app.model.HomeData
import com.shoppi.app.repository.home.HomeAssetDataSource

class HomeRepository(private val assetDataSource: HomeAssetDataSource) {

    fun getHomeData() : HomeData? {
        return assetDataSource.getHomeData()
    }
}