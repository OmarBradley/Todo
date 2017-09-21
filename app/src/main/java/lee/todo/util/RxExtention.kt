package lee.todo.util

import io.reactivex.Completable
import io.reactivex.Single

fun Completable.neverError(): Completable =
        onErrorResumeNext { e ->
            e.printStackTrace()
            Completable.never()
        }

fun <T> Single<T>.throwError(): Single<T> =
        onErrorResumeNext { e ->
            e.printStackTrace()
            Single.error<T> { throw e }
        }
