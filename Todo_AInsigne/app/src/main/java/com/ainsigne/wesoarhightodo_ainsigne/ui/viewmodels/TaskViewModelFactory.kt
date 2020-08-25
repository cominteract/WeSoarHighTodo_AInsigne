package com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ainsigne.wesoarhightodo_ainsigne.ui.interfaces.ITaskRepository

/**
 * Factory for creating a [TasksViewModel] with a constructor that takes a
 * [FakeTaskRepository].
 */
class TaskViewModelFactory(
    private val repo: ITaskRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(repo) as T
    }
}