package com.example.yanvarimtihon.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.yanvarimtihon.models.MyClass

class MyDbHelper(context: Context): SQLiteOpenHelper(context,"Codial",null,1),MyDbInterface {
    override fun onCreate(p0: SQLiteDatabase?) {
        val table =
            "create table Note (id integer not null primary key autoincrement unique, name text not null, date text not null,done boolean not null)"

        p0?.execSQL(table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    override fun addNote(myClass: MyClass) {
        val dataBase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name",myClass.note)
        contentValues.put("date",myClass.date)
        contentValues.put("done",myClass.check)
        dataBase.insert("Note",null,contentValues)
        dataBase.close()
    }

    override fun getAllNotes(): List<MyClass> {
        val list = ArrayList<MyClass>()
        val dataBase = this.readableDatabase
        val queryf = "select * from Note"
        val cursor = dataBase.rawQuery(queryf,null)

        if (cursor.moveToFirst()){
            do {
                val kursClass = MyClass(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
                )
                list.add(kursClass)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun deleteNotes(myClass: MyClass) {
        val database = this.writableDatabase
        database.delete("Note","id=?", arrayOf(myClass.id.toString()))
        database.close()
    }
}