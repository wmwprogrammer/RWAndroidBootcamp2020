import helper.CafeController
import helper.SampleData
import model.animals.Cat
import model.shelter.Shelter

fun main() {
    //populate Sample Data
    val personsList = SampleData.createPersonsList()
    val shelterList = SampleData.addCatsToShelter()
    val productList = SampleData.createProducts()

    //create employees
    val employeeList = SampleData.createEmployees(personsList)



    val cafeController = CafeController() // print out the data here using CafeController functions
}