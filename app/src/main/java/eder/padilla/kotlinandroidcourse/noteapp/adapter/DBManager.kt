package eder.padilla.kotlinandroidcourse.noteapp.adapter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 * Created by ederpadilla on 14/06/17.
 */
class DBManager {
    val dbName ="MyNotes"
    val dbTable = "Notes"
    val colId = "ID"
    val colTitle ="Title"
    val colDes = "Description"
    val dbVersion = 1
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS $dbTable ( $colId INTEGER PRIMARY KEY, $colTitle TEXT, $colDes TEXT);"

    var sqlDB : SQLiteDatabase? = null
    constructor(context : Context){
        val db = DataBaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }
    inner class DataBaseHelperNotes : SQLiteOpenHelper {

        var context : Context?=null

        constructor(context : Context):super(context,dbName,null,dbVersion){
            this.context=context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(context,"Database is created",Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
           db!!.execSQL("Drop table IF EXISTS $dbTable ")
        }


    }

    fun insert(values : ContentValues) : Long{
        val ID =sqlDB!!.insert(dbTable,"",values)
        return ID
    }
}