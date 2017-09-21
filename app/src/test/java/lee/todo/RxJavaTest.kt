package lee.todo

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class RxJavaTest : StringSpec() {
    init {

        "length should return size of string" {
            "hello".length shouldBe 5
        }

    }
}