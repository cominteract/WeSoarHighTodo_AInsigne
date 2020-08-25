package com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels

import androidx.lifecycle.*
import com.ainsigne.wesoarhightodo_ainsigne.ui.interfaces.ITaskRepository
import com.google.android.gms.tasks.Task

/**
 * The ViewModel for [TasksFragment].
 */
class DetailTaskViewModel internal constructor(repo: ITaskRepository, id : Int) : ViewModel() {

    var task = repo.getTask(id)

    


}