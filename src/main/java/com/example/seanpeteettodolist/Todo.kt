package com.example.seanpeteettodolist

// Simple class to hold the value of the to-do title and see if the box next to the to-do name is
// checked or not
data class Todo (
    val title: String,
    var isChecked: Boolean = false
)