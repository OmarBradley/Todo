package lee.todo.domain

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lee.todo.base.CompletableRequestUseCase
import lee.todo.data.TaskRepository

class PatchTaskCompletedUseCase(
        val repository: TaskRepository
) : CompletableRequestUseCase<PatchTaskCompletedUseCase.RequestValue> {

    override fun excute(request: RequestValue): Completable =
            repository.patchTaskCompleted(request.uuid, request.isCompleted)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    data class RequestValue(
            val uuid: String,
            val isCompleted: Boolean
    )
}
