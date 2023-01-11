package com.ihsan.roomdb_practice.repository

import androidx.lifecycle.LiveData
import com.ihsan.roomdb_practice.dao.TodoDao
import com.ihsan.roomdb_practice.model.ImageAttachment
import com.ihsan.roomdb_practice.model.Todo

class TodoRepository(private val todoDao: TodoDao) {
    val readAllTodo:LiveData<List<Todo>> = todoDao.readAllTodo()
    val readAllTodoRight:LiveData<List<Todo>> = todoDao.readAllTodoRight()
    val readAllImageAttachment:LiveData<List<ImageAttachment>> = todoDao.readAllImages()

    suspend fun addTodo(todo: Todo){
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.updateTodo(todo)
    }

//    suspend fun deleteTodo(todo: Todo){
//        todoDao.deleteTodo(todo)
//    }

    suspend fun deleteAllTodo(){
        todoDao.deleteAllTodo()
    }

    suspend fun addTodoRight(todo: Todo){
        todo.status="right-todo"
        todoDao.updateTodo(todo)
    }

    suspend fun deleteAllTodoRight(){
        todoDao.deleteAllTodoRight()
    }

    suspend fun addImage(image: ImageAttachment){
        todoDao.addImage(image)
    }

    suspend fun deleteImage(image: ImageAttachment){
        todoDao.deleteImage(image)
    }
}