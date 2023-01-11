package com.ihsan.roomdb_practice.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_images")
data class ImageAttachment(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val image:Bitmap,
    val todoId:Int
    )
