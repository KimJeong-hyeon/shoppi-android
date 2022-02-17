package com.shoppi.app.model

import com.google.gson.annotations.SerializedName
import com.shoppi.app.ProductDetail

data class CategoryDetail (
    @SerializedName("top_selling")
    val topSelling: TopSelling,
    val promotions: Promotions,
)

data class TopSelling(
    val title: Title,
    val categories: List<Category>
)

data class Promotions(
    val title: Title,
    val items: List<ProductDetail>
)