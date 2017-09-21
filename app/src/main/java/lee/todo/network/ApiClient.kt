package lee.todo.network

import io.reactivex.Completable
import io.reactivex.Single
import lee.todo.network.model.TaskJson

interface ApiClient {

    fun getTask(uuid: String): Single<TaskJson>

    fun getTasksBeforeCreateTimeStamp(createTimeStamp: Long): Single<List<TaskJson>>

    fun getAllTasks(): Single<List<TaskJson>>

    fun postTask(task: TaskJson): Single<TaskJson>

    fun deleteTask(uuid: String): Completable

    fun patchTaskName(uuid: String, name: String): Completable

    fun patchTaskCompleted(uuid: String, isCompleted: Boolean): Completable

    fun patchTaskDeleted(uuid: String, isDeleted: Boolean): Completable

}