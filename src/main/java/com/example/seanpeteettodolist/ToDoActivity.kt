package com.example.seanpeteettodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_to_do.*

class ToDoActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)
        todoAdapter = TodoAdapter(mutableListOf())

        // creating items that help arrange the to-do items within the list
        recyclerViewToDoItems.adapter = todoAdapter
        recyclerViewToDoItems.layoutManager = LinearLayoutManager(this)

        // assign onclick listeners to the buttons within the second page on app
        buttonAddToDo.setOnClickListener {
            val todoTitle = editTextToDo.text.toString()
            //checking is to-do item is empty, do not want to add empty item to to-do list
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                editTextToDo.text.clear()
            }
        }
        // Creating functionality to delete a done/checked to-do item
        buttonDeleteToDo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}