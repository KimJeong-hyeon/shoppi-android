package com.shoppi.app.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.ProductDetail
import com.shoppi.app.repository.productdetail.produtDetailRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productDetailRepository: produtDetailRepository
) : ViewModel() {

    private val _product = MutableLiveData<ProductDetail>()
    val product: LiveData<ProductDetail> = _product

    fun loadProductDetail(productId: String) {
        viewModelScope.launch {
            _product.value = productDetailRepository.getProductDetail(productId)
        }
    }
}