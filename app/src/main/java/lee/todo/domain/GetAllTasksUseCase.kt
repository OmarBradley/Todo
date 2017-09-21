package lee.todo.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lee.todo.base.SingleResponseUseCase
import lee.todo.data.TaskRepository
import lee.todo.entity.Task

class GetAllTasksUseCase(
        val repository: TaskRepository
) : SingleResponseUseCase<GetAllTasksUseCase.ResponseValue> {

    override fun excute(): Single<GetAllTasksUseCase.ResponseValue> =
            repository.getAllTasks()
                    .subscribeOn(Schedulers.io())
                    .map { ResponseValue(it) }
                    .observeOn(AndroidSchedulers.mainThread())

    data class ResponseValue(
            val tasks: List<Task>
    )
}