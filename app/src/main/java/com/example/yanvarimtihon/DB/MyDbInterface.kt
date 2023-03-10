package com.example.yanvarimtihon.DB

import com.example.yanvarimtihon.models.MyClass

interface MyDbInterface {
    fun addNote(myClass: MyClass)
    fun getAllNotes():List<MyClass>
    fun deleteNotes(myClass: MyClass)
}