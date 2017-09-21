package lee.todo.domain

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lee.todo.base.CompletableRequestUseCase
import lee.todo.data.TaskRepository

class PatchTaskDeleteUseCase(
        val repository: TaskRepository
) : CompletableRequestUseCase<PatchTaskDeleteUseCase.RequestValue> {

    override fun excute(request: RequestValue): Completable =
            repository.patchTaskDeleted(request.uuid, request.isDeleted)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    data class RequestValue(
            val uuid: String,
            val isDeleted: Boolean
    )
}
