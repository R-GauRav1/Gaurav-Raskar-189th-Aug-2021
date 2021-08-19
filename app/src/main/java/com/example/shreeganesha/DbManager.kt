package com.example.shreeganesha

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast
import java.nio.ByteOrder

class DbManager {

     val dbName = "My Players"
    val dbTable = "Players"
    var id = "ID"
    var playerName = "Name"
    var dbVersion = 1
    //CREATE TABLE IF NOT EXISTS(ID INTERGER PRIMARY NUMBER,NAME TEXT)

    val sqlcreatetable = "CREATE TABLE IF NOT EXISTS "+ dbTable+" ("+ id +" INTEGER PRIMARY KEY,"+ playerName+" TEXT);"
    var sqldb :SQLiteDatabase? = null

      constructor(context:Context){
      var db = DataBaseHelperPlayer(context)
      sqldb = db.writableDatabase

}

    inner class DataBaseHelperPlayer :SQLiteOpenHelper{
        override fun onCreate(db: SQLiteDatabase?) {

            db!!.execSQL(sqlcreatetable)
            Toast.makeText(this.context,"Database is Created",Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS"+dbTable)
        }
               var context:Context? = null
        constructor(context:Context):super(context,dbName,null,dbVersion){
            this.context=context
        }
    }
    fun insert(values: ContentValues):Long{
        val ID = sqldb!!.insert(dbTable,"",values)
        return ID
    }
    fun Query(projection: Array<String>,selection:String,selectionArgs: Array<String>,sortOrder: String):Cursor{
        val qb = SQLiteQueryBuilder()
        qb.tables = dbTable
        val Cursor = qb.query(sqldb,projection,selection,selectionArgs,null,null,sortOrder)
        return Cursor


    }
    fun Delete (selection:String,selectionArgs: Array<String>): Int{

        var count = sqldb!!.delete(dbTable,selection,selectionArgs)
        return count

    }
}