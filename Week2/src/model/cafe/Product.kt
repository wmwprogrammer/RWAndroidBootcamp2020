package model.cafe

import java.util.*

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val desc: String,
    val price: Double
)