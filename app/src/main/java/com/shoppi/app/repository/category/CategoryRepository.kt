package com.shoppi.app.repository.category

import com.shoppi.app.model.Category
import com.shoppi.app.repository.category.CategoryRemoteDataSource

class CategoryRepository(private val remoteDataSource: CategoryRemoteDataSource) {

    suspend fun getCategorise(): List<Category> {
        return remoteDataSource.getCategories()
    }
}