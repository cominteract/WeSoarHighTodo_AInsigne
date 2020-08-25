package com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ainsigne.wesoarhightodo_ainsigne.ui.interfaces.ITaskRepository

/**
 * Factory for creating a [TasksViewModel] with a constructor that takes a
 * [FakeTaskRepository].
 */
class DetailTaskViewModelFactory(
    private val repo: ITaskRepository,private val id: Int
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailTaskViewModel(repo, id) as T
    }
}