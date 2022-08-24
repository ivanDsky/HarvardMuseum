package ua.zloydi.search

import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {
	@Provides
	@Singleton
	fun getClient(): OkHttpClient =
		OkHttpClient.Builder()
			.addInterceptor(ApiKeyInterceptor())
			.callTimeout(5, TimeUnit.SECONDS)
			.readTimeout(5, TimeUnit.SECONDS)
			.writeTimeout(5, TimeUnit.SECONDS)
			.build()
	
	@Provides
	@Singleton
	fun getRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
		.baseUrl(BuildConfig.BASE_URL)
		.client(client)
		.build()
}