package com.minchinovich.lesson_06

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.minchinovich.lesson_06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val loginSuccess by lazy { getString(R.string.login_success) }
    private val registrationSuccess by lazy { getString(R.string.registration_success) }
    private val loginFailing by lazy { getString(R.string.login_failing) }
    private val registrationFailing by lazy { getString(R.string.registration_failing) }
    private val messageLoginEmpty by lazy { getString(R.string.message_login_empty) }
    private val messagePasswordEmpty by lazy { getString(R.string.message_password_empty) }

    private lateinit var binding: ActivityMainBinding
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
            if (checkInputFields()) {
                val login = binding.loginEditText.text.toString().trim()
                val password = binding.passwordEditText.text.toString()
                loginAction(viewModel.checkUser(login, password))
            }
        }

        binding.registerButton.setOnClickListener {
            if (checkInputFields()) {
                val login = binding.loginEditText.text.toString().trim()
                val password = binding.passwordEditText.text.toString()
                registrationAction(viewModel.registerUser(login, password))
            }
        }
    }

    private fun checkInputFields(): Boolean {
        if (binding.loginEditText.text.toString().trim().isEmpty()) {
            binding.loginEditText.error = messageLoginEmpty
            binding.loginEditText.requestFocus()
            return false
        }
        if (binding.passwordEditText.text.toString().isEmpty()) {
            binding.passwordEditText.error = messagePasswordEmpty
            binding.passwordEditText.requestFocus()
            return false
        }
        return true
    }

    private fun loginAction(isSuccessful: Boolean) {
        if (isSuccessful) {
            Toast.makeText(this, loginSuccess, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, loginFailing, Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrationAction(isSuccessful: Boolean) {
        if (isSuccessful) {
            Toast.makeText(this, registrationSuccess, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, registrationFailing, Toast.LENGTH_SHORT).show()
        }
    }
}