package model.people

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit.MINUTES

class Employee(
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String,
    val salary: Double,
    val socialSecurityNumber: String,
    val hireDate: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {
        return "${firstName} ${lastName} ${hireDate} ${salary}"
    }

    var hoursWorkedThisWeek: Double = 0.0
    var timeClockedIn: LocalTime? = null
    /**
     * Prints a time of clocking in, in a nice format.
     *
     * Hint: to get time, you can create a `Date` object. Use SimpleDateFormatter to format the time!
     * */
    fun clockIn() {
        if (timeClockedIn == null) {
            timeClockedIn = LocalTime.now()
        }
    }

    fun clockOut() {
        if (timeClockedIn != null) {
            var timeWorked = MINUTES.between(LocalTime.now(), timeClockedIn)
            var hoursWorked = timeWorked / 60
            val minutesWorked = timeWorked - (hoursWorked * 60)
            val hourPortion = minutesWorked / 60
            hoursWorkedThisWeek += hoursWorked + hourPortion
        }
    }
}