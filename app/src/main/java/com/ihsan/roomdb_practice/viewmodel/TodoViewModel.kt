package com.ihsan.roomdb_practice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ihsan.roomdb_practice.db.TodoDatabase
import com.ihsan.roomdb_practice.model.*
import com.ihsan.roomdb_practice.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    val readAllTodo: LiveData<List<Todo>>
    val readAllTodoRight: LiveData<List<Todo>>
    private val repository: TodoRepository

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllTodo = repository.readAllTodo
        readAllTodoRight = repository.readAllTodoRight
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todo.status="deleted"
            repository.updateTodo(todo)
        }
    }

    fun deleteAllTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTodo()
        }
    }


    fun deleteAllTodoRight() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTodoRight()
        }
    }

    fun addImage(image:ImageAttachment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addImage(image)
        }
    }

    fun deleteImage(image: ImageAttachment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteImage(image)
        }
    }
}