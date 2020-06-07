import helper.CafeController
import model.cafe.Product
import model.cafe.Receipt
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import kotlin.test.assertTrue

fun main() {

    val cafeController = CafeController() // print out the data here using CafeController functions

    //print the total number of transactions for that business day
    val today = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    cafeController.printReceiptsFor(today)

    //print the total number of customers (both employees who bought stuff and regular customers) for the day
    cafeController.printTheNumberOfCustomersFor(today)

    //print the total number of non-employee patrons
    cafeController.printTheNumberOfNonEmployeePatronsFor(today)

    //if the purchaser is an employee, a 15% discount is applied for food and drink
    println(cafeController.sellItems(listOf(Product("3", "Espresso", 4.0)),"1"))
    //print the total number of transactions for that business day
    cafeController.printReceiptsFor(today)

    //no discount is applied for an employee adopting a cat
    println(cafeController.sellItems(listOf(Product("4", "Cat Sponsorship", 100.0)),"1"))

    //determine the number of adoptions per shelter
    println(cafeController.getNumberOfAdoptionsPerShelter())

}