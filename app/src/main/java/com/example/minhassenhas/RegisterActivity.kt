package com.example.minhassenhas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minhassenhas.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butaoRegister.setOnClickListener {
            // Get the site, username, and password
            val site = binding.etSite.text.toString()
            val user = binding.etUser.text.toString()
            val senha = binding.etSenha.text.toString()

            // Input validation (add your own checks here)
            if (site.isBlank() || user.isBlank() || senha.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Don't proceed if fields are empty
            }

            // Insert data into the database
            val dbController = DatabaseController(applicationContext)
            val result = dbController.insertData(site, user, senha)

            // Display a message to the user
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

            // Clear the input fields
            binding.etSite.text.clear()
            binding.etUser.text.clear()
            binding.etSenha.text.clear()

            // (Optional) Navigate to another activity
            // Example:
            // val intent = Intent(this, PasswordListActivity::class.java)
            // startActivity(intent)
        }

        binding.butaoVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
