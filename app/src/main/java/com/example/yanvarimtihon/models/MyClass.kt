package com.example.yanvarimtihon.models

class MyClass {
    var id:Int? = null
    var note:String? = null
    var date:String? = null
    var check:Int? = 0

    constructor(note: String?, date: String?, check: Int?) {
        this.note = note
        this.date = date
        this.check = check
    }

    constructor(id: Int?, note: String?, date: String?, check: Int) {
        this.id = id
        this.note = note
        this.date = date
        this.check = check
    }

    override fun toString(): String {
        return "MyClass(id=$id, note=$note, date=$date, check=$check)"
    }


}