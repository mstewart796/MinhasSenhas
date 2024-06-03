package com.example.minhassenhas

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DatabaseController(context: Context) {

    private val database: CreateDB
    private val writableDatabase: SQLiteDatabase
    private val readableDatabase: SQLiteDatabase

    init {
        database = CreateDB(context)
        writableDatabase = database.writableDatabase
        readableDatabase = database.readableDatabase
    }

    fun insertData(site: String, usuario: String, senha: String): String {
        val values = ContentValues()
        values.put(CreateDB.SITE, site)
        values.put(CreateDB.USUARIO, usuario)
        values.put(CreateDB.SENHA, senha)
        val result = writableDatabase.insert(CreateDB.TABLE, null, values)
        return if (result == -1L) "Erro inserindo dados" else "Dados inseridos com sucesso"
    }

    fun loadData(): Cursor? {
        val fields = arrayOf(CreateDB.ID, CreateDB.SITE)
        val cursor = readableDatabase.query(CreateDB.TABLE, fields, null, null,
            null, null, null, null)
        cursor.moveToFirst()
        return cursor
    }
}