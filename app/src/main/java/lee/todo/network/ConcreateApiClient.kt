package lee.todo.network

import io.reactivex.Completable
import io.reactivex.Single
import lee.todo.network.ctrl.TaskCtrl
import lee.todo.network.model.TaskJson

class ConcreateApiClient(
        private val apiService: ApiService
) : ApiClient {

    override fun getTask(uuid: String): Single<TaskJson> =
            apiService.getTask(uuid)
                    .map { it.task ?: throw NullPointerException() }

    override fun getTasksBeforeCreateTimeStamp(createTimeStamp: Long): Single<List<TaskJson>> =
            apiService.getAllTasks()
                    .map { it.tasks }

    override fun getAllTasks(): Single<List<TaskJson>> =
            apiService.getAllTasks()
                    .map { it.tasks }

    override fun postTask(task: TaskJson): Single<TaskJson> =
            apiService.postTask(TaskCtrl.PostTask.Body(task))
                    .map { it.task ?: throw NullPointerException() }

    override fun deleteTask(uuid: String): Completable =
            apiService.deleteTask(uuid)

    override fun patchTaskName(uuid: String, name: String): Completable =
            apiService.patchTaskName(TaskCtrl.PatchTask.Body(TaskJson(uuid = uuid, name = name)))

    override fun patchTaskCompleted(uuid: String, isCompleted: Boolean): Completable =
            apiService.patchTaskCompleted(TaskCtrl.PatchTask.Body(TaskJson(uuid = uuid, isCompleted = isCompleted)))

    override fun patchTaskDeleted(uuid: String, isDeleted: Boolean): Completable =
            apiService.patchTaskDeleted(TaskCtrl.PatchTask.Body(TaskJson(uuid = uuid, isDeleted = isDeleted)))
}