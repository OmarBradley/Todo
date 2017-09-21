package lee.todo.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lee.todo.base.SingleUseCase
import lee.todo.data.TaskRepository
import lee.todo.entity.Task

class GetTaskUseCase(
        val repository: TaskRepository
) : SingleUseCase<GetTaskUseCase.RequestValue, GetTaskUseCase.ResponseValue> {

    override fun excute(request: RequestValue): Single<ResponseValue> =
            repository.getTask(request.uuid)
                    .subscribeOn(Schedulers.io())
                    .map { ResponseValue(it) }
                    .observeOn(AndroidSchedulers.mainThread())

    data class RequestValue(
            val uuid: String
    )

    data class ResponseValue(
            val task: Task
    )
}