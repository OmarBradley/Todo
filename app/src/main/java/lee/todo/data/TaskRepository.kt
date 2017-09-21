package lee.todo.data

import io.reactivex.Completable
import io.reactivex.Single
import lee.todo.entity.Task

interface TaskRepository {

    fun getTask(uuid: String): Single<Task>

    fun getTasksBeforeCreateTimeStamp(createTimeStamp: Long): Single<List<Task>>

    fun getAllTasks(): Single<List<Task>>

    fun postTask(task: Task): Single<Task>

    fun patchTaskName(uuid: String, name: String): Completable

    fun patchTaskCompleted(uuid: String, isCompleted: Boolean): Completable

    fun patchTaskDeleted(uuid: String, isDeleted: Boolean): Completable
}