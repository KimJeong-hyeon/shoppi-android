package com.shoppi.app.repository.productdetail


import com.shoppi.app.model.ProductDetail
import com.shoppi.app.network.ApiClient

class productDetailRemoteDataSource(private val api: ApiClient): productDetailDataSource {

    override suspend fun getProductDetail(productId: String): ProductDetail {
        return api.getProductDetail(productId)
    }
}