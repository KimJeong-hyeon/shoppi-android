package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartItem
import com.shoppi.app.model.CartProduct
import com.shoppi.app.model.ProductDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class CartRepository(private val localDataSource: CartItemLocalDataSource) {

    suspend fun addCartItem(product: ProductDetail) {
        withContext(Dispatchers.IO) {
            val cartItem = CartItem(
                productId = product.productId,
                label = product.label,
                price = product.price,
                brandName = product.brandName ?: "",
                thumbnailImageUrl = product.thumbnailImageUrl ?: "",
                type = product.type ?: "",
                amount = 1
            )
            localDataSource.addCartItem(cartItem)
        }
    }

    suspend fun getCartItems(): List<CartItem> {
        return withContext(Dispatchers.IO) {
            localDataSource.getCartItems()
        }
    }
}