package com.example.minhassenhas.adapters

import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minhassenhas.CreateDB
import com.example.minhassenhas.UpdateActivity
import com.example.minhassenhas.databinding.PasswordListItemBinding

class CustomCursorAdapter(private var cursor: Cursor) :
    RecyclerView.Adapter<CustomCursorAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PasswordListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PasswordListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        val site = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.SITE))
        val user = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.USUARIO))
        val senha = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.SENHA))

        holder.binding.siteTextView.text = site
        holder.binding.userTextView.text = user

        // Inicializar com senha escondida
        holder.binding.senhaTextView.text = "*".repeat(senha.length)

        // Alternar a visibilidade da senha ao clicar
        holder.binding.senhaTextView.setOnClickListener {
            if (holder.binding.senhaTextView.text.toString() == "*".repeat(senha.length)) {
                holder.binding.senhaTextView.text = senha
            } else {
                holder.binding.senhaTextView.text = "*".repeat(senha.length)
            }
        }

        holder.itemView.setOnClickListener {
            val site = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.SITE))
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java)
            intent.putExtra("SITE_KEY", site)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = cursor.count

    fun swapCursor(newCursor: Cursor?) {
        if (newCursor != null) {
            cursor.close()
            cursor = newCursor
            notifyDataSetChanged()
        }
    }
}
