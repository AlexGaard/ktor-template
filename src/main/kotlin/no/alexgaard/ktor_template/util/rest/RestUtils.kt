package no.alexgaard.ktor_template.util.rest

import okhttp3.OkHttpClient

fun baseClient(): OkHttpClient {
	return OkHttpClient().newBuilder().build()
}