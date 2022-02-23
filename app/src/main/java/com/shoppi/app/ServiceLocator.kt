package com.shoppi.app

import com.shoppi.app.network.ApiClient
import kotlin.jvm.internal.Intrinsics

object ServiceLocator {

    private var apiClient: ApiClient? = null

    fun providerApiClient(): ApiClient {
        // null인 경우 run구문을 실행 null이 아닌경우 기존의 apiClient를 반환
        return apiClient ?: kotlin.run {
            ApiClient.create().also {
                apiClient = it
            }
        }
    }
}