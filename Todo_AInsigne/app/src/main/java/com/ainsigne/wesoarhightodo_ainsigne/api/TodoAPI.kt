package com.ainsigne.wesoarhightodo_ainsigne.api

import com.ainsigne.wesoarhightodo_ainsigne.utils.BASE_API
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
/**
 * [TodoAPI] declaration of its retrofit service builder for making http calls
 */
class TodoAPI {
    val webservice: TodoService by lazy {



        val client = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).build()


        Retrofit.Builder()
            .baseUrl(BASE_API).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(TodoService::class.java)
    }

}