package com.ihsan.roomdb_practice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ihsan.roomdb_practice.data.Converters
import com.ihsan.roomdb_practice.model.ImageAttachment
import com.ihsan.roomdb_practice.model.Todo
import com.ihsan.roomdb_practice.dao.TodoDao

@Database(entities = [Todo::class, ImageAttachment::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TodoDatabase:RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase?=null

        fun getDatabase(context: Context): TodoDatabase {
            val tempInstance= INSTANCE

            if(tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE =instance

                return instance
            }
        }
    }
}