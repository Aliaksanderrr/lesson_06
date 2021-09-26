package com.minchinovich.lesson_06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.minchinovich.lesson_06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.loginButton.setOnClickListener {
            if(checkInputFields()) {
                val login = binding.loginEditText.text.toString().trim()
                val password = binding.passwordEditText.text.toString()
                loginAction(viewModel.checkUser(login, password))
            }
        }

        binding.registerButton.setOnClickListener {
            if(checkInputFields()) {
                val login = binding.loginEditText.text.toString().trim()
                val password = binding.passwordEditText.text.toString()
                registrationAction(viewModel.registerUser(login, password))
            }
        }
    }

    private fun checkInputFields(): Boolean{
        if (binding.loginEditText.text.toString().trim().isNullOrEmpty()){
            binding.loginEditText.error = getString(R.string.message_login_enpty)
            binding.loginEditText.requestFocus()
            return false
        }
        if (binding.passwordEditText.text.toString().isNullOrEmpty()){
            binding.passwordEditText.error = getString(R.string.message_password_empty)
            binding.passwordEditText.requestFocus()
            return false
        }
        return true
    }

    private fun loginAction(isSuccessful: Boolean){
        if (isSuccessful){
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Not logged in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrationAction(isSuccessful: Boolean){
        if (isSuccessful){
            Toast.makeText(this, "Congratulations, you are a new user", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Sorry, something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}