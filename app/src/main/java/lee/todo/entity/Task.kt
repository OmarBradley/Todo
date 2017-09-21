package lee.todo.entity

import lee.todo.base.Entity
import lee.todo.base.EntityMapper
import lee.todo.network.model.TaskJson
import lee.todo.realm.model.TaskModel
import java.util.*

data class Task(

        var uuid: String = UUID.randomUUID().toString(),

        var name: String = "",

        var isCompleted: Boolean = false,

        var isDeleted: Boolean = false,

        var createTimeStamp: Long = System.currentTimeMillis()

) : Entity

object TaskJsonMapper : EntityMapper<Task, TaskJson> {

    override fun convertDataToEntity(data: TaskJson): Task =
            Task(
                    uuid = data.uuid ?: UUID.randomUUID().toString(),
                    name = data.name ?: "",
                    isCompleted = data.isCompleted ?: false,
                    isDeleted = data.isDeleted ?: false,
                    createTimeStamp = data.createTimeStamp ?: System.currentTimeMillis()
            )

    override fun convertEntityToData(entity: Task): TaskJson =
            TaskJson(
                    uuid = entity.uuid,
                    name = entity.name,
                    isCompleted = entity.isCompleted,
                    isDeleted = entity.isDeleted,
                    createTimeStamp = entity.createTimeStamp
            )
}

object TaskModelMapper : EntityMapper<Task, TaskModel> {

    override fun convertDataToEntity(data: TaskModel): Task =
            Task(
                    uuid = data.uuid,
                    name = data.name,
                    isCompleted = data.isCompleted,
                    isDeleted = data.isDeleted,
                    createTimeStamp = data.createTimeStamp
            )

    override fun convertEntityToData(entity: Task): TaskModel =
            TaskModel(
                    uuid = entity.uuid,
                    name = entity.name,
                    isCompleted = entity.isCompleted,
                    isDeleted = entity.isDeleted,
                    createTimeStamp = entity.createTimeStamp
            )
}
