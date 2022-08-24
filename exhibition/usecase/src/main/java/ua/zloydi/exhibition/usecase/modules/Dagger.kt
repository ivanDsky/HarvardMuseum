package ua.zloydi.exhibition.usecase.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.zloydi.exhibition.usecase.data.ExhibitionService
import javax.inject.Scope

interface ExhibitionDependencies {
	val retrofit: Retrofit
}

@Module
internal class ExhibitionModule {
	@Provides
	@ExhibitionScope
	fun provideService(deps: ExhibitionDependencies): ExhibitionService =
		deps.retrofit.create(ExhibitionService::class.java)
}

@Scope
annotation class ExhibitionScope