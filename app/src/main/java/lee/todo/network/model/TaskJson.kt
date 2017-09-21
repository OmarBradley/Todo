package lee.todo.network.model

import com.google.gson.annotations.SerializedName
import lee.todo.base.Data

data class TaskJson(

        @SerializedName("uuid")
        var uuid: String? = null,

        @SerializedName("name")
        var name: String? = null,

        @SerializedName("is_completed")
        var isCompleted: Boolean? = null,

        @SerializedName("is_deleted")
        var isDeleted: Boolean? = null,

        @SerializedName("create_time_stamp")
        var createTimeStamp: Long? = null

) : Data