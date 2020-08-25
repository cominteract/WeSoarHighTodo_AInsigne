package com.ainsigne.wesoarhightodo_ainsigne.ui.data

import androidx.lifecycle.LiveData
import com.ainsigne.wesoarhightodo_ainsigne.ui.interfaces.ITaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Repository module for handling data operations for the [Task].
 */
class TasksRepository(var taskDao: TaskDao) : ITaskRepository {


    override fun getTasks() = taskDao.getTasks()

    override fun getTask(id: Int) = taskDao.getTaskFromId(id)

    override fun updateTask(task: Task){
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.updateTask(task.id, task.completed, task.title, task.date)
        }
    }

    override fun getTasksWithtitle(secondaryId: String): LiveData<List<Task>> =
        taskDao.getTasksFromFilter(secondaryId)

    override fun insertTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.insertTask(task)
        }
    }

    override fun deleteTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.deleteTask(task)
        }
    }

    override fun insertAll(tasks: List<Task>) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.insertAll(tasks)
        }
    }


}