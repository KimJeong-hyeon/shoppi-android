package com.shoppi.app.repository.productdetail

import com.shoppi.app.model.ProductDetail

interface productDetailDataSource {


    suspend fun getProductDetail(productId: String): ProductDetail
}