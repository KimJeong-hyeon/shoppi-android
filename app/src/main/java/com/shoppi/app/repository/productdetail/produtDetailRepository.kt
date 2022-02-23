package com.shoppi.app.repository.productdetail

import com.shoppi.app.model.ProductDetail


class produtDetailRepository(private val remoteDataSource: productDetailDataSource) {

    suspend fun getProductDetail(productId: String): ProductDetail {
        return remoteDataSource.getProductDetail(productId)
    }
}