package com.ihsan.roomdb_practice.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var title:String,
    var desc:String?,
    var dueTime: String?,
    var completedDate: String?,
    var imagesId:String?,
    var status:String?
):Parcelable