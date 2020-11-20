package com.fouadbahari.kotlin

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter (
    private val todos: MutableList<Todo>
):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item ,
                parent,
                false)
        )
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)

    }


    fun deleteDoneTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(txt_todo_item: TextView, isCheked: Boolean){
        if (isCheked){
            txt_todo_item.paintFlags = txt_todo_item.paintFlags  or STRIKE_THRU_TEXT_FLAG
        }else{
            txt_todo_item.paintFlags = txt_todo_item.paintFlags  and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            txt_todo_item.text = currentTodo.title
            check_item.isChecked = currentTodo.isChecked
            toggleStrikeThrough(txt_todo_item,currentTodo.isChecked)
            check_item.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(txt_todo_item,isChecked)
                 currentTodo.isChecked = !currentTodo.isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}