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
        val fields = arrayOf(CreateDB.ID, CreateDB.SITE, CreateDB.USUARIO, CreateDB.SENHA)
        val cursor = readableDatabase.query(CreateDB.TABLE, fields, null, null,
            null, null, null, null)
        return cursor
    }

    fun loadDataBySite(site: String): Cursor? {
        val fields = arrayOf(CreateDB.ID, CreateDB.SITE, CreateDB.USUARIO, CreateDB.SENHA)
        val where = "${CreateDB.SITE} = ?"
        val whereArgs = arrayOf(site)
        val cursor = readableDatabase.query(CreateDB.TABLE, fields, where, whereArgs, null,
            null, null, null)
        return cursor
    }

    fun updateDataBySite(site: String, newUsuario: String, newSenha: String) {
        val values = ContentValues()
        values.put(CreateDB.USUARIO, newUsuario)
        values.put(CreateDB.SENHA, newSenha) // Assuming no hashing for simplicity

        val where = "${CreateDB.SITE} = ?"
        val whereArgs = arrayOf(site)

        writableDatabase.update(CreateDB.TABLE, values, where, whereArgs)
    }

    fun deleteDataBySite(site: String) {
        val where = "${CreateDB.SITE} = ?"
        val whereArgs = arrayOf(site)

        writableDatabase.delete(CreateDB.TABLE, where, whereArgs)
    }
}
