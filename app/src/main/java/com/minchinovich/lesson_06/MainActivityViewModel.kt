package com.minchinovich.lesson_06

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val repository = Repository()

    fun checkUser(login : String, password : String): Boolean{
        return repository.checkUser(login, password)
    }

    fun registerUser(login: String, password: String): Boolean{
        return repository.addUser(login, password)
    }
}

