package com.example.minhassenhas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.minhassenhas.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var dbController: DatabaseController
    private var site: String? = null // Declare 'site' as a class-level variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize dbController instance
        dbController = DatabaseController(applicationContext)

        // Retrieve the site name from the Intent
        site = intent.getStringExtra("SITE_KEY")

        // Load data from the database based on the site name
        val cursor = site?.let { dbController.loadDataBySite(it) }

        // Update UI with the retrieved data
        if (cursor != null && cursor.moveToFirst()) {
            binding.etSite.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.SITE)))
            binding.etUser.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.USUARIO)))
            binding.etSenha.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.SENHA)))
        }

        // Set up click listeners for the buttons

        binding.butaoApagar.setOnClickListener {
            site?.let {
                dbController.deleteDataBySite(it)
                Toast.makeText(this, "Senha Apagada", Toast.LENGTH_SHORT).show()
                // Navigate back to the list or main activity
                val intent = Intent(this, DataListActivity::class.java)
                startActivity(intent)
            }
        }

        binding.butaoAtualizar.setOnClickListener {
            val newUsuario = binding.etUser.text.toString()
            val newSenha = binding.etSenha.text.toString()

            site?.let {
                dbController.updateDataBySite(it, newUsuario, newSenha)
                Toast.makeText(this, "Senha Atualizada", Toast.LENGTH_SHORT).show()
                // Navigate back to the list or main activity
                val intent = Intent(this, DataListActivity::class.java)
                startActivity(intent)
            }
        }

        binding.butaoVoltar.setOnClickListener {
            val intent = Intent(this, DataListActivity::class.java)
            startActivity(intent)
        }
    }
}
