package ua.zloydi.search

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val original = chain.request()
		val urlWithApiKey =
			original.url().newBuilder().addQueryParameter("apikey", BuildConfig.API_KEY).build()
		val requestWithApiKey = original.newBuilder().url(urlWithApiKey).build()
		return chain.proceed(requestWithApiKey)
	}
}