package com.example.restaurantkitchen.model

import android.icu.text.AlphabeticIndex

data class Order(
    var orderId: Int,
    var restaurant: Restaurant,
    var customer: Customer,
    var orderFromMenu: String,
    var completed: Boolean
)

data class Restaurant(
    var address: String,
    var id: Int,
    var menu: Map<String, String>,
    var restaurantName: String
)

data class Customer(
    var address: String,
    var id: Int,
    var name: String
)