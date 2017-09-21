package lee.todo.network.ctrl

import lee.todo.network.model.TaskJson

class TaskCtrl {

    class GetTasks {

        data class Response(
                var tasks: List<TaskJson> = emptyList()
        )
    }

    class GetTask {

        data class Response(
                var task: TaskJson? = null
        )
    }

    class PostTask {

        data class Body(
                var task: TaskJson
        )

        data class Response(
                var task: TaskJson? = null
        )
    }

    class PatchTask {

        data class Body(
                var task: TaskJson
        )
    }
}