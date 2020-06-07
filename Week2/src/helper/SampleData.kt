package helper

import model.animals.Cat
import model.cafe.Cafe
import model.cafe.Product
import model.people.Employee
import model.people.Person
import model.shelter.Shelter

class SampleData() {
  companion object {
    val firstNames = arrayOf(
      "Liam", "Emma", "Noah", "Olivia", "William", "Ava", "James", "Isabella", "Oliver",
      "Sophia", "Benjamin", "Charlotte", "Elijah", "Mia", "Lucas", "Amelia", "Mason", "Harper", "Logan", "Evelyn"
    )

    val lastNames = arrayOf(
      "Morrison",
      "Lambert",
      "Rowe",
      "Ferrell",
      "Henderson",
      "Joseph",
      "Hughes",
      "Stewart",
      "McCall",
      "Woodward",
      "Forbes",
      "Lawrence",
      "Wyatt",
      "McFarland",
      "LeBlanc",
      "Pollard",
      "Hurst",
      "Ingram",
      "Anthony",
      "Morales"
    )

    //create list of Persons
    fun createPersonsList(): List<Person> {
      val personList = mutableListOf<Person>()
      for (firstName in firstNames) {
        for (lastName in lastNames) {
          personList.add(
            Person(
              firstName = firstName, lastName = lastName, phoneNumber = "555-111-2222",
              email = "${firstName}.${lastName}@gmail.com"
            )
          )
        }
      }
      return personList
    }


    val shelterList = mutableListOf<Shelter>()

    fun createShelters(): List<Shelter> {
      shelterList.add(
        Shelter(
          name = "Shelter One", address = "187 Biscayne Dr", city = "Albany", state = "NY",
          zipCode = "12202", phoneNumber = "555-345-1212"
        )
      )
      shelterList.add(
        Shelter(
          name = "Shelter Two", address = "21 Jump St.", city = "Albany", state = "NY",
          zipCode = "12203", phoneNumber = "969-123-5555"
        )
      )
      return shelterList
    }


    val catList = mutableListOf<Cat>()
    fun createCats(): List<Cat> {
      val catOne = Cat(
        name = "Morris",
        breed = "Tabby tomcat",
        color = "Orange",
        gender = "Male",
        notes = "The world's most finicky cat",
        shelterId = shelterList[0].id
      )
      catList.add(catOne)
      val catTwo = Cat(
        name = "Orangey",
        breed = "Toyger",
        color = "Brown, Black",
        gender = "Male",
        notes = "They're gr-r-reat!",
        shelterId = shelterList[0].id
      )
      catList.add(catTwo)
      val catThree = Cat(
        name = "Lewis Carol",
        breed = "Cheshire",
        color = "Purple",
        gender = "Male",
        notes = "Enjoys smiling",
        shelterId = shelterList[1].id
      )
      catList.add(catThree)
      val catFour = Cat(
        name = "Orangey",
        breed = "Marmalade Tabby",
        color = "Orange",
        gender = "Male",
        notes = "Enjoy's breakfast at Tiffany's",
        shelterId = shelterList[1].id
      )
      catList.add(catFour)
      val catFive = Cat(
        name = "Choupette",
        breed = "Birman",
        color = "Blue-Cream Tortie",
        gender = "Female",
        notes = "Only eats Strottarga Bianco Caviar",
        shelterId = shelterList[1].id
      )
      catList.add(catFive)
      return catList
    }

    fun addCatsToShelter(
      shelterList: List<Shelter> = createShelters(),
      catList: List<Cat> = createCats()
    ): List<Shelter> {
      for (shelter in shelterList) {
        for (cat in catList) {
          if (cat.shelterId == shelter.id) {
            shelter.cats.add(cat)
          }
        }
      }
      return shelterList
    }

    val productList: MutableList<Product> = mutableListOf()

    fun createProducts(): List<Product> {
      productList.add(Product(desc = "Cappuccino", price = 2.20))
      productList.add(Product(desc = "Bagel", price = 9.50))
      productList.add(Product(desc = "Cat Sponsorship", price=100.0))
      productList.add(Product(desc= "Cat Adoption", price=500.0))
      return productList
    }

    fun createEmployees(personList: List<Person>): List<Employee> {
      val employeeList: MutableList<Employee> = mutableListOf()
      for (counter in 1..3) {
        val person = employeeList.get(counter)
        employeeList.add(
          Employee(
            person.firstName, person.lastName, person.email, person.phoneNumber,
            12.0, "111-111-1111", "06/07/2020"
          )
        )
      }
      return employeeList
    }

//    fun createReceipts() {
//      val daysOfWeek = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
//      daysOfWeek.forEach {
//        for (i in 1..20) {
//          CafeController.
//        }
//      }
//    }
  }
}
