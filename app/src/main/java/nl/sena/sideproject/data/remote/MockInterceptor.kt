package nl.sena.sideproject.data.remote

import nl.sena.sideproject.data.remote.mockdata.numbersJson
import okhttp3.*

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        return when {
            uri.endsWith("numbers") -> createSuccessResponse(chain.proceed(chain.request()))
            else -> createFailedResponse(chain.proceed(chain.request()))
        }
    }

    private fun createSuccessResponse(response: Response): Response {
        val successResponse = response
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(numbersJson)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    numbersJson.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
        return successResponse.build()
    }

    private fun createFailedResponse(response: Response): Response {
        val failedResponse = response
            .newBuilder()
            .code(404)
            .protocol(Protocol.HTTP_2)
        return failedResponse.build()
    }
}