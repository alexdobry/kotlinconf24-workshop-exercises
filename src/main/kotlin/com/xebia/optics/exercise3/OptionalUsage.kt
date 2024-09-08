package com.xebia.com.xebia.optics.exercise3

import arrow.optics.dsl.notNull
import arrow.optics.optics

@optics
data class Person(val name: String, val age: Int, val address: Address?) {
  companion object
}

@optics
data class Address(val street: Street, val city: City) {
  companion object
}

@optics
data class Street(val name: String, val number: Int?) {
  companion object
}

@optics
data class City(val name: String, val country: String) {
  companion object
}

fun Person.removeAddress(): Person =
  Person.address.set(this, null)

fun Person.changeNumber(newNumber: Int): Person =
  Person.address.notNull.street.number.set(this, newNumber)

fun main() {
  val person0 =
    Person(
      "KotlinConf Attendee",
      0,
      Address(
        Street("Center Blvd.", 1),
        City("København", "Denmark")
      )
    )

  val person2 = person0.changeNumber(5)
  println(person2.address?.street?.number == 5)

  val person1 = person0.removeAddress()
  println(person1.address == null)
}