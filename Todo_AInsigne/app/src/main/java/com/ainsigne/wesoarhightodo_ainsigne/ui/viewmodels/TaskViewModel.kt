package com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels

import androidx.lifecycle.*
import com.ainsigne.wesoarhightodo_ainsigne.ui.interfaces.ITaskRepository
import com.google.android.gms.tasks.Task

/**
 * The ViewModel for [TasksFragment].
 */
class TasksViewModel internal constructor(repo: ITaskRepository) : ViewModel() {

    var tasks = repo.getTasks()




}