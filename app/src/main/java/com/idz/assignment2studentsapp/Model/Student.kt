package com.idz.assignment2studentsapp.Model

data class Student(
    var name:String,
    var id:String,
    var phone:String = "",
    var address:String = "",
    var isChecked:Boolean
)
