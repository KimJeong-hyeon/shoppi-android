package com.shoppi.app.repository.home

import com.google.gson.Gson
import com.shoppi.app.AssetLoader
import com.shoppi.app.model.HomeData

class HomeAssetDataSource(private val assetLoader: AssetLoader) : HomeDataSource {

    private val gson = Gson()

    override fun getHomeData(): HomeData? {
//        val homeJsonString = assetLoader.getJsonString("home.json")
//        return gson.fromJson(homeJsonString, HomeData::class.java)
        // let을 사용하여 위에 코드를 간결하게 만들 수 있음
       return assetLoader.getJsonString("home.json")?.let {
           gson.fromJson(it, HomeData::class.java)
        }
    }
}