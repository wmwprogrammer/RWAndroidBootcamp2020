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
  var cats: MutableList<Cat> = mutableListOf(
//    Cat("1","Cat One", breed = "Tabby", color = "Orange", gender = "Male", notes = "", shelterId = id),
//    Cat("2", "Cat Two", breed = "Toyger", color = "brown/black", gender = "Female", notes = "", shelterId = id),
//    Cat(name = "Cat Three", breed = "Cheshire", color = "Purple", gender = "Female", notes = "", shelterId = id)
  )
) {

  fun addCat(catToAdd: Cat) {
    cats.add(catToAdd)
  }
}