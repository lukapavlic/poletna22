package com.example.restaurantkitchen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantkitchen.model.Order
import com.example.restaurantkitchen.service.APIService
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {
    private val _orderList = mutableStateListOf<Order>()
    var errorMessage: String by mutableStateOf("")
    val orderList: List<Order>
        get() = _orderList

    fun getOrderList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _orderList.clear()
                _orderList.addAll(apiService.getOrders())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun setCompletedAtIndex(index: Int, isSelected: Boolean) {
        _orderList[index] = _orderList[index].copy(completed = isSelected)
    }
}