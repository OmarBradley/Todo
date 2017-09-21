package lee.todo.realm.model

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import lee.todo.base.Data
import java.util.*

open class TaskModel(

        @PrimaryKey
        var uuid: String = UUID.randomUUID().toString(),

        var name: String = "",

        var isCompleted: Boolean = false,

        var isDeleted: Boolean = false,

        var createTimeStamp: Long = System.currentTimeMillis()

) : RealmObject(), Data {

    companion object {

        const val SYNC_UUID = "syncuuid"
        const val NAME = "name"
        const val IS_COMPLETED = "isCompleted"
        const val IS_DELETED = "isDeleted"
        const val CREATE_TIME_STAMP = "createTimeStamp"
    }
}

// Query

fun Realm.getNotDeleteTaskModel(uuid: String): TaskModel? =
        where(TaskModel::class.java)
                .equalTo(TaskModel.SYNC_UUID, uuid)
                .equalTo(TaskModel.IS_DELETED, false)
                .findAll()
                .firstOrNull()
