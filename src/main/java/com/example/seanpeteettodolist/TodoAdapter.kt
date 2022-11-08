package com.example.seanpeteettodolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

//Creating classes to hold and populate items in the recycler view for the second page on app screen
class TodoAdapter (
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    // function will create the viewholder. Adding parameters of the viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false,
            )
        )
    }

    // function will add a to-do item to our list. Making sure it's added to bottom of list
    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    // creating function to delete done to-do items
    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    // Function to create strike through of when it is checked off to-do list or take away
    // the strike through if it is not yet checked
    private fun toggleStrikeThrough(textViewTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            textViewTodoTitle.paintFlags = textViewTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            textViewTodoTitle.paintFlags  = textViewTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    // Function will bind data from to-do list to the view of the list. Function will also use the
    // funcionaility from function toggleStrikeTrhough to make sure text has a strike-through or not
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            textViewTodoTitle.text = curTodo.title
            checkBoxDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(textViewTodoTitle, curTodo.isChecked)
            checkBoxDone.setOnCheckedChangeListener { _ , isChecked ->
                toggleStrikeThrough(textViewTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }

    }

    // Returns the amount of items that are in the list
    override fun getItemCount(): Int {
        return todos.size
    }
}