package ua.zloydi.exhibibtion.modules

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.zloydi.exhibibtion.data.ExhibitionRemoteDataSource
import ua.zloydi.exhibibtion.data.ExhibitionService
import javax.inject.Scope
import javax.inject.Singleton

interface ExhibitionDependencies {
	val retrofit: Retrofit
}

@Module
internal class ExhibitionModule {
	@Provides
	@ExhibitionScope
	fun provideDataSource(deps: ExhibitionDependencies): ExhibitionService =
		deps.retrofit.create(ExhibitionService::class.java)
}

@Scope
internal annotation class ExhibitionScope

@ExhibitionScope
@Component(modules = [ExhibitionModule::class])
internal interface ExhibitionComponent {
	fun inject(any: Any)
	
	@Component.Factory
	interface Factory {
		fun create(@BindsInstance deps: ExhibitionDependencies): ExhibitionComponent
	}
}


@Component
internal interface Comp{
	@Component.Factory
	interface Fact{
		fun create(@BindsInstance dep: ExhibitionComponent): Comp
	}
}
