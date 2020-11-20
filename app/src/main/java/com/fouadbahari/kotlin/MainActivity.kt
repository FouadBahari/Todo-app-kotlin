package com.fouadbahari.kotlin

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    private lateinit var todoAdapter:TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        recycler_todo.adapter = todoAdapter
        recycler_todo.layoutManager = LinearLayoutManager(this)

        add_btn.setOnClickListener{
            val todoTitle = edt_todo.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                edt_todo.text.clear()
            }
        }

        delete_btn.setOnClickListener{
            todoAdapter.deleteDoneTodo()
        }
    }
}