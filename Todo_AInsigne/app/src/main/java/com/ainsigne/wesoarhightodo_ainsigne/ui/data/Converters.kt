package com.ainsigne.wesoarhightodo_ainsigne.ui.data


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }


    @TypeConverter
    fun tasksToString(tasks: ArrayList<Task>) = Gson().toJson(tasks)

    @TypeConverter
    fun stringToTasks(value: String): ArrayList<Task> {
        val modelType = object : TypeToken<ArrayList<Task>>() {}.type
        return Gson().fromJson(value, modelType)
    }




    @TypeConverter
    fun arrayListHashmapToString(value: ArrayList<HashMap<String, Any>>) = Gson().toJson(value)

    @TypeConverter
    fun stringToArrayListHashmap(value: String): ArrayList<HashMap<String, Any>> {
        val taskType = object : TypeToken<ArrayList<HashMap<String, Any>>>() {}.type
        return Gson().fromJson(value, taskType)
    }

    @TypeConverter
    fun hashmapToString(value: HashMap<String, Any>) = Gson().toJson(value)

    @TypeConverter
    fun stringTohashmap(value: String): HashMap<String, Any> {
        val taskType = object : TypeToken<HashMap<String, Any>>() {}.type
        return Gson().fromJson(value, taskType)
    }


    @TypeConverter
    fun anyToString(any: Any) = Gson().toJson(any)

    @TypeConverter
    fun stringToAny(value: String): Any {
        val taskType = object : TypeToken<Any>() {}.type
        return Gson().fromJson(value, taskType)
    }

}