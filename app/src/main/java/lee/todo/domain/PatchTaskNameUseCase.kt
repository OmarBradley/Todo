package lee.todo.domain

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lee.todo.base.CompletableRequestUseCase
import lee.todo.data.TaskRepository


class PatchTaskNameUseCase(
        val repository: TaskRepository
) : CompletableRequestUseCase<PatchTaskNameUseCase.RequestValue> {

    override fun excute(request: RequestValue): Completable =
            repository.patchTaskName(request.uuid, request.name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    data class RequestValue(
            val uuid: String,
            val name: String
    )
}