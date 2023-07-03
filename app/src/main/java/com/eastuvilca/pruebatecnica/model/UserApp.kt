package com.eastuvilca.pruebatecnica.model

class UserApp {
    var email = ""
    var firstName = ""
    var lastName = ""
    var password = ""

    fun getName(): String{
        return "$firstName $lastName"
    }
}