package lee.todo.data

import io.reactivex.Completable
import io.reactivex.Single
import lee.todo.entity.Task
import lee.todo.entity.TaskJsonMapper
import lee.todo.network.ApiClient
import lee.todo.util.neverError
import lee.todo.util.throwError

class TaskJsonRepository(
        private val apiClient: ApiClient
) : TaskRepository {

    override fun getTask(uuid: String): Single<Task> =
            apiClient.getTask(uuid)
                    .map { TaskJsonMapper.convertDataToEntity(it) }
                    .throwError()

    override fun getTasksBeforeCreateTimeStamp(createTimeStamp: Long): Single<List<Task>> =
            apiClient.getTasksBeforeCreateTimeStamp(createTimeStamp)
                    .map { taskJsons ->
                        taskJsons.map { taskJson ->
                            TaskJsonMapper.convertDataToEntity(taskJson)
                        }
                    }.throwError()

    override fun getAllTasks(): Single<List<Task>> =
            apiClient.getAllTasks()
                    .map { taskJsons ->
                        taskJsons.map { taskJson ->
                            TaskJsonMapper.convertDataToEntity(taskJson)
                        }
                    }.throwError()

    override fun postTask(task: Task): Single<Task> =
            apiClient.postTask(TaskJsonMapper.convertEntityToData(task))
                    .map { TaskJsonMapper.convertDataToEntity(it) }
                    .throwError()

    override fun patchTaskName(uuid: String, name: String): Completable =
            apiClient.patchTaskName(uuid, name)
                    .neverError()

    override fun patchTaskCompleted(uuid: String, isCompleted: Boolean): Completable =
            apiClient.patchTaskCompleted(uuid, isCompleted)
                    .neverError()

    override fun patchTaskDeleted(uuid: String, isDeleted: Boolean): Completable =
            apiClient.patchTaskDeleted(uuid, isDeleted)
                    .neverError()
}
