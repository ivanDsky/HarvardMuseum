package ua.zloydi.di

typealias MapDependencies = @JvmSuppressWildcards Map<Class<out Dependencies>, Dependencies>

interface Dependencies

interface HasDependencies {
	val dependencies: MapDependencies
}