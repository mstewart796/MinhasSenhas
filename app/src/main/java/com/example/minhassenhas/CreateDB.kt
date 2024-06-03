package com.example.minhassenhas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CreateDB(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    companion object {
        const val DB_NAME = "bank.db"
        const val TABLE = "senhas"
        const val ID = "_id"
        const val SITE = "site"
        const val USUARIO = "usuário"
        const val SENHA = "senha"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {

        val sql = String.format(
                "CREATE TABLE %s (%s integer primary key autoincrement, %s text, %s text, %s text)",
        TABLE, ID, SITE, USUARIO, SENHA
        )
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }
}