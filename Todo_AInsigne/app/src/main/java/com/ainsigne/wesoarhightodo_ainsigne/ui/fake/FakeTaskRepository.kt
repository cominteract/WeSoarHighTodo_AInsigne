package com.ainsigne.wesoarhightodo_ainsigne.ui.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.Task
import com.ainsigne.wesoarhightodo_ainsigne.ui.interfaces.ITaskRepository
import com.ainsigne.wesoarhightodo_ainsigne.utils.fakeTasks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

/**
 * Fake repository module for handling data operations for the [Task].
 */
class FakeTasksRepository() : ITaskRepository {

    var alltasks = ArrayList<Task>()

    val tasksLiveData = MutableLiveData<List<Task>>()

    init {
        generateItems()
    }

    fun generateItems() {
        if (alltasks.isEmpty()) {
            alltasks = fakeTasks as ArrayList<Task>
        }
    }

    override fun getTasks(): LiveData<List<Task>> {
        tasksLiveData.value = alltasks
        return tasksLiveData
    }

    override fun getTask(id: Int): LiveData<Task> {
        val liveData = MutableLiveData<Task>()
        liveData.value = alltasks.filter { it.id == id }.first()
        return liveData
    }

    override fun updateTask(task: Task){
        val position = alltasks.map { it.id }.indexOf(task.id)
        if(position >= 0)
            alltasks.add(position, task)
        tasksLiveData.value = alltasks
    }

    override fun deleteTask(task: Task) {
        val position = alltasks.map { it.id }.indexOf(task.id)
        if(position >= 0)
            alltasks.removeAt(position)
        tasksLiveData.value = alltasks
    }

    override fun getTasksWithtitle(secondaryId: String): LiveData<List<Task>> {
        val liveData = MutableLiveData<List<Task>>()
        liveData.value = alltasks.filter { it.title == secondaryId }
        return liveData
    }

    override fun insertTask(task: Task) {
        alltasks.add(task)
        tasksLiveData.value = alltasks
    }

    override fun insertAll(tasks: List<Task>) {

        alltasks.addAll(tasks)

        tasksLiveData.value = alltasks


    }


}