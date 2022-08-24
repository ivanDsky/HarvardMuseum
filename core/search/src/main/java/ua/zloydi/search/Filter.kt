package ua.zloydi.search

abstract class Filter<T>{
	abstract val name: String
	abstract val value: T
	open fun toQuery(): Pair<String, String> = name to value.toString()
}