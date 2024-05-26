package com.example.minhassenhas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.minhassenhas.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up your registration logic here
        binding.butaoApagar.setOnClickListener {
            // Get the email and password from the EditTexts
            val site = binding.etSite.text.toString()
            val user = binding.etUser.text.toString()
            val senha = binding.etSenha.text.toString()
            // TODO: Add validation and registration logic here
        }

        binding.butaoAtualizar.setOnClickListener {
            // Get the email and password from the EditTexts
            val site = binding.etSite.text.toString()
            val user = binding.etUser.text.toString()
            val senha = binding.etSenha.text.toString()
            // TODO: Add validation and registration logic here
            }
        binding.butaoVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            }
        }
}