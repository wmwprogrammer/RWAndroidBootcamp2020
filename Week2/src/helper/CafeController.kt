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
      "1", "Shelter One", "", "", "", "", ""),
    Shelter(
      "2", "Shelter Two", "", "", "", "", "")
  )

  private val shelterToCat = mutableMapOf<Shelter, MutableSet<Cat>>(
    shelters.elementAt(0) to mutableSetOf(
      Cat("1", "Cat One", "Tabby", "Orange", "Male", "1", ""),
      Cat("2", "Cat Two", "Toyger", "brown/black", "Female", "1", ""),
      Cat("6", "Cat Six", "Toyger", "brown/black", "Female", "1", "")
    ),
    shelters.elementAt(1) to mutableSetOf(
      Cat("3", "Cat Three", "Cheshire", "Purple", "Female", "2", ""),
      Cat("4", "Cat Four", "Tabby", "orange", "Female", "2", ""),
      Cat("5", "Cat Five", "Birman", "blue-cream tortie", "Female", "2", "")
    )
  )
  
//  val breeds = shelterToCat.get(shelters.first())?.fold(mutableSetOf<String>()) { acc, cat ->
//    acc.add(cat.breed)
//    return@fold acc
//  }

  fun adoptCat(catId: String, personId: String) {
    val person = cafe.getPotentialAdopter(personId)
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
    val shelterMap: MutableMap<String, Int> = mutableMapOf()
    val allAdopters = cafe.getAdopters()
    if (allAdopters.isEmpty()) return mapOf()
    for (adopter in allAdopters) {
      val catsCount = adopter.cats.size
      for (cat in adopter.cats) {
        val shelterList = shelters.filter { it.id == cat.shelterId }
        shelterList.forEach { shelterMap.put(it.name, catsCount) }
      }
    }
    return shelterMap
  }

  fun getUnadoptedCats(): Set<Cat> {
    val unadoptedCats = mutableSetOf<Cat>()
    shelterToCat.forEach { unadoptedCats.addAll(it.value)}
    return unadoptedCats
  }

  fun getSponsoredUnadoptedCats(): MutableSet<Cat> {
    val sponsoredCatids = cafe.getSponsoredCatsIds()
    val sponsoredCats = mutableSetOf<Cat>()
    shelterToCat.forEach {
      it.value.forEach() {
        if (it.id in sponsoredCatids) sponsoredCats.add(it)}
    }
    return sponsoredCats
  }

  fun getPopularItems(): Set<Product> {
    return cafe.getTopSellingItems()

  }

  fun getPopularCats(): Set<Cat> {
    return cafe.getMostPopularCats()
  }
}