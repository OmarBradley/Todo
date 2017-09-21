package lee.todo.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

open class APIRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
            chain.proceed(getRequest(chain.request()))

    private fun getRequest(request: Request): Request =
            request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .url(request.url())
                    .build()
}