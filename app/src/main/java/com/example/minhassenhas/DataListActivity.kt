package com.example.minhassenhas

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minhassenhas.databinding.ActivityListDataBinding
import com.example.minhassenhas.adapters.CustomCursorAdapter  // Assuming you create this adapter

class DataListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListDataBinding
    private lateinit var dbController: DatabaseController
    private lateinit var cursor: Cursor
    private lateinit var adapter: CustomCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbController = DatabaseController(applicationContext)
        cursor = dbController.loadData()!! // Assuming loadData returns a Cursor

        binding.rvData.layoutManager = LinearLayoutManager(this)
        adapter = CustomCursorAdapter(cursor)
        binding.rvData.adapter = adapter

        binding.butaoVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        cursor = dbController.loadData()!!
        adapter.swapCursor(cursor) // Update the adapter with the new data
    }
}
