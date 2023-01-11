package com.ihsan.roomdb_practice.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ihsan.roomdb_practice.model.ImageAttachment
import com.ihsan.roomdb_practice.model.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todo: Todo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImage(image: ImageAttachment)

    @Query("SELECT * FROM todo_table WHERE status='todo' ORDER BY id ASC")
    fun readAllTodo(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE status='right-todo' ORDER BY id ASC")
    fun readAllTodoRight(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_images ORDER BY id ASC")
    fun readAllImages(): LiveData<List<ImageAttachment>>

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("DELETE FROM todo_table WHERE status='todo'")
    suspend fun deleteAllTodo()

    @Query("DELETE FROM todo_table WHERE status='right-todo'")
    suspend fun deleteAllTodoRight()

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteImage(image: ImageAttachment)
}