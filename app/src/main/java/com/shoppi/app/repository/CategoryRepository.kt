package com.shoppi.app.repository

import com.shoppi.app.model.Category

class CategoryRepository(private val remoteDataSource: CategoryRemoteDataSource) {

    suspend fun getCategorise(): List<Category> {
        return remoteDataSource.getCategories()
    }
}