package com.shoppi.app

import com.google.gson.annotations.SerializedName
import com.shoppi.app.model.ProductDetail

data class Banner(
    @SerializedName("background_image_url")
    val backgroundImageUrl: String,
    val badge: BannerBadge,
    val label: String,
    @SerializedName("product_detail")
    val product: ProductDetail
)

data class BannerBadge(
    val label: String,
    @SerializedName("background_color")
    val backgroundColor: String
)
