package lee.todo.etc

import io.reactivex.Observable

// 이건 RxJava 연습하려고 쓴 것임, 이번 프로젝트와는 상관이 없음
class RxJavaPractice {

    // 명령형
    fun gugudan(a: Int): MutableList<Int> {

        val number = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val answer = mutableListOf<Int>()
        for (n in number) {
            answer.add(a * n)
        }
        return answer
    }

    // RxJava
    fun gugudanByRx(a: Int) =
            Observable.range(1, 9)
                    .flatMap { number -> Observable.just(number * a) }
}