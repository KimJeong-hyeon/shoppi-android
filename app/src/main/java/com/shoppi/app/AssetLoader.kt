package com.shoppi.app

import android.content.Context
import android.util.Log

class AssetLoader(private val context: Context) {

    fun getJsonString(fileName: String): String? {
        //성공과 실패 케이스로 나뉘어지는 작업을 처리하는 경우 runCatching 사용
        // getOrNull 성공할경우 그 결과를 실패할 경우 null을 반
        return kotlin.runCatching {
            loadAssets(fileName)
        }.getOrNull()
    }

    private fun loadAssets(fileName: String): String{
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available()
            val bytes = ByteArray(size)
            inputStream.read(bytes)
            String(bytes)
        }
    }

}