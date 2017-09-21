package lee.todo.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lee.todo.base.SingleUseCase
import lee.todo.data.TaskRepository
import lee.todo.entity.Task

class PostTaskUseCase(
        val repository: TaskRepository
) : SingleUseCase<PostTaskUseCase.RequestValue, PostTaskUseCase.ResponseValue> {

    override fun excute(request: RequestValue): Single<ResponseValue> =
            repository.postTask(request.task)
                    .subscribeOn(Schedulers.io())
                    .map { ResponseValue(it) }
                    .observeOn(AndroidSchedulers.mainThread())

    data class RequestValue(
            val task: Task
    ) {
        constructor(name: String) : this(
                task = Task(name = name, isDeleted = false, isCompleted = false)
        )
    }

    data class ResponseValue(
            val task: Task
    )

}
