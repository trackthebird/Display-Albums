package com.trackthebird.displayalbum.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val phone: String,
    val website: String,
    val email: String,
    val address: Address,
    val company: Company,
)

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)

data class Geo(
    val lat: String,
    val lng: String
)