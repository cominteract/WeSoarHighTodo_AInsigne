package com.ainsigne.wesoarhightodo_ainsigne.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@Entity(tableName = "task")
data class Task(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    var title: String = "Default Title",
    var completed: Boolean = false,
    var date : String = Date().toString()

)