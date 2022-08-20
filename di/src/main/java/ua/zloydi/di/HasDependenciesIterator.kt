package ua.zloydi.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment

val Fragment.iterator: Iterator<HasDependencies>
	get() = createIterator(this as? HasDependencies) {
		parentFragment?.iterator ?: requireActivity().iterator
	}

val Activity.iterator
	get() = createIterator(this as? HasDependencies) { application.iterator }

val Application.iterator
	get() = createIterator(this as? HasDependencies) {
		object : Iterator<HasDependencies> {
			override fun hasNext() = false
			override fun next() = error("No parent of application")
		}
	}

private inline fun createIterator(
	current: HasDependencies?, crossinline parentFactory: () -> Iterator<HasDependencies>
) = if (current == null) parentFactory() else object : Iterator<HasDependencies> {
	private var isCurrentConsumed = false
	private var parentIterator: Iterator<HasDependencies>? = null
	override fun hasNext(): Boolean {
		if (isCurrentConsumed && parentIterator == null) parentIterator = parentFactory()
		return !isCurrentConsumed || checkNotNull(parentIterator).hasNext()
	}
	
	override fun next() = if (!isCurrentConsumed) current else checkNotNull(parentIterator).next()
}
