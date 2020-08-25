package com.ainsigne.wesoarhightodo_ainsigne.api


import com.ainsigne.wesoarhightodo_ainsigne.ui.data.Task
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface TodoService {
    /**
     * gets the response to retrieve the tasks list from the mock api
     */
    @GET("tasks")
    suspend fun getTasks() : Response<List<Task>>

    /**
     * gets the response to retrieve the tasks list by id from the mock api
     */
    @GET("tasks")
    suspend fun getTaskById(@Query("id") id : Int) : Response<List<Task>>

    /**
     * gets the response to retrieve the tasks list by title from the mock api
     */
    @GET("tasks")
    suspend fun getTaskByTitle(@Query("title") id : String) : Response<List<Task>>

    /**
     * gets the response to retrieve the tasks list by date from the mock api
     */
    @GET("tasks")
    suspend fun getTaskByDate(@Query("date") date : String) : Response<List<Task>>

    /**
     * gets the response to retrieve the tasks list by completed from the mock api
     */
    @GET("tasks")
    suspend fun getTaskByCompleted(@Query("completed") completed : Boolean) : Response<List<Task>>


    /**
     * submits the task to be added into the task list
     */
    @POST("tasks")
    suspend fun submitTask(@Body task: Task) : Response<JSONObject>

    /**
     * updates the task selected to reflect on the task list
     */
    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id") id : String,@Body task: Task) : Response<JSONObject>

    /**
     * deletes the task selected to reflect on the task list
     */
    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id : String) : Response<JSONObject>
}