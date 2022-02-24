package com.shoppi.app.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.ProductDetail
import com.shoppi.app.repository.cart.CartRepository
import com.shoppi.app.repository.productdetail.produtDetailRepository
import com.shoppi.app.ui.common.Event
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productDetailRepository: produtDetailRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _product = MutableLiveData<ProductDetail>()
    val product: LiveData<ProductDetail> = _product

    private val _addCartEvent = MutableLiveData<Event<Unit>>()
    val addCartEvent : LiveData<Event<Unit>> = _addCartEvent

    fun loadProductDetail(productId: String) {
        viewModelScope.launch {
            _product.value = productDetailRepository.getProductDetail(productId)
        }
    }

    fun addCart(product: ProductDetail) {
        viewModelScope.launch {
            cartRepository.addCartItem(product)
            _addCartEvent.value = Event(Unit)
        }
    }
}