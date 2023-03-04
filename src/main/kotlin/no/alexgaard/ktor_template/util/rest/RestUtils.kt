package no.alexgaard.ktor_template.util.rest

import kotlinx.serialization.decodeFromString
import no.alexgaard.ktor_template.util.rest.ApiResult.Companion.failure
import no.alexgaard.ktor_template.util.rest.ApiResult.Companion.success
import no.alexgaard.ktor_template.util.rest.JsonUtils.jsonMapper
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

fun baseClient(): OkHttpClient {
	return OkHttpClient().newBuilder().build()
}

fun bearerAuthorzation(bearerTokenProvider: () -> String): Pair<String, String> {
	return bearerAuthorzation(bearerTokenProvider.invoke())
}

fun bearerAuthorzation(bearerToken: String): Pair<String, String> {
	return "Authorization" to "Bearer $bearerToken"
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
	bodyParser: (body: String) -> T = { jsonMapper.decodeFromString(it) }
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