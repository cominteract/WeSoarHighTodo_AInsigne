package com.ainsigne.wesoarhightodo_ainsigne.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.AppDatabase
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.TaskDao
import com.ainsigne.wesoarhightodo_ainsigne.ui.data.TasksRepository
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.DetailTaskViewModel
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.DetailTaskViewModelFactory
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.TaskViewModelFactory
import com.ainsigne.wesoarhightodo_ainsigne.ui.viewmodels.TasksViewModel
import com.ainsigne.wesoarhightodo_ainsigne.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class DatabaseModule(appContext: Context) {

    var appDatabase : AppDatabase


    init {
        appDatabase = Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun providesRoom() : AppDatabase {
        return appDatabase
    }

    @Provides
    @Singleton
    fun providesTaskDao() : TaskDao {
        return appDatabase.taskDao()
    }




    @Provides
    @Singleton
    fun providesTaskRepository(dao: TaskDao) : TasksRepository{
        return TasksRepository(dao)

    }



    @Provides
    @Singleton
    fun provideTaskFactory(tasksRepository: TasksRepository) : TaskViewModelFactory{
        return TaskViewModelFactory(tasksRepository)
    }


    @Provides
    @Singleton
    fun providesTasksViewModel(factory: TaskViewModelFactory) : TasksViewModel{
        val viewModel = factory.create(TasksViewModel::class.java)
        return  viewModel
    }

    @Provides
    @Singleton
    fun provideDetailTaskFactory(tasksRepository: TasksRepository, id : Int) : DetailTaskViewModelFactory{
        return DetailTaskViewModelFactory(tasksRepository, id)
    }


    @Provides
    @Singleton
    fun providesTaskDetailsViewModel(factory: DetailTaskViewModelFactory) : DetailTaskViewModel{
        val viewModel = factory.create(DetailTaskViewModel::class.java)
        return  viewModel
    }


}