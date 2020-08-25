package com.ainsigne.wesoarhightodo_ainsigne.di



import com.ainsigne.wesoarhightodo_ainsigne.MainActivity
import com.ainsigne.wesoarhightodo_ainsigne.ui.fragments.TaskEntryFragment
import com.ainsigne.wesoarhightodo_ainsigne.ui.fragments.TaskListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class,DatabaseModule::class])
interface ActivityComponent{
    fun mainActivity(): MainActivity
    fun inject(taskEntryFragment: TaskEntryFragment)
    fun inject(taskListFragment: TaskListFragment)


}