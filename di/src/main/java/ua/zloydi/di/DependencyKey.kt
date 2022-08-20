package ua.zloydi.di

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class DependencyKey(val key: KClass<out Dependencies>)