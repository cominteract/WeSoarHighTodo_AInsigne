package com.ainsigne.wesoarhightodo_ainsigne.ui.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * The Data Access Object for the Task class.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY id")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE title = :secondaryId_")
    fun getTasksFromFilter(secondaryId_: String): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id = :primaryId_")
    fun getTaskFromId(primaryId_: Int): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Task>)

    @Query("UPDATE task SET completed = :completed_, date = :date_, title = :title_  WHERE id = :primaryId_")
    suspend fun updateTask(primaryId_: Int, completed_ : Boolean, title_ : String, date_ : String)

    @Delete
    suspend fun deleteTask(task : Task)

    @Insert
    suspend fun insertTask(task : Task)

}
