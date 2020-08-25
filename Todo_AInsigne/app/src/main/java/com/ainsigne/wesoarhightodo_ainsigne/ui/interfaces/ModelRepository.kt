package com.ainsigne.wesoarhightodo_ainsigne.ui.interfaces

import androidx.lifecycle.LiveData
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.*

interface ITaskRepository {
    fun getTasks(): LiveData<List<Task>>

    fun getTask(id: Int)
            : LiveData<Task>

    fun getTasksWithtitle(secondaryId: String): LiveData<List<Task>>

    fun insertTask(task: Task)

    fun updateTask(task: Task)

    fun deleteTask(task: Task)

    fun insertAll(tasks: List<Task>)
}
