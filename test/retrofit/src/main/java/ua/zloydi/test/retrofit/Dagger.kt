package ua.zloydi.test.retrofit

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.zloydi.retrofit.ApiKeyInterceptor
import java.util.concurrent.TimeUnit

object RetrofitTest {
	val client =
		OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor()).callTimeout(5, TimeUnit.SECONDS)
			.readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).build()
	
	fun getRetrofit(url: HttpUrl) =
		Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url).client(client)
			.build()
}