package com.ainsigne.wesoarhightodo_ainsigne.di


import com.ainsigne.wesoarhightodo_ainsigne.MainActivity
import com.ainsigne.wesoarhightodo_ainsigne.api.TodoAPI
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val act: MainActivity) {
    @Provides
    fun providesActivity() : MainActivity = act

    @Provides
    fun providesFoursquare() : TodoAPI = TodoAPI()

}