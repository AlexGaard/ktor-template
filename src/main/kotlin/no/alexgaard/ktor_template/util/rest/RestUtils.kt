package no.alexgaard.ktor_template.util.rest

import no.alexgaard.ktor_template.util.rest.ApiResult.Companion.failure
import no.alexgaard.ktor_template.util.rest.ApiResult.Companion.success
import no.alexgaard.ktor_template.util.rest.JsonUtils.jsonMapper
import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

fun baseClient(): OkHttpClient {
	return OkHttpClient().newBuilder().build()
}

fun request(
	method: String,
	url: String,
	headers: Map<String, String> = emptyMap(),
	body: String? = null,
	contentType: MediaType = "application/json".toMediaType()
): Request {
	return Request.Builder()
		.url(url)
		.headers(headers.toHeaders())
		.method(method, body?.toRequestBody(contentType))
		.build()
}

inline fun <reified T> OkHttpClient.sendRequest(
	request: Request,
	bodyParser: (body: String) -> T = { jsonMapper.readValue(it, T::class.java) }
): ApiResult<T> {
	try {
		this.newCall(request).execute().use { res ->
			val body = res.body?.string()

			if (!res.isSuccessful) {
				return failure(BadHttpStatusApiException(res.code, body))
			}

			return body?.let { success(bodyParser.invoke(body)) }
				?: failure(ResponseDataApiException.missingBody())
		}
	} catch (e: Throwable) {
		return when (e) {
			is IOException -> failure(NetworkApiException(e))
			else -> failure(UnspecifiedApiException(e))
		}
	}
}

fun OkHttpClient.sendRaw(request: Request): RawResponse {
	return this.newCall(request).execute().use {
		RawResponse(
			statusCode = it.code,
			body = it.body?.string(),
			headers = it.headers
		)
	}
}

data class RawResponse(
	val statusCode: Int,
	val body: String?,
	val headers: Headers,
)