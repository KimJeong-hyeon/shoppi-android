package com.shoppi.app.network

import com.shoppi.app.model.Category
import com.shoppi.app.model.CategoryDetail
import com.shoppi.app.model.ProductDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("categories.json")
    // 코루틴으로 요청하기 때문에 suspend 키워드
    suspend fun getCategories(): List<Category>

    @GET("fashion_female.json")
    suspend fun getCategoryDetail(): CategoryDetail

    @GET("products/{productId}.json")
    suspend fun getProductDetail(@Path("productId") productId: String): ProductDetail

    //API CLIENT객체 생성
    companion object {

        private const val baseUrl = "https://shoppi-7e427-default-rtdb.asia-southeast1.firebasedatabase.app/"

        fun create(): ApiClient {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()


            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}