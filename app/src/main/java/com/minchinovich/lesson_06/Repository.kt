package com.minchinovich.lesson_06

public class Repository() {
    private val loginInDB = "User"
    private val passwordInDB = "root"

    fun checkUser(login : String, password : String): Boolean{
        return login == loginInDB && password == passwordInDB
    }

    fun addUser(login: String, password: String): Boolean {
        return true
    }
}