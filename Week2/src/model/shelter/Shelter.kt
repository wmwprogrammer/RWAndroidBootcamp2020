package model.shelter

import model.animals.Cat
import java.util.*

data class Shelter(
  val id: String = UUID.randomUUID().toString(),
  val name: String,
  val address: String,
  val city: String,
  val state: String,
  val zipCode: String,
  val phoneNumber: String,
  var cats: MutableList<Cat> = mutableListOf()
) {

  fun addCat(catToAdd: Cat) {
    cats.add(catToAdd)
  }
}