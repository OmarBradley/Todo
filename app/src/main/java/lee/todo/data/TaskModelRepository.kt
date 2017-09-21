package lee.todo.data

import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
import lee.todo.entity.Task
import lee.todo.entity.TaskModelMapper
import lee.todo.realm.model.TaskModel
import lee.todo.realm.model.getNotDeleteTaskModel
import lee.todo.util.neverError
import lee.todo.util.throwError

class TaskModelRepository(
        val realm: Realm
) : TaskRepository {

    override fun getTask(uuid: String): Single<Task> =
            Single.just(
                    realm.getNotDeleteTaskModel(uuid)
            ).map { taskModel ->
                TaskModelMapper.convertDataToEntity(taskModel)
            }.throwError()

    override fun getTasksBeforeCreateTimeStamp(createTimeStamp: Long): Single<List<Task>> =
            Single.just(
                    realm.where(TaskModel::class.java)
                            .equalTo(TaskModel.IS_DELETED, false)
                            .lessThan(TaskModel.CREATE_TIME_STAMP, createTimeStamp)
                            .findAll()
            ).map { taskModels ->
                taskModels.map { taskModel ->
                    TaskModelMapper.convertDataToEntity(taskModel)
                }
            }.throwError()

    override fun getAllTasks(): Single<List<Task>> =
            Single.just(
                    realm.where(TaskModel::class.java)
                            .equalTo(TaskModel.IS_DELETED, false)
                            .findAll()
            ).map { taskModels ->
                taskModels.map { taskModel ->
                    TaskModelMapper.convertDataToEntity(taskModel)
                }
            }.throwError()

    override fun postTask(task: Task): Single<Task> =
            Single.just(
                    realm.copyToRealm(TaskModelMapper.convertEntityToData(task))
            ).map { taskModel ->
                TaskModelMapper.convertDataToEntity(taskModel)
            }.throwError()

    override fun patchTaskName(uuid: String, name: String): Completable =
            Completable.fromAction {
                realm.getNotDeleteTaskModel(uuid)
                        ?.let { taskModel ->
                            taskModel.name = name
                        } ?: throw NullPointerException()
            }.neverError()

    override fun patchTaskCompleted(uuid: String, isCompleted: Boolean): Completable =
            Completable.fromAction {
                realm.getNotDeleteTaskModel(uuid)
                        ?.let { taskModel ->
                            taskModel.isCompleted = isCompleted
                        } ?: throw NullPointerException()
            }.neverError()

    override fun patchTaskDeleted(uuid: String, isDeleted: Boolean): Completable =
            Completable.fromAction {
                realm.getNotDeleteTaskModel(uuid)
                        ?.let { taskModel ->
                            taskModel.isDeleted = isDeleted
                        } ?: throw NullPointerException()
            }.neverError()
}