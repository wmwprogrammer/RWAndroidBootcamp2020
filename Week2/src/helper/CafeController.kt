package helper

import model.animals.Cat
import model.cafe.Cafe
import model.cafe.Product
import model.people.Person
import model.shelter.Shelter


class CafeController {
  // cafe related things
  private val cafe = Cafe()

  // shelter related things
  private val shelters = mutableSetOf<Shelter>(
    Shelter(
      "1", "Shelter One", "", "", "", "", "",
      mutableListOf(
        Cat("1", "Cat One", "Tabby", "Orange", "Male", "1", ""),
        Cat("2", "Cat Two", "Toyger", "brown/black", "Female", "1", "")

      )
    ),
    Shelter(
      "2", "Shelter Two", "", "", "", "", "",
      mutableListOf(
        Cat("3", "Cat Three", "Cheshire", "Purple", "Female", "1", ""),
        Cat("4", "Cat Four", "Tabby", "orange", "Female", "2", ""),
        Cat("5", "Cat Five", "Birman", "blue-cream tortie", "Female", "2", "")
      )
    )
  )
  private val shelterToCat = mutableMapOf<Shelter, MutableSet<Cat>>()

  fun adoptCat(catId: String, person: Person) {
    // check if cats exist, and retrieve its entry!
    val catInShelter = shelterToCat.entries.firstOrNull { (_, catsInShelter) ->
      catsInShelter.any { it.id == catId }
    }

    // you can adopt that cat!
    if (catInShelter != null) {
      val cat = catInShelter.value.first { cat -> cat.id == catId } // find the cat for that ID again

      // remove the cat from the shelter
      catInShelter.value.remove(cat)

      // add the cat to the person
      person.cats.add(cat)
    }
  }

  fun printReceiptsFor(day: String) {
    return cafe.showNumberOfReceiptsForDay(day)
  }

  fun printTheNumberOfCustomersFor(day: String) {
    return cafe.printNumberOfCustomersFor(day)
  }

  fun printTheNumberOfNonEmployeePatronsFor(day: String) {
    return cafe.printNumberOfNonEmployeePatronsFor(day)
  }

  fun sellItems(items: List<Product>, customerId: String) {
    val receipt = cafe.getReceipt(items, customerId)
    println(
      "You purchased: ${receipt.products.size} items:\n" +
          "\t${receipt.products}\n" +
          "\tYour total is: ${receipt.total}"
    )
  }

  /**
   * First gets a list of all adopters, then queries shelters.
   *
   * */
  fun getNumberOfAdoptionsPerShelter(): Map<String, Int> {
    val allAdopters = cafe.getAdopters()
    if (allAdopters.isEmpty()) return mapOf()

    return mapOf()
  }

  fun getUnadoptedCats(): Set<Cat> {
    // TODO finish the method to actually return unadopted cats
    return setOf();
  }


}