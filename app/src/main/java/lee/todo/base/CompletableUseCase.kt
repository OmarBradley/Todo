package lee.todo.base

import io.reactivex.Completable

interface CompletableRequestUseCase<in REQUEST> {

    fun excute(request: REQUEST): Completable
}

interface CompletableUseCase {

    fun excute(): Completable
}