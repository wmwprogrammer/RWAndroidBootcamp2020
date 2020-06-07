package model.cafe

import java.util.*

class Receipt(
  val id: String = UUID.randomUUID().toString(),
  val products: MutableList<Product> = mutableListOf()
) {

  fun addProduct(product: Product) {
    products.add(product)
  }
}