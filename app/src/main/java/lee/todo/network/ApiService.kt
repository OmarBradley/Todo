package lee.todo.network

import io.reactivex.Completable
import io.reactivex.Single
import lee.todo.network.ctrl.TaskCtrl
import retrofit2.http.*

interface ApiService {

    @GET("/task")
    fun getTask(
            @Query("sync_uuid") uuid: String
    ): Single<TaskCtrl.GetTask.Response>

    fun getTasksBeforeCreateTimeStamp(
            @Query("create_time_stamp") createTimeStamp: Long
    ): Single<TaskCtrl.GetTasks.Response>

    @GET("/tasks")
    fun getAllTasks(): Single<TaskCtrl.GetTasks.Response>

    @POST("/task")
    fun postTask(
            @Body data: TaskCtrl.PostTask.Body
    ): Single<TaskCtrl.PostTask.Response>

    @DELETE("/task")
    fun deleteTask(
            @Path("/sync_uuid") uuid: String
    ): Completable

    @PATCH("/task")
    fun patchTaskName(
            @Body data: TaskCtrl.PatchTask.Body
    ): Completable

    @PATCH("/task")
    fun patchTaskCompleted(
            @Body data: TaskCtrl.PatchTask.Body
    ): Completable

    @PATCH("/task")
    fun patchTaskDeleted(
            @Body data: TaskCtrl.PatchTask.Body
    ): Completable
}
