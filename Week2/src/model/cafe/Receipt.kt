package model.cafe

import java.util.*

class Receipt(
  val id: String = UUID.randomUUID().toString(),
  val customerId: String,
  val products: MutableList<Product> = mutableListOf()
//    Product(desc = "Cappuccino", price = 2.20),
//    Product(desc = "Bagel", price = 9.50)
) {

  val total: Double get() {
    var cost: Double = 0.0
    for (product in products) {
      cost += product.price
    }
    return cost
  }


  fun addProduct(product: Product) {
    products.add(product)
  }
}

