package lee.todo.base

import io.reactivex.Single

interface SingleUseCase<in REQUEST, RESPONSE> {

    fun excute(request: REQUEST): Single<RESPONSE>
}

interface SingleResponseUseCase<RESPONSE> {

    fun excute(): Single<RESPONSE>
}