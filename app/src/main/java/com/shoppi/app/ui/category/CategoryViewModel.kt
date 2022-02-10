package com.shoppi.app.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.Category
import com.shoppi.app.repository.CategoryRepository
import kotlinx.coroutines.launch
import java.util.*

class CategoryViewModel(private val categoryRepository: CategoryRepository): ViewModel() {

    private val _items = MutableLiveData<List<Category>>()
    val items: LiveData<List<Category>> = _items

    init {
        loadCategory()
    }

    private fun loadCategory() {
        // TODO repository클래스에 데이터 요청
        //코루틴
        viewModelScope.launch {
            val categories = categoryRepository.getCategorise()
            _items.value = categories
        }
    }
}