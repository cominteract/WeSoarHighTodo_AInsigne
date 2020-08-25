package com.ainsigne.wesoarhightodo_ainsigne

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.Task
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.TasksRepository
import com.ainsigne.wesoarhightodo_ainsigne.ui.fake.FakeTasksRepository
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.DetailTaskViewModel
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.TasksViewModel
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.verify
import java.util.*

class TaskTest {

    inline fun <reified T> lambdaMock(): T = Mockito.mock(T::class.java)
    lateinit var repository: FakeTasksRepository
    lateinit var viewmodel: TasksViewModel
    lateinit var detailsviewModel: DetailTaskViewModel
    var taskid : Int = 0

    fun observeTaskChanges(lifecycle: Lifecycle, observer: (List<Task>) -> Unit) {
        viewmodel.tasks.observe( { lifecycle } ) { tasks ->
            tasks?.let(observer)
        }
    }

    fun observeTaskInsertChanges(lifecycle: Lifecycle, observer: (List<Task>) -> Unit) {
        viewmodel.tasks.observe( { lifecycle } ) { tasks ->
            tasks?.let(observer)
        }
    }

    fun observeTaskDetailChanges(lifecycle: Lifecycle, observer: (Task) -> Unit) {
        detailsviewModel.task.observe( { lifecycle }){task ->
            task?.let(observer)
        }
    }


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        taskid = 4
        repository = FakeTasksRepository()
        viewmodel = TasksViewModel(repository)
        detailsviewModel = DetailTaskViewModel(repository, taskid)

    }

    @Test
    fun testTaskDetails(){
        val detailObserver = lambdaMock<(Task) -> Unit>()
        val lifecycle = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        observeTaskDetailChanges(lifecycle, detailObserver)
        repository.getTask(taskid).value?.let{
            assertTrue(it.id == taskid)
            verify(detailObserver).invoke(it)
        }
    }

    @Test
    fun testInsertTasks() {
        val observer = lambdaMock<(List<Task>) -> Unit>()
        val lifecycle = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        observeTaskInsertChanges(lifecycle, observer)
        val task = Task(205, "Title", false,Date().toString())
        repository.insertTask(task)
        viewmodel.tasks.value?.let {
            assert(it.size == 31)

        }
    }

    @Test
    fun testUpdateTasks() {
        val observer = lambdaMock<(List<Task>) -> Unit>()
        val lifecycle = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        observeTaskChanges(lifecycle, observer)

        val task = repository.alltasks[1]
        task.completed = true
        task.title = "Updated Title"
        repository.updateTask(task)
        viewmodel.tasks.value?.let {
            assert(it[1].completed)
            assert(it[1].title.equals("Updated Title"))

        }
    }

    @Test
    fun testDeleteTasks() {
        val observer = lambdaMock<(List<Task>) -> Unit>()
        val lifecycle = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        observeTaskChanges(lifecycle, observer)

        val task = repository.alltasks.get(1)
        repository.deleteTask(task)
        viewmodel.tasks.value?.let {
            assert(it.size == 29)

        }
    }


}