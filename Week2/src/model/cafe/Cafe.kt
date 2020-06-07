package model.cafe

import model.animals.Cat
import model.people.Employee
import model.people.Person
import java.time.LocalDate
import java.time.format.TextStyle

class Cafe {


  // cafe related stuff

  // maybe as employees check in, you can add them to the list of working employees!
  private val employees = mutableSetOf<Employee>(
    Employee("1", "Liam", "Morrison", "", "", 15.0, "", "5/1/2015"),
    Employee("2", "Emma", "Lambert", "", "", 12.0, "", "1/1/2020"),
    Employee("3", "Noah", "Rowe", "", "", 12.0, "", "6/1/2020")
  )
  private val customers = mutableSetOf<Person>(
    Person(id = "4", firstName = "Olivia", lastName = "Ferrell", phoneNumber = "", email = ""),
    Person(id = "5", firstName = "William", lastName = "Henderson", phoneNumber = "", email = ""),
    Person(id = "6", firstName = "Ava", lastName = "Joseph", phoneNumber = "", email = ""),
    Person(id = "7", firstName = "James", lastName = "Hughes", phoneNumber = "", email = ""),
    Person(id = "8", firstName = "Isabella", lastName = "Stewart", phoneNumber = "", email = "")
  )

  /**
   * To simplify it, let's imitate a week-long cafe turnaround.
   *
   * Make sure to add your receipts to each set, with your data.
   * */
  private val receiptsByDay = mutableMapOf(
    "Monday" to mutableSetOf<Receipt>(
      Receipt(
        "1", "1",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20))
      ),
      Receipt(
        "2", "2",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "3", "3",
        mutableListOf(Product("2", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "4", "4",
        mutableListOf(Product("1", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "5", "6",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      )
    ),
    "Tuesday" to mutableSetOf<Receipt>(
      Receipt(
        "6", "1",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20))
      ),
      Receipt(
        "7", "2",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "8", "3",
        mutableListOf(Product("2", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "9", "5",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "10", "6",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      )
    ),
    "Wednesday" to mutableSetOf<Receipt>(
      Receipt(
        "11", "1",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20))
      ),
      Receipt(
        "12", "2",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "13", "3",
        mutableListOf(Product("2", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "14", "4",
        mutableListOf(Product("1", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "15", "7",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      )
    ),
    "Thursday" to mutableSetOf<Receipt>(
      Receipt(
        "16", "1",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20))
      ),
      Receipt(
        "17", "2",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "18", "3",
        mutableListOf(Product("2", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "19", "4",
        mutableListOf(Product("1", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "20", "7",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      )
    ),
    "Friday" to mutableSetOf<Receipt>(
      Receipt(
        "21", "1",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20))
      ),
      Receipt(
        "22", "2",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "23", "3",
        mutableListOf(Product("2", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "24", "4",
        mutableListOf(Product("1", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "25", "6",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      )
    ),
    "Saturday" to mutableSetOf<Receipt>(
      Receipt(
        "26", "1",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20))
      ),
      Receipt(
        "27", "2", mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "28", "3",
        mutableListOf(Product("2", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "29", "7",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "30", "8",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      )
    ),
    "Sunday" to mutableSetOf<Receipt>(
      Receipt(
        "31", "1",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20))
      ),
      Receipt(
        "32", "2",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "33", "3",
        mutableListOf(Product("2", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "34", "4",
        mutableListOf(Product("1", "Cappuccino", price = 2.20))
      ),
      Receipt(
        "35", "5",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "36", "6",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "37", "7",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      ),
      Receipt(
        "38", "8",
        mutableListOf(Product(desc = "Cappuccino", price = 2.20), Product(desc = "Bagel", price = 9.50))
      )
    )
  )

  // make sure to add sponsorships and tie them to people!
  private val sponsorships = mutableSetOf<Sponsorship>(
    Sponsorship("4", "1"),
    Sponsorship("5", "2"),
    Sponsorship("6", "2"),
    Sponsorship("7", "2"),
    Sponsorship("8", "2"),
    Sponsorship("1", "3"),
    Sponsorship("2", "3"),
    Sponsorship("3", "3")
  )

  fun checkInEmployee(employee: Employee) {
    employee.clockIn()
  }

  fun checkOutEmployee(employee: Employee) {
    employee.clockOut()
  }

  fun showNumberOfReceiptsForDay(day: String) {
    val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!

    println("On $day you made ${receiptForDay.size} transactions!")
  }

  fun getReceipt(items: List<Product>, customerId: String): Receipt {
    for (item in items) {
      if (isEmployee(customerId) && !item.desc.contains("Cat")) {
        item.price = item.price * .85
      }
    }
    val today: String = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, java.util.Locale.ENGLISH)
    val receipt = Receipt(customerId = customerId, products = items.toMutableList())

    receiptsByDay[today]?.add(receipt)
    return receipt
  }

  fun addSponsorship(catId: String, personId: String) {
    sponsorships.add(Sponsorship(catId, personId))
  }

  fun getWorkingEmployees(): Set<Employee> = employees

  fun getAdoptedCats(): Set<Cat> {
    return setOf()
    //TODO fix to return actual data
  }

  fun getSponsoredCats(): Set<Cat> {
    return setOf()
    //TODO fix to return actual data
  }

  fun getMostPopularCats(): Set<Cat> {
    return setOf()
    //TODO fix to return actual data
  }

  fun getTopSellingItems(): Set<Product> {
    return setOf()
    //TODO fix to return actual data
  }

  fun getAdopters(): List<Person> {
    return (employees + customers).filter { it.cats.isNotEmpty() }
  }

  fun printNumberOfCustomersFor(day: String) {
    var employeeCount: Int = 0
    var patronCount: Int = 0
    val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!


    for (receipt in receiptForDay) {
      val id = receipt.customerId
        patronCount++
      if (isEmployee(id)) {
        employeeCount++
      }
    }
    println("On $day you had ${patronCount} customers. \n" +
        "\t${employeeCount} of which were employees")
  }

  fun printNumberOfNonEmployeePatronsFor(day: String) {
    var patronCount: Int = 0
    val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!


    for (receipt in receiptForDay) {
      val id = receipt.customerId
      if (!isEmployee(id)) {
        patronCount++
      }
    }
    println("On $day you had ${patronCount} non-employee customers.")
  }

  fun isEmployee(id: String): Boolean {
    employees.forEach {
      if (it.id == id) return true
    }
    return false
  }
}