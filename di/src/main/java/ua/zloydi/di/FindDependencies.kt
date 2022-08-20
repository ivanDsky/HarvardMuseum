package ua.zloydi.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment

inline fun <reified D : Dependencies> Fragment.findDependencies(): D = parents.findDependencies()
inline fun <reified D : Dependencies> Activity.findDependencies(): D = parents.findDependencies()
inline fun <reified D : Dependencies> Application.findDependencies(): D = parents.findDependencies()

inline fun <reified D : Dependencies> Iterable<HasDependencies>.findDependencies(): D =
	firstNotNullOfOrNull { it.dependencies[D::class.java] as? D } ?: error("No dependencies")