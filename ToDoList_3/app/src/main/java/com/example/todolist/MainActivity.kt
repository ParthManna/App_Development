package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapters.TodoAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.models.Todo

class MainActivity : AppCompatActivity() {

    private val todos: ArrayList<Todo> = ArrayList()
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize adapter and set it to RecyclerView
        todoAdapter = TodoAdapter(todos)
        binding.rvTodos.layoutManager = LinearLayoutManager(this)
        binding.rvTodos.adapter = todoAdapter

        // Add initial task
        todos.add(Todo("First Task", false))
        todoAdapter.notifyItemInserted(todos.size - 1)

        // Handle Add Button click event
        binding.btnAdd.setOnClickListener {
            val newTodo = binding.etNewTodo.text.toString().trim()

            if (newTodo.isNotEmpty()) {
                // Add new task
                todos.add(Todo(newTodo, false))
                todoAdapter.notifyItemInserted(todos.size - 1)

                // Clear input field
                binding.etNewTodo.text.clear()
            }
        }

        // Handle Clear Button click event
        binding.btnClear.setOnClickListener {
            // Clear all tasks
            todos.clear()
            todoAdapter.notifyDataSetChanged()
        }
    }
}
