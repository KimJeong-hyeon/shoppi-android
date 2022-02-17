package com.shoppi.app.repository.categorydetail

import com.shoppi.app.model.CategoryDetail
import com.shoppi.app.network.ApiClient

// CategoryDetailDataSource의 인터페이스를 구현하는 클래스
class CategoryDetailRemoteDataSource(private val api: ApiClient): CategoryDetailDataSource {
    override suspend fun getCategoryDeatail(): CategoryDetail {
        return api.getCategoryDetail()
    }
}