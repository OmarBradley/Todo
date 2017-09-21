package lee.todo.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lee.todo.base.SingleUseCase
import lee.todo.data.TaskRepository
import lee.todo.entity.Task

class GetTaskBeforeCreateTimeStampUseCase(
        val repository: TaskRepository
) : SingleUseCase<GetTaskBeforeCreateTimeStampUseCase.RequestValue, GetTaskBeforeCreateTimeStampUseCase.ResponseValue> {

    override fun excute(request: RequestValue): Single<ResponseValue> =
            repository.getTasksBeforeCreateTimeStamp(request.createTimeStamp)
                    .subscribeOn(Schedulers.io())
                    .map { ResponseValue(it) }
                    .observeOn(AndroidSchedulers.mainThread())

    data class RequestValue(
            val createTimeStamp: Long
    )

    data class ResponseValue(
            val tasks: List<Task>
    )
}